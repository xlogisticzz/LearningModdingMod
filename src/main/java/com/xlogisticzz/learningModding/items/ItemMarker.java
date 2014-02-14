package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.lib.Constants;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemMarker extends ItemBlock {

    public ItemMarker() {
        super(ModBlocks.markerBlock);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int dammage) {
        return dammage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return Constants.UnLocalisedNames.MARKERS + stack.getItemDamage();
    }
}
