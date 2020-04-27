package com.vah.netty_base_study_kt.netty_1_06

import io.netty.channel.group.DefaultChannelGroup
import io.netty.util.concurrent.GlobalEventExecutor

/**
 *@Description
 *@Author vah
 **/
class ChannelHandler {
    companion object{
        val channelGroup = DefaultChannelGroup(GlobalEventExecutor.INSTANCE)
    }
}