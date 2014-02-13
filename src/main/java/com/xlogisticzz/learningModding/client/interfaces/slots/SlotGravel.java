package com.xlogisticzz.learningModding.client.interfaces.slots;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGravel extends Slot {

    public SlotGravel(IInventory par1IInventory, int id, int x, int y) {
        super(par1IInventory, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {
        return Block.getBlockFromItem(par1ItemStack.getItem()) == Blocks.gravel;
    }
}
