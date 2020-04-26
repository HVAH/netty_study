package com.vah.netty_base_study_kt.aio;

import java.nio.ByteBuffer
import java.nio.channels.AsynchronousSocketChannel
import java.nio.charset.Charset

/**
 *@Description
 *@Author HuangJiang
 **/
class ChannelHandler(
    private var channel: AsynchronousSocketChannel,
    private var charset: Charset
) {

    fun writeAndFlush(msg: Any) {
        val byteArray = msg.toString().toByteArray(charset)
        val writeBuffer = ByteBuffer.allocate(byteArray.size)
        writeBuffer.put(byteArray)
        writeBuffer.flip()
        channel.write(writeBuffer)
    }

    fun channel(): AsynchronousSocketChannel = channel

}
