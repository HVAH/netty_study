package com.vah.netty_base_study_kt.netty_1_07;

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
            val b = Bootstrap()
            b.group(eventLoopGroup)
                .channel(NioSocketChannel::class.java)
                .option(ChannelOption.AUTO_READ, true)
                .handler(MyChannelInitializer())
            val future = b.connect(inetHost, inetPort).sync()
            println("vah-demo-netty client 107 start done")
            future.channel().closeFuture().sync()
        }catch (e: InterruptedException) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully()
        }
    }

}

fun main() {
    NettyClient().connect("127.0.0.1", 7329)
}
