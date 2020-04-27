package com.vah.netty_base_study_kt.netty_1_05

import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.socket.SocketChannel
import java.nio.charset.Charset
import java.util.Date

/**
 *@Description
 *@Author vah
 **/
class MyServerHandler: ChannelInboundHandlerAdapter() {

    @ExperimentalStdlibApi
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        println("" + Date() + "接收到消息：${msg}")

        val str = "服务端收到: ${Date()} ${msg}"
//        val buf = Unpooled.buffer(str.encodeToByteArray().size)
//        buf.writeBytes(str.toByteArray(Charset.forName("GBK")))
        ctx.writeAndFlush(str)
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        val channel = ctx.channel() as SocketChannel
        println("链接报告开始")
        println("链接报告信息：有一客户端链接到本服务端")
        println("链接报告IP:" + channel.localAddress().hostString)
        println("链接报告Port:" + channel.localAddress().port)
        println("链接报告完毕")

        // 通知客户端
        var str = "通知客户端链接建立成功 ${Date()} ${channel.localAddress()}"
//        val buf = Unpooled.buffer(str.toByteArray(Charset.forName("GBK")).size)
//        buf.writeBytes(str.toByteArray(Charset.forName("GBK")))
        ctx.writeAndFlush(str)
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        println("客户端断开连接 ${ctx.channel().localAddress()}")
    }


}