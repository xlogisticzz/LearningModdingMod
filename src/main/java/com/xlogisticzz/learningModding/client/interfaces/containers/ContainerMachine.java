package com.xlogisticzz.learningModding.client.interfaces.containers;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.client.interfaces.slots.SlotGravel;
import com.xlogisticzz.learningModding.tileEntites.TileEntityMachine;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMachine extends Container {

    TileEntityMachine entityMachine;

    public ContainerMachine(InventoryPlayer inventoryPlayer, TileEntityMachine entityMachine) {
        this.entityMachine = entityMachine;

        // Player Hotbar
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(inventoryPlayer, x, 8 + 18 * x, 130));
        }

        // Player Inventory
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(inventoryPlayer, x + y * 9 + 9, 8 + 18 * x, 72 + y * 18));
            }
        }

        // Block slots
        for (int x = 0; x < 3; x++) {
            addSlotToContainer(new SlotGravel(entityMachine, x, 8 + 18 * x, 17));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return entityMachine.isUseableByPlayer(entityplayer);
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
            } else if (stack.itemID != Block.gravel.blockID || !mergeItemStack(stack, 36, 36 + entityMachine.getSizeInventory(), false)) {
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

    public TileEntityMachine getMachine() {
        return entityMachine;
    }

}