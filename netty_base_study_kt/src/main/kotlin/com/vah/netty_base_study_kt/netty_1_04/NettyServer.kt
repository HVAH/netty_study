package com.vah.netty_base_study_kt.netty_1_04

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel

/**
 *@Description
 *@Author vah
 **/
class NettyServer {
    fun bing(port: Int) {
        val parentGroup = NioEventLoopGroup()
        val childGroup = NioEventLoopGroup()
        try {
            val b = ServerBootstrap()
            b.group(parentGroup, childGroup)
                .channel(NioServerSocketChannel::class.java)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childHandler(MyChannelInitializer())
            val future = b.bind(port).sync()
            println("vah netty server 104 start done")
            future.channel().closeFuture().sync()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            parentGroup.shutdownGracefully()
            childGroup.shutdownGracefully()
        }
    }
}

fun main() {
    NettyServer().bing(7372)
}