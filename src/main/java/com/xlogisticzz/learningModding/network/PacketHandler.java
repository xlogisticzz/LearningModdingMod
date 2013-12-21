package com.xlogisticzz.learningModding.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.xlogisticzz.learningModding.entities.EntitySpaceship;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
        int packetId = reader.readByte();
        
        /* Code based upon ID for the packet */
        switch (packetId) {
        /* Spaceship Bomb packet */
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
                    ((EntitySpaceship) entity1).forward();
                }
                break;

            case 2:
                int entityId2 = reader.readInt();
                Entity entity2 = entityPlayer.worldObj.getEntityByID(entityId2);
                if (entity2 != null && entity2 instanceof EntitySpaceship && entity2.riddenByEntity == entityPlayer) {
                    ((EntitySpaceship) entity2).backward();
                }
                break;

            case 3:
                int entityId3 = reader.readInt();
                Entity entity3 = entityPlayer.worldObj.getEntityByID(entityId3);
                if (entity3 != null && entity3 instanceof EntitySpaceship && entity3.riddenByEntity == entityPlayer) {
                    ((EntitySpaceship) entity3).up();
                }
                break;

            case 4:
                int entityId4 = reader.readInt();
                Entity entity4 = entityPlayer.worldObj.getEntityByID(entityId4);
                if (entity4 != null && entity4 instanceof EntitySpaceship && entity4.riddenByEntity == entityPlayer) {
                    ((EntitySpaceship) entity4).down();
                }
                break;
        }

    }

    /* Spaceship Bomb packet */
    public static void sendShipBombPacket(EntitySpaceship entity) {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        try {
            dataStream.writeByte(0);
            dataStream.writeInt(entity.entityId);

            PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Constants.Mod.MODID, byteStream.toByteArray()));
        } catch (IOException e) {
            System.err.append("Failled to send spaceship drop packet");
        }
    }

    public static void sendShipForwardPacket(EntitySpaceship entity) {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        try {
            dataStream.writeByte(1);
            dataStream.writeInt(entity.entityId);

            PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Constants.Mod.MODID, byteStream.toByteArray()));
        } catch (IOException e) {
            System.err.append("Failled to send spaceship Forward packet");
        }

    }

    public static void sendShipBackwardPacket(EntitySpaceship entity) {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        try {
            dataStream.writeByte(2);
            dataStream.writeInt(entity.entityId);

            PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Constants.Mod.MODID, byteStream.toByteArray()));
        } catch (IOException e) {
            System.err.append("Failled to send spaceship Forward packet");
        }

    }

    public static void sendShipUpPacket(EntitySpaceship entity) {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        try {
            dataStream.writeByte(3);
            dataStream.writeInt(entity.entityId);

            PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Constants.Mod.MODID, byteStream.toByteArray()));
        } catch (IOException e) {
            System.err.append("Failled to send spaceship Forward packet");
        }

    }

    public static void sendShipDownPacket(EntitySpaceship entity) {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        try {
            dataStream.writeByte(4);
            dataStream.writeInt(entity.entityId);

            PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Constants.Mod.MODID, byteStream.toByteArray()));
        } catch (IOException e) {
            System.err.append("Failled to send spaceship Forward packet");
        }

    }
}
