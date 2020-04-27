package com.vah.netty_base_study_kt.netty_1_04

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.LineBasedFrameDecoder
import io.netty.handler.codec.string.StringDecoder
import java.nio.charset.Charset

/**
 *@Description
 *@Author vah
 **/
class MyChannelInitializer: ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        //ch.pipeline().addLast(LineBasedFrameDecoder(1024))
        ch.pipeline().addLast(StringDecoder(Charset.forName("GBK")))
        ch.pipeline().addLast(MyServerHandler())
    }
}