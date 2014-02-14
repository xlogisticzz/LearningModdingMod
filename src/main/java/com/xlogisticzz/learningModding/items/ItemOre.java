package com.xlogisticzz.learningModding.items;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.lib.Constants;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemOre extends ItemBlock {

    public ItemOre() {
        super(ModBlocks.ores);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int dammage) {
        return dammage;
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return Constants.UnLocalisedNames.ORE + par1ItemStack.getItemDamage();
    }
}
