package com.xlogisticzz.learningModding.client.interfaces.slots;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCake extends Slot {

    public SlotCake(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {

        if (Block.getBlockFromItem(par1ItemStack.getItem()) == Blocks.cake) {
            return true;
        } else return par1ItemStack.getItem() == Items.cake;
    }

}
