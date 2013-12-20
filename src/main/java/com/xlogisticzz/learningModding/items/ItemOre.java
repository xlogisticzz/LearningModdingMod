package com.xlogisticzz.learningModding.items;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.lib.Constants;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemOre extends ItemBlock {

    public ItemOre(int id) {

        super(id);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int dammage) {

        return dammage;
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {

        return Constants.BlockNames.ORES[par1ItemStack.getItemDamage()];
    }
}
