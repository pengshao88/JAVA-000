package com.yezp.gateway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * Created on 2020/11/1 15:48.
 *
 * @author yezp
 */
public class HttpInboundServer {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundServer.class);

    private int port;

    public HttpInboundServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // boss 线程组负责 accept connect
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // worker 线程组负责处理读写...... cpu 4核
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128) // 可等待链接队列
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_RCVBUF, 8 * 1024) // 接收缓存区大小
                    .option(ChannelOption.SO_SNDBUF, 8 * 1024) // 发送缓存区大小
                    .option(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 保活
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new HttpInboundInitializer());

            Channel ch = b.bind(port).sync().channel();
            logger.info("网关服务器：开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
