package com.xlogisticzz.learningModding.network;

import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerCakeStorage;
import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerMachine;
import com.xlogisticzz.learningModding.tileEntites.TileEntityCakeStorage;
import com.xlogisticzz.learningModding.tileEntites.TileEntityMachine;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * @author xLoGisTicZz
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class PacketCakeButton extends PacketLearningModding{

    private int id;

    public PacketCakeButton() {
    }

    public PacketCakeButton(int id) {
        this.id = id;
    }

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
        Container container = player.openContainer;
        if (container != null && container instanceof ContainerCakeStorage) {
            TileEntityCakeStorage cakeStorage = ((ContainerCakeStorage) container).getTile();
            cakeStorage.reciveButtonEvent((byte) id);
        }
    }
}
