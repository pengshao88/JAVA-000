package com.yezp.gateway.inbound;

import com.yezp.gateway.filter.GatewayFilter;
import com.yezp.gateway.filter.HttpRequestFilter;
import com.yezp.gateway.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

/**
 * 入站处理
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private HttpOutboundHandler handler;
    private HttpRequestFilter filter;

    public HttpInboundHandler() {
        handler = new HttpOutboundHandler();
        filter = new GatewayFilter();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            // 过滤
            filter.filter(fullRequest, ctx);
            // 执行请求处理
            handler.handle(fullRequest, ctx);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
