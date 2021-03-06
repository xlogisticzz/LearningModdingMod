package com.xlogisticzz.learningModding.network;

import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerCakeStorage;
import com.xlogisticzz.learningModding.tileEntites.TileEntityCakeStorage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * @author xLoGisTicZz
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class PacketCakeButtonGui extends PacketLearningModding {

    private int id;
    private int type;
    private boolean isShifted;
    private boolean isControl;

    public PacketCakeButtonGui() {
    }

    public PacketCakeButtonGui(int type, int id, boolean isShifted, boolean isControl) {
        this.id = id;
        this.type = type;
        this.isShifted = isShifted;
        this.isControl = isControl;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buf) {
        buf.writeInt(id);
        buf.writeInt(type);
        buf.writeBoolean(isShifted);
        buf.writeBoolean(isControl);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buf) {
        id = buf.readInt();
        type = buf.readInt();
        isShifted = buf.readBoolean();
        isControl = buf.readBoolean();
    }

    @Override
    public void handleClient(EntityPlayer player) {

    }

    @Override
    public void handleServer(EntityPlayer player) {
        Container container = player.openContainer;
        if (container != null && container instanceof ContainerCakeStorage) {
            TileEntityCakeStorage cakeStorage = ((ContainerCakeStorage) container).getTile();
            cakeStorage.reciveInterfaceEvent(type, id, isShifted, isControl);
        }
    }
}
