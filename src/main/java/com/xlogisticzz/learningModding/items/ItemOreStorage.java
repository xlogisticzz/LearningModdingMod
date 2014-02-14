package com.xlogisticzz.learningModding.items;

/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.lib.Constants;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemOreStorage extends ItemBlock {

    public ItemOreStorage() {
        super(ModBlocks.orestorageblocks);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return Constants.UnLocalisedNames.ORE_STORAGE_BLOCKS + itemStack.getItemDamage();
    }

}
