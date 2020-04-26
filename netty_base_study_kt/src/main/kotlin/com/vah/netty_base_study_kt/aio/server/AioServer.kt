package com.vah.netty_base_study_kt.aio.server;

import java.net.InetSocketAddress
import java.nio.channels.AsynchronousChannelGroup
import java.nio.channels.AsynchronousServerSocketChannel
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

/**
 *@Description
 *@Author HuangJiang
 **/
class AioServer(
): Thread() {

    private var serverSocketChannel: AsynchronousServerSocketChannel? = null
    override fun run() {
        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open(AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10))
            serverSocketChannel?.bind(InetSocketAddress(7396))
            println("aio server start done")
            val latch = CountDownLatch(1)
            serverSocketChannel?.accept(this, AioServerChannelInitializer())
            latch.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun serverSocketChannel() = serverSocketChannel
}

fun main() {
    AioServer().start()
}