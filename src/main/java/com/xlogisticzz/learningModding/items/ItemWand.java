package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemWand extends Item {

    public ItemWand() {
        super();
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setUnlocalizedName(Constants.UnLocalisedNames.WAND);
        setMaxDamage(1);
        setFull3D();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        itemIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.WAND);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target) {
        if (target instanceof EntityCreeper) {
            (target).setHealth(0);
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 5));
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 5));
            if (!player.worldObj.isRemote) {
                player.entityDropItem(new ItemStack(ModItems.deathstone, 1, 0), 1F);
            }
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
            return true;
        } else if (target instanceof EntityEnderman) {
            (target).setHealth(0);
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 5));
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 5));
            if (!player.worldObj.isRemote) {
                player.entityDropItem(new ItemStack(ModItems.deathstone, 1, 1), 1F);
            }
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
            return true;
        } else if (target instanceof EntityPig) {
            (target).setHealth(0);
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 5));
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 5));
            if (!player.worldObj.isRemote) {
                player.entityDropItem(new ItemStack(ModItems.deathstone, 1, 2), 1F);
            }
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
            return true;
        } else if (target instanceof EntityPlayer) {
            (target).setHealth(0);
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 5));
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 5));
            if (!player.worldObj.isRemote) {
                player.entityDropItem(new ItemStack(ModItems.deathstone, 1, 3), 1F);
            }
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
            return true;
        } else if (target instanceof EntitySkeleton) {
            (target).setHealth(0);
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 5));
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 5));
            if (!player.worldObj.isRemote) {
                player.entityDropItem(new ItemStack(ModItems.deathstone, 1, 4), 1F);
            }
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
            return true;
        } else {
            return false;
        }
    }

}
