package com.xlogisticzz.learningModding.client.interfaces.containers;
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
import net.minecraft.item.Item;
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
            addSlotToContainer(new SlotCake(tile, x, 8 + 18 * x, 17));
        }
        for (int x = 0; x < 5; x++) {
            addSlotToContainer(new SlotCake(tile, x + 5, 8 + 18 * x, 35));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return tile.isUseableByPlayer(entityplayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        Slot slot = getSlot(i);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            ItemStack result = stack.copy();

            if (i >= 36) {
                if (!mergeItemStack(stack, 0, 36, false)) {
                    return null;
                }
            } else if (stack.itemID != Item.cake.itemID || !mergeItemStack(stack, 36, 36 + tile.getSizeInventory(), false)) {
                return null;
            }

            if (stack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            slot.onPickupFromSlot(player, stack);

            return result;
        }

        return null;
    }

    public TileEntityCakeStorage getTile() {
        return tile;
    }
}
