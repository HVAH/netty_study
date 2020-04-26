package com.vah.netty_base_study_kt.netty_1_03

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.socket.SocketChannel
import java.util.Date

/**
 *@Description
 *@Author vah
 **/
class MyServerHandler: ChannelInboundHandlerAdapter() {

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        println("" + Date() + "接收到消息：${msg}")
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        val channel = ctx.channel() as SocketChannel
        println("链接报告开始")
        println("链接报告信息：有一客户端链接到本服务端")
        println("链接报告IP:" + channel.localAddress().hostString)
        println("链接报告Port:" + channel.localAddress().port)
        println("链接报告完毕")
    }
}