package com.xlogisticzz.learningModding.client.interfaces.containers;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.client.interfaces.slots.SlotBomb;
import com.xlogisticzz.learningModding.entities.EntitySpaceship;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSpaceship extends Container {

    private EntitySpaceship spaceship;

    public ContainerSpaceship(InventoryPlayer inventoryPlayer, EntitySpaceship spaceship) {

        this.spaceship = spaceship;

        // Player Hotbar
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(inventoryPlayer, x, 8 + 18 * x, 194));
        }

        // Player Inventory
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(inventoryPlayer, x + y * 9 + 9, 8 + 18 * x, 136 + y * 18));
            }
        }

        //Block Slots
        for (int x = 0; x < 5; x++) {
            addSlotToContainer(new SlotBomb(spaceship, x, 8 + 18 * x, 17));
        }
        for (int x = 0; x < 5; x++) {
            addSlotToContainer(new SlotBomb(spaceship, x + 5, 8 + 18 * x, 35));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return spaceship.isUseableByPlayer(entityplayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        return null;
    }
}
