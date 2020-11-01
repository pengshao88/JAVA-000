package com.yezp.gateway.outbound;

import com.yezp.gateway.httpClient.HttpClient;
import com.yezp.gateway.router.GatewayRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpOutboundHandler {

    private GatewayRouter gatewayRouter;
    private ExecutorService proxyService;
    private List<String> proxyUrlList;

    public HttpOutboundHandler() {
        gatewayRouter = new GatewayRouter();
        proxyUrlList = new ArrayList<>(4);
        proxyUrlList.add("http://localhost:8801");
        proxyUrlList.add("http://localhost:8802");
        proxyUrlList.add("http://localhost:8803");
        proxyUrlList.add("http://localhost:8804/test");

        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        proxyService.execute(() -> handleResponse(fullRequest, ctx) );
    }


    public void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        final String url = this.gatewayRouter.route(proxyUrlList) + fullRequest.uri();
        System.out.println("url : " + url);
        FullHttpResponse response = null;

        try {
            Map<String, String> map = new HashMap<>(1);
            map.put("nio", fullRequest.headers().get("nio"));

            CloseableHttpResponse closeableHttpResponse = HttpClient.sendPostBy(url, map);
            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            byte[] body = EntityUtils.toByteArray(httpEntity);

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length",
                    Integer.parseInt(closeableHttpResponse.getFirstHeader("Content-Length").getValue()));
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 写回客户端
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }
    }

}
