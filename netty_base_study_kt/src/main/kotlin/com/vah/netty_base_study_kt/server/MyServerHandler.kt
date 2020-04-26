package com.vah.netty_base_study_kt.server;

import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.socket.SocketChannel
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.Date

/**
 *@Description
 *@Author HuangJiang
 **/
class MyServerHandler : ChannelInboundHandlerAdapter() {

    @ExperimentalStdlibApi
    override fun channelActive(ctx: ChannelHandlerContext) {
        val channel = ctx.channel() as SocketChannel
        println("链接报告开始")
        println("链接报告信息：有一客户端链接到本服务端")
        println("链接报告 IP:" + channel.localAddress().hostString)
        println("链接报告 Port:" + channel.localAddress().port)
        println("链接报告完毕")
        val str = "通知客户端链接建立成功${Date()} ${channel.localAddress().hostString}"
        val buffer = Unpooled.buffer(str.encodeToByteArray().size)
        buffer.writeBytes(str.encodeToByteArray())
        ctx.writeAndFlush(buffer)
    }


    override fun channelInactive(ctx: ChannelHandlerContext) {
        println("客户端断开链接" + ctx.channel().localAddress().toString())

    }

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        println( SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()) + "接收到消息：" + msg)
        // 通知客户端链消息发送成功
        ctx.writeAndFlush("服务端收到：" + Date() + "\r\n")
    }


    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        ctx.close()
        println("异常信息：\r\n" + cause.message)
    }
}
