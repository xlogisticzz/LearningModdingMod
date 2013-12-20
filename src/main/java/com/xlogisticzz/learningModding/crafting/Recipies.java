package com.xlogisticzz.learningModding.crafting;

import com.xlogisticzz.learningModding.proxies.CommonProxy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.items.ModItems;
import com.xlogisticzz.learningModding.lib.Constants;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class Recipies {
    
    public static void initVanilla() {
    
        /* Register Furnace Recipies */
        CommonProxy.addSmeltingRecipe(ModBlocks.ores.blockID, 1, new ItemStack(ModItems.titanitumIngot), 10);
        /* Register Block Recipies */
        CommonProxy.addShapedRecipe(new ItemStack(ModBlocks.rubyblock), new Object[] { "XXX", "XXX", "XXX", 'X', ModItems.ruby });
        CommonProxy.addShapedRecipe(new ItemStack(ModBlocks.titaniumblock), new Object[] { "XXX", "XXX", "XXX", 'X', ModItems.titanitumIngot });
        CommonProxy.addShapelessRecipe(new ItemStack(ModItems.ruby, 9), ModBlocks.rubyblock);
        CommonProxy.addShapelessRecipe(new ItemStack(ModItems.titanitumIngot, 9), ModBlocks.titaniumblock);
        
        /* Register Item Recipies */
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.wand), new Object[] { "  X", " / ", " / ", 'X', ModItems.ruby, '/', Item.stick });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyWand), new Object[] { "  X", " / ", "/  ", 'X', ModItems.rubyWand, '/', Item.stick });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubySword), new Object[] { " X ", " X ", " S ", 'X', ModItems.ruby, 'S', Item.stick });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyPickaxe), new Object[] { "XXX", " S ", " S ", 'X', ModItems.ruby, 'S', Item.stick });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubySpade), new Object[] { " X ", " S ", " S ", 'X', ModItems.ruby, 'S', Item.stick });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyAxe), new Object[] { " XX", " SX", " S ", 'X', ModItems.ruby, 'S', Item.stick });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyAxe), new Object[] { "XX ", "XS ", " S ", 'X', ModItems.ruby, 'S', Item.stick });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyHoe), new Object[] { " XX", " S ", " S ", 'X', ModItems.ruby, 'S', Item.stick });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyHoe), new Object[] { "XX ", " S ", " S ", 'X', ModItems.ruby, 'S', Item.stick });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyHelmet), new Object[] { "XXX", "X X", "   ", 'X', ModItems.ruby });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyHelmet), new Object[] { "   ", "XXX", "X X", 'X', ModItems.ruby });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyChestplate), new Object[] { "X X", "XXX", "XXX", 'X', ModItems.ruby });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyLeggings), new Object[] { "XXX", "X X", "X X", 'X', ModItems.ruby });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyBoots), new Object[] { "   ", "X X", "X X", 'X', ModItems.ruby });
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyBoots), new Object[] { "X X", "X X", "   ", 'X', ModItems.ruby });
        
        for (int i = 0; i < Constants.ItemNames.DEATHSTONES_NAMES.length; i++){
            ItemStack input = new ItemStack(ModItems.deathstone, 1, i);
            if (i == Constants.ItemNames.DEATHSTONES_NAMES.length - 1){
                ItemStack output = new ItemStack(ModItems.deathstone, 1, 0);
                CommonProxy.addShapelessRecipe(output, input);
            }else{
                ItemStack output = new ItemStack(ModItems.deathstone, 1, i + 1);
                CommonProxy.addShapelessRecipe(output, input);
            }
        }
    }
}
