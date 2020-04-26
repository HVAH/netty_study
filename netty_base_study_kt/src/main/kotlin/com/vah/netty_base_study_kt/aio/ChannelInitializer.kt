package com.vah.netty_base_study_kt.aio;

import com.vah.netty_base_study_kt.aio.server.AioServer
import java.nio.channels.AsynchronousSocketChannel
import java.nio.channels.CompletionHandler

/**
 *@Description
 *@Author HuangJiang
 **/
abstract class ChannelInitializer : CompletionHandler<AsynchronousSocketChannel, AioServer> {
    override fun completed(channel: AsynchronousSocketChannel, attachment: AioServer) {
        try {
            initChannel(channel)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            attachment.serverSocketChannel()?.accept(attachment, this)
        }
    }

    override fun failed(exc: Throwable, attachment: AioServer) {
        exc.printStackTrace()
    }

    abstract fun initChannel(asynchronousSocketChannel: AsynchronousSocketChannel)
}
