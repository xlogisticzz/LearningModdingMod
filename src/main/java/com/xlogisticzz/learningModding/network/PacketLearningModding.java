package com.xlogisticzz.learningModding.network;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class PacketLearningModding {

    public abstract void encodeInto(ChannelHandlerContext ctx, ByteBuf buf);

    public abstract void decodeInto(ChannelHandlerContext ctx, ByteBuf buf);

    public abstract void handleClient(EntityPlayer player);

    public abstract void handleServer(EntityPlayer player);
}
