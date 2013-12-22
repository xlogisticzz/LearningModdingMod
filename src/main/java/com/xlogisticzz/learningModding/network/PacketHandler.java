package com.xlogisticzz.learningModding.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerCakeStorage;
import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerMachine;
import com.xlogisticzz.learningModding.entities.EntitySpaceship;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.tileEntites.TileEntityCakeStorage;
import com.xlogisticzz.learningModding.tileEntites.TileEntityMachine;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {

        ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
        EntityPlayer entityPlayer = (EntityPlayer) player;
        
        /* ID for the packet */
        byte packetType = reader.readByte();
        byte packetId = reader.readByte();

        /* Code based upon ID for the packet */
        switch (packetType) {
        /* Spaceship packet */
            case 0:
                switch (packetId) {
                    case 0:
                        int entityId = reader.readInt();
                        Entity entity = entityPlayer.worldObj.getEntityByID(entityId);
                        if (entity != null && entity instanceof EntitySpaceship && entity.riddenByEntity == entityPlayer) {
                            ((EntitySpaceship) entity).dropBomb();
                        }
                        break;

                    case 1:
                        int entityId1 = reader.readInt();
                        Entity entity1 = entityPlayer.worldObj.getEntityByID(entityId1);
                        if (entity1 != null && entity1 instanceof EntitySpaceship && entity1.riddenByEntity == entityPlayer) {
                            ((EntitySpaceship) entity1).openInventory();
                        }
                        break;
                }
            /*Button Packet type*/
            case 1:
                Container container = entityPlayer.openContainer;
                if (container != null && container instanceof ContainerMachine) {
                    TileEntityMachine machine = ((ContainerMachine) container).getMachine();
                    machine.reciveButtonEvent(packetId);
                }
                break;
            case 2:
                Container container1 = entityPlayer.openContainer;
                if (container1 != null && container1 instanceof ContainerCakeStorage) {
                    TileEntityCakeStorage cakeStorage = ((ContainerCakeStorage) container1).getTile();
                    cakeStorage.reciveButtonEvent(packetId);
                }
                break;

        }

    }


    /* Spaceship Bomb packet */
    public static void sendShipPacket(EntitySpaceship entity, int packetId) {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        switch (packetId) {
            case 0:
                try {
                    dataStream.writeByte(0);
                    dataStream.writeByte(0);
                    dataStream.writeInt(entity.entityId);

                    PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Constants.Mod.CHANNEL_NAME, byteStream.toByteArray()));
                } catch (IOException e) {
                    System.err.append("Failled to send spaceship drop packet");
                }
                break;
            case 1:
                try {
                    dataStream.writeByte(0);
                    dataStream.writeByte(1);
                    dataStream.writeInt(entity.entityId);

                    PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Constants.Mod.CHANNEL_NAME, byteStream.toByteArray()));
                } catch (IOException e) {
                    System.err.append("Failled to send spaceship Inventory packet");
                }
                break;
        }
    }

    /*Machine Button Packets*/
    public static void sendMachineButtonPacket(byte id) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        try {
            dataStream.writeByte((byte) 1);
            dataStream.writeByte(id);

            PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Constants.Mod.CHANNEL_NAME, byteStream.toByteArray()));
        } catch (IOException ex) {
            System.err.append("Failed to send button click packet");
        }
    }
    /*Cake button Packets*/
    public static void sendCakeButtonPacket(byte id) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        try {
            dataStream.writeByte((byte) 2);
            dataStream.writeByte(id);

            PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Constants.Mod.CHANNEL_NAME, byteStream.toByteArray()));
        } catch (IOException ex) {
            System.err.append("Failed to send button click packet");
        }
    }
}
