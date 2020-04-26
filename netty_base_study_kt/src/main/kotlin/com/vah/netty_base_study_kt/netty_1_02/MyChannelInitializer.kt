package com.vah.netty_base_study_kt.netty_1_02

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

/**
 *@Description
 *@Author vah
 **/
class MyChannelInitializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(channel: SocketChannel) {
        println("链接报告开始")
        println("链接报告信息：有一客户端链接到本服务端")
        println("链接报告IP:" + channel.localAddress().hostString)
        println("链接报告Port:" + channel.localAddress().port)
        println("链接报告完毕")

        channel.pipeline().addLast(MyServerHandler())
    }
}