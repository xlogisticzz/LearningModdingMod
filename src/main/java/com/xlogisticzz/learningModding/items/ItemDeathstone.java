package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemDeathstone extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public ItemDeathstone() {

        super();
        this.setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {

        return "item." + Constants.UnLocalisedNames.DEATHSTONE + par1ItemStack.getItemDamage();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {

        icons = new IIcon[Constants.Icons.DEATHSTONES_ICONS.length];

        for (int i = 0; i < Constants.Icons.DEATHSTONES_ICONS.length; i++) {
            icons[i] = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.DEATHSTONES_ICONS[i]);
        }
    }

    @Override
    public IIcon getIconFromDamage(int par1) {

        return icons[par1];
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < Constants.Icons.DEATHSTONES_ICONS.length; i++) {
            par3List.add(new ItemStack(item, 1, i));
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        /* DAMAGE VALUES START AT 0 */
        if (par1ItemStack.getItemDamage() == Constants.Icons.DEATHSTONES_ICONS.length - 1) {
            par3List.add("Next Item crafted is " + Constants.Icons.DEATHSTONES_ICONS[0]);
        } else {
            par3List.add("Next Item crafted is " + Constants.Icons.DEATHSTONES_ICONS[par1ItemStack.getItemDamage() + 1]);
        }
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
        int id = par1ItemStack.getItemDamage();
        switch (id) {
            case 0:
                if (par2EntityLivingBase instanceof EntityCreeper) {
                    par2EntityLivingBase.setHealth(0);
                    return true;
                } else {
                    return false;
                }
            case 1:
                if (par2EntityLivingBase instanceof EntityEnderman) {
                    par2EntityLivingBase.setHealth(0);
                    return true;
                } else {
                    return false;
                }
            case 2:
                if (par2EntityLivingBase instanceof EntityPig) {
                    par2EntityLivingBase.setHealth(0);
                    return true;
                } else {
                    return false;
                }
            case 3:
                if (par2EntityLivingBase instanceof EntityPlayer) {
                    par2EntityLivingBase.setHealth(0);
                    return true;
                } else {
                    return false;
                }
            case 4:
                if (par2EntityLivingBase instanceof EntitySkeleton) {
                    par2EntityLivingBase.setHealth(0);
                    return true;
                } else {
                    return false;
                }

            default:
                return false;
        }
    }
}