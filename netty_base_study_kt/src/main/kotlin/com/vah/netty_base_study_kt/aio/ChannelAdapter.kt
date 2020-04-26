package com.vah.netty_base_study_kt.aio;

import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousSocketChannel
import java.nio.channels.CompletionHandler
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 *@Description
 *@Author HuangJiang
 **/
abstract class ChannelAdapter(
    private var charset: Charset,
    private var channel: AsynchronousSocketChannel
) : CompletionHandler<Int, Any> {

    init {
        if (channel.isOpen) {
            channelActive(ChannelHandler(channel, charset))
        }
    }

    override fun completed(result: Int, attachment: Any?) {
        try {
            val buffer = ByteBuffer.allocate(1024)
            val timeout = 60 * 60L
            channel.read(buffer, timeout, TimeUnit.SECONDS, null, object : CompletionHandler<Int, Any?> {
                override fun completed(result: Int, attachment: Any?) {
                    if (result == -1) {
                        try {
                            channelInactive(ChannelHandler(channel, charset))
                            channel.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        return
                    }
                    buffer.flip()
                    channelRead(ChannelHandler(channel, charset), charset.decode(buffer))
                    buffer.clear()
                    channel.read<Any?>(buffer, timeout, TimeUnit.SECONDS, null, this)
                }

                override fun failed(exc: Throwable, attachment: Any?) {
                    exc.printStackTrace()
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun failed(exc: Throwable, attachment: Any) {
        exc.printStackTrace()
    }

    abstract fun channelActive(channelHandler: ChannelHandler)

    abstract fun channelInactive(channelHandler: ChannelHandler)

    abstract fun channelRead(channelHandler: ChannelHandler, msg: Any)
}
