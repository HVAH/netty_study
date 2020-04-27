package com.vah.netty_base_study_kt.netty_1_08

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
        ch.pipeline()
            .addLast(StringDecoder(Charset.forName("GBK")))
            .addLast(StringEncoder(Charset.forName("GBK")))
            .addLast(MyClientHandler())
    }
}