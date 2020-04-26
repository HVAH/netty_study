package com.vah.netty_base_study_kt.server;

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel

/**
 *@Description
 *@Author HuangJiang
 **/
class NettyServer {
    fun bing(port: Int) {
        val eventLoopGroup = NioEventLoopGroup()
        val childGroup = NioEventLoopGroup()
        try {
            val serverBootstrap = ServerBootstrap()
            serverBootstrap.group(eventLoopGroup, childGroup)
                .channel(NioServerSocketChannel::class.java)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childHandler(MyChannelInitializer())
            val channelFuture = serverBootstrap.bind(port).sync()
            println("vah-demo-netty server start done")
            channelFuture.channel().closeFuture().sync()
        } finally {
            childGroup.shutdownGracefully()
            eventLoopGroup.shutdownGracefully()
        }
    }

}

fun main() {
    NettyServer().bing(7397)
}
