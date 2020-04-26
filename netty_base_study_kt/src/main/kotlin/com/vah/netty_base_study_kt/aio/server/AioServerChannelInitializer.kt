package com.vah.netty_base_study_kt.aio.server;

import com.vah.netty_base_study_kt.aio.ChannelInitializer
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousSocketChannel
import java.nio.channels.CompletionHandler
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 *@Description
 *@Author HuangJiang
 **/
class AioServerChannelInitializer: ChannelInitializer() {
    override fun initChannel(channel: AsynchronousSocketChannel) {
        channel.read(ByteBuffer.allocate(1024), 10, TimeUnit.SECONDS, null, AioServerHandler(channel, Charset.forName("GBK")) as CompletionHandler<Int, Any?>)
    }
}
