package com.xlogisticzz.learningModding.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;

/**
 * @author xLoGisTicZz
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class EntityStruckByLightningEventHandler {


    @SubscribeEvent
    public void onEntityStruckByLightningEvent(EntityStruckByLightningEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                if (player.inventory.getStackInSlot(i) != null) {
                    EntityItem item = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, player.inventory.getStackInSlot(i));
                    player.inventory.setInventorySlotContents(i, null);
                    player.worldObj.spawnEntityInWorld(item);
                }
            }
        }
    }

}
