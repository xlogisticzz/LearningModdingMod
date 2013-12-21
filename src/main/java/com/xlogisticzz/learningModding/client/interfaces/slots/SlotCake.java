package com.xlogisticzz.learningModding.client.interfaces.slots;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotCake extends Slot {

    public SlotCake(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {

        if (par1ItemStack.itemID == Block.cake.blockID) {
            return true;
        } else return par1ItemStack.itemID == Item.cake.itemID;
    }

}
