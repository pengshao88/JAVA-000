package com.yezp.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Description:网关过滤器
 *
 * Created on 2020/11/1.
 * @author yezp
 */
public class GatewayFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("nio", "yezhipeng");
    }

}
