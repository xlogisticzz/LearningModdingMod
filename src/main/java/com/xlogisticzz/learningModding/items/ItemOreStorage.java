package com.xlogisticzz.learningModding.items;

/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.lib.Constants;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemOreStorage extends ItemBlock {


    public ItemOreStorage(int id) {

        super(id);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {

        return damage;
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {

        return Constants.BlockNames.ORESTORAGEBLOCKS[par1ItemStack.getItemDamage()];
    }

}
