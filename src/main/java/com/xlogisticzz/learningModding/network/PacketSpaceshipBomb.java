package com.xlogisticzz.learningModding.network;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.entities.EntitySpaceship;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketSpaceshipBomb extends PacketLearningModding {

    private int id;

    public PacketSpaceshipBomb(int id) {
        this.id = id;
    }

    public PacketSpaceshipBomb(){}

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buf) {
        buf.writeInt(id);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buf) {
        id = buf.readInt();
    }

    @Override
    public void handleClient(EntityPlayer player) {

    }

    @Override
    public void handleServer(EntityPlayer player) {
        Entity entity = player.worldObj.getEntityByID(id);
        if (entity != null && entity instanceof EntitySpaceship && entity.riddenByEntity == player) {
            ((EntitySpaceship) entity).dropBomb();
        }
    }
}
