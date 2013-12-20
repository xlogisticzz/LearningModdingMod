package com.xlogisticzz.learningModding;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.xlogisticzz.learningModding.items.ModItems;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class LearningModdingCreativeTab {
    
    public static CreativeTabs tabLearningModding = new CreativeTabs("tabLearningModding") {
        
        /* Tab Icon */
        @Override
        public ItemStack getIconItemStack() {
        
            return new ItemStack(ModItems.ruby, 1, 0);
        }
    };
    
}
