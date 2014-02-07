package com.xlogisticzz.learningModding.proxies;

import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.util.Random;


/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class CommonProxy {

    public void initSounds() {
    }

    public void initRenderers() {
    }

    public static void addShapedRecipe(ItemStack result, Object[] recipe) {
        CraftingManager.getInstance().addRecipe(result, recipe);
    }

    public static void addShapelessRecipe(ItemStack result, Object... recipe) {
        CraftingManager.getInstance().addShapelessRecipe(result, recipe);
    }

    public static void addSmeltingRecipe(int id, int meta, ItemStack itemstack, int xp) {
        FurnaceRecipes.smelting().addSmelting(id, meta, itemstack, xp);
    }

    public static void dropItemsFromInventoryOnBlockBreak(IInventory inv, World world, int x, int y, int z, Random rand) {
        if (inv != null) {
            for (int j1 = 0; j1 < inv.getSizeInventory(); ++j1) {
                ItemStack itemstack = inv.getStackInSlot(j1);

                if (itemstack != null) {
                    float f = rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = rand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0) {
                        int k1 = rand.nextInt(21) + 10;

                        if (k1 > itemstack.stackSize) {
                            k1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= k1;
                        EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (double) ((float) rand.nextGaussian() * f3);
                        entityitem.motionY = (double) ((float) rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double) ((float) rand.nextGaussian() * f3);
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }
            //Last Param is never used so is useless. For more info see method in world.class
            world.func_147453_f(x, y, z, world.getBlock(x,y,z));
        }
    }
}
