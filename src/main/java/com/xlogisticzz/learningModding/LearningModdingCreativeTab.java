package com.xlogisticzz.learningModding;

import com.xlogisticzz.learningModding.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class LearningModdingCreativeTab {

    public static CreativeTabs tabLearningModding = new CreativeTabs("tabLearningModding") {

        @Override
        public ItemStack getIconItemStack() {

            return new ItemStack(ModItems.items, 1, 0);
        }

        @Override
        public Item getTabIconItem() {
            return ModItems.items;
        }

        @Override
        public int func_151243_f() {
            return 1;
        }
    };

}
