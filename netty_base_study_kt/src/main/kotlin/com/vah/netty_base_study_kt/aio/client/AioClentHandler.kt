package com.vah.netty_base_study_kt.aio.client;

import com.vah.netty_base_study_kt.aio.ChannelAdapter
import com.vah.netty_base_study_kt.aio.ChannelHandler
import java.nio.channels.AsynchronousSocketChannel
import java.nio.charset.Charset
import java.util.Date

/**
 *@Description
 *@Author HuangJiang
 **/
class AioClentHandler(
    channel: AsynchronousSocketChannel,
    charset: Charset
): ChannelAdapter(charset, channel) {


    override fun channelActive(channelHandler: ChannelHandler) {
        try {
            println("连接信息报告：${channelHandler.channel().remoteAddress}")
            channelHandler.writeAndFlush("通知服务端链接建立成功 ${Date()} ${channelHandler.channel().remoteAddress}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun channelInactive(channelHandler: ChannelHandler) {
    }

    override fun channelRead(channelHandler: ChannelHandler, msg: Any) {
        println("服务端收到：${Date()} ${msg} \r\n")
        channelHandler.writeAndFlush("客户端处理消息success \r\n")
    }
}
