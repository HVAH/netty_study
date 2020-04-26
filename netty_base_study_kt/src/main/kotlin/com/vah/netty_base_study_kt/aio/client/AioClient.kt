package com.vah.netty_base_study_kt.aio.client;

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousSocketChannel
import java.nio.channels.CompletionHandler
import java.nio.charset.Charset

/**
 *@Description
 *@Author HuangJiang
 **/
class AioClient {
}

fun main() {
    val socketChannel = AsynchronousSocketChannel.open()
    val connect = socketChannel.connect(InetSocketAddress("127.0.0.1", 7396))
    println("vah aioclient start")
    socketChannel.read(ByteBuffer.allocate(1024), null, AioClentHandler(socketChannel, Charset.forName("UTF-8")) as CompletionHandler<Int, Any?>)
    Thread.sleep(100000)
}
