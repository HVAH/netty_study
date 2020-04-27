package com.vah.netty_base_study_kt.netty_1_07

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.LineBasedFrameDecoder
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import java.nio.charset.Charset

/**
 *@Description
 *@Author vah
 **/
class MyChannelInitializer: ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        println("链接报告开始")
        println("链接报告信息：本客户端链接到服务端 channelId: ${ch.localAddress()}")
        println("链接报告完毕")
    }
}