package com.vah.netty_base_study_kt.netty_1_08

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.socket.SocketChannel
import java.text.SimpleDateFormat
import java.util.Date

/**
 *@Description
 *@Author vah
 **/
class MyClientHandler : ChannelInboundHandlerAdapter() {
    @ExperimentalStdlibApi
    override fun channelActive(ctx: ChannelHandlerContext) {
        val channel = ctx.channel() as SocketChannel
        println("链接报告开始")
        println("链接报告信息：本客户端链接到服务端。channelId：" + channel.id())
        println("链接报告 IP:" + channel.localAddress().getHostString())
        println("链接报告 Port:" + channel.localAddress().getPort())
        println("链接报告完毕")
        val str = "通知服务端链接建立成功${Date()} ${channel.localAddress()}"
        ctx.writeAndFlush(str)
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        println("断开链接" + ctx.channel().localAddress().toString())

    }

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        println( SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()) + "接收到消息：" + msg)
        // 通知客户端链消息发送成功
        ctx.writeAndFlush("客户端收到：${Date()} ${msg}")
    }


    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        ctx.close()
        println("异常信息：\r\n" + cause.message)
    }
}