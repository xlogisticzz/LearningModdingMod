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

    public String getMinecraftVersion() {
        return Loader.instance().getMinecraftModContainer().getVersion();
    }

    public Object getClient() {
        return null;
    }

    public World getClientWorld() {
        return null;
    }

    public String getCurrentLang() {
        return null;
    }

    public void removeEntity(Entity entity) {
        entity.worldObj.removeEntity(entity);
    }

    public static void registerBlock(Block block, int meta) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("block.", "") + meta);
    }

    public static void registerBlock(Block block, int meta, Class<? extends ItemBlock> item) {
        GameRegistry.registerBlock(block, item, block.getUnlocalizedName().replace("tile.", "") + meta);
    }

    public static void registerBlock(Block block, int meta, String toolClassType, int harvestLevel, String name) {
        registerBlock(block, meta);
        addName(block, name);
        setBlockHarvestLevel(block, meta, toolClassType, harvestLevel);
    }


    public static void registerBlock(Block block, int meta, String toolClassType, int harvestLevel, Class<? extends ItemBlock> item, String name) {
        registerBlock(block, meta, item);
        addName(block, name);
        if (toolClassType != null) {
            setBlockHarvestLevel(block, meta, toolClassType, harvestLevel);
        }
    }

    public static void setBlockHarvestLevel(Block block, int metadata, String toolClass, int harvestLevel) {
        MinecraftForge.setBlockHarvestLevel(block, metadata, toolClass, harvestLevel);
    }

    public static void registerItem(Item item, String name) {
        GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""), Constants.Mod.MODID);
        addName(item, name);
    }

    public static void registerItem(Item item, ItemStack itemStack, String name) {
        GameRegistry.registerItem(item, item.getUnlocalizedName(itemStack), Constants.Mod.MODID);
        addName(itemStack, name);
    }

    public static void registerTileEntity(Class<? extends TileEntity> clas) {
        GameRegistry.registerTileEntity(clas, clas.getName());
    }

    public static void addShapedRecipe(ItemStack result, Object[] recipe) {
        CraftingManager.getInstance().addRecipe(result, recipe);
    }

    public static void addShapelessRecipe(ItemStack result, Object... recipe) {
        CraftingManager.getInstance().addShapelessRecipe(result, recipe);
    }

    public static void addName(Object obj, String name) {
        LanguageRegistry.addName(obj, name);
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
                        EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

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
            world.func_96440_m(x, y, z, 1);
        }
    }
}
