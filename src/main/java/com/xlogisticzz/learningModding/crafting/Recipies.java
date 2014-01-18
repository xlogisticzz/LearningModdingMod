package com.xlogisticzz.learningModding.crafting;

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.items.ModItems;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.proxies.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class Recipies {

    public static void initVanilla() {
    
        /* Register Furnace Recipies */
        CommonProxy.addSmeltingRecipe(ModBlocks.ores.blockID, 1, new ItemStack(ModItems.items, 1, 1), 10);
        /* Register Block Recipies */
        CommonProxy.addShapedRecipe(new ItemStack(ModBlocks.orestorageblocks, 1, 0), new Object[]{"XXX", "XXX", "XXX", 'X', new ItemStack(ModItems.items, 1, 0)});
        CommonProxy.addShapedRecipe(new ItemStack(ModBlocks.orestorageblocks, 1, 1), new Object[]{"XXX", "XXX", "XXX", 'X', new ItemStack(ModItems.items, 1, 0)});
        CommonProxy.addShapelessRecipe(new ItemStack(ModItems.items, 9, 0), new ItemStack(ModBlocks.orestorageblocks, 1, 0));
        CommonProxy.addShapelessRecipe(new ItemStack(ModItems.items, 9, 1), new ItemStack(ModBlocks.orestorageblocks, 1, 1));
        CommonProxy.addShapedRecipe(new ItemStack(ModBlocks.customFurnace, 1, 0), new Object[]{"XXX", "XFX", "XXX", 'X', new ItemStack(Block.blockNetherQuartz, 1, 0), 'F', new ItemStack(Block.furnaceIdle, 1, 0)});

        /* Register Item Recipies */
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.wand), new Object[]{"  X", " / ", " / ", 'X', new ItemStack(ModItems.items, 1, 0), '/', Item.stick});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyWand), new Object[]{"  X", " / ", "/  ", 'X', new ItemStack(ModItems.items, 1, 0), '/', Item.stick});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubySword), new Object[]{" X ", " X ", " S ", 'X', new ItemStack(ModItems.items, 1, 0), 'S', Item.stick});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyPickaxe), new Object[]{"XXX", " S ", " S ", 'X', new ItemStack(ModItems.items, 1, 0), 'S', Item.stick});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubySpade), new Object[]{" X ", " S ", " S ", 'X', new ItemStack(ModItems.items, 1, 0), 'S', Item.stick});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyAxe), new Object[]{" XX", " SX", " S ", 'X', new ItemStack(ModItems.items, 1, 0), 'S', Item.stick});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyAxe), new Object[]{"XX ", "XS ", " S ", 'X', new ItemStack(ModItems.items, 1, 0), 'S', Item.stick});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyHoe), new Object[]{" XX", " S ", " S ", 'X', new ItemStack(ModItems.items, 1, 0), 'S', Item.stick});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyHoe), new Object[]{"XX ", " S ", " S ", 'X', new ItemStack(ModItems.items, 1, 0), 'S', Item.stick});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyHelmet), new Object[]{"XXX", "X X", "   ", 'X', new ItemStack(ModItems.items, 1, 0)});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyHelmet), new Object[]{"   ", "XXX", "X X", 'X', new ItemStack(ModItems.items, 1, 0)});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyChestplate), new Object[]{"X X", "XXX", "XXX", 'X', new ItemStack(ModItems.items, 1, 0)});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyLeggings), new Object[]{"XXX", "X X", "X X", 'X', new ItemStack(ModItems.items, 1, 0)});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyBoots), new Object[]{"   ", "X X", "X X", 'X', new ItemStack(ModItems.items, 1, 0)});
        CommonProxy.addShapedRecipe(new ItemStack(ModItems.rubyBoots), new Object[]{"X X", "X X", "   ", 'X', new ItemStack(ModItems.items, 1, 0)});

        for (int i = 0; i < Constants.ItemNames.DEATHSTONES.length; i++) {
            ItemStack input = new ItemStack(ModItems.deathstone, 1, i);
            if (i == Constants.ItemNames.DEATHSTONES.length - 1) {
                ItemStack output = new ItemStack(ModItems.deathstone, 1, 0);
                CommonProxy.addShapelessRecipe(output, input);
            } else {
                ItemStack output = new ItemStack(ModItems.deathstone, 1, i + 1);
                CommonProxy.addShapelessRecipe(output, input);
            }
        }
    }
}
