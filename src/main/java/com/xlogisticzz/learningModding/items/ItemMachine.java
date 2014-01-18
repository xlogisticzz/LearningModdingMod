package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.lib.Constants;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemMachine extends ItemBlock {

    public ItemMachine(int id) {

        super(id);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int dammage) {

        return dammage;
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {

        return Constants.BlockNames.MACHINE_BLOCK[par1ItemStack.getItemDamage()];
    }
}
