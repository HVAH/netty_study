package com.vah.netty_base_study_kt.server;

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.LineBasedFrameDecoder
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import java.nio.charset.Charset

/**
 *@Description
 *@Author HuangJiang
 **/
class MyChannelInitializer: ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        ch.pipeline().addLast(LineBasedFrameDecoder(1024))
        ch.pipeline().addLast(StringDecoder(Charset.forName("UTF-8")))
        ch.pipeline().addLast(StringEncoder(Charset.forName("UTF-8")))
        ch.pipeline().addLast(MyServerHandler())
    }
}
