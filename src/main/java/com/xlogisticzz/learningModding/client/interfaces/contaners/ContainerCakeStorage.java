package com.xlogisticzz.learningModding.client.interfaces.contaners;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.client.interfaces.slots.SlotCake;
import com.xlogisticzz.learningModding.tileEntites.TileEntityCakeStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class ContainerCakeStorage extends Container {

    private TileEntityCakeStorage tile;

    public ContainerCakeStorage(InventoryPlayer inventoryPlayer, TileEntityCakeStorage tile) {
        this.tile = tile;

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
            addSlotToContainer(new SlotCake(tile, x, 44 + 18 * x, 53));
        }
        for (int x = 0; x < 5; x++) {
            addSlotToContainer(new SlotCake(tile, x + 5, 44 + 18 * x, 71));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return tile.isUseableByPlayer(entityplayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        return null;
    }
}
