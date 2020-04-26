package com.vah.netty_base_study_kt.netty_1_02

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import java.nio.charset.Charset
import java.util.Date

/**
 *@Description
 *@Author vah
 **/
class MyServerHandler: ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        val buf = msg as ByteBuf
        val msgByte = ByteArray(buf.readableBytes())
        buf.readBytes(msgByte)
        println("" + Date() + "接收到消息：")
        println(String(msgByte, Charset.forName("GBK")))
    }
}