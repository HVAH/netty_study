package com.vah.netty_base_study_kt.client;

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel

/**
 *@Description
 *@Author HuangJiang
 **/
class NettyClient {
    fun connect(inetHost: String, inetPort: Int) {
        val eventLoopGroup = NioEventLoopGroup()
        try {
            val bootstrap = Bootstrap()
            bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel::class.java)
                .option(ChannelOption.AUTO_READ, true)
                .handler(MyChannelInitializer())
            val future = bootstrap.connect(inetHost, inetPort).sync()
            println("vah-demo-netty client start done")
            future.channel().closeFuture().sync()
        }catch (e: InterruptedException) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully()
        }
    }

}

fun main() {
    NettyClient().connect("127.0.0.1", 7397)
}
