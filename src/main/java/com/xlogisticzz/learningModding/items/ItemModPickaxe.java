package com.xlogisticzz.learningModding.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;

import com.xlogisticzz.learningModding.lib.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemModPickaxe extends ItemPickaxe {
    
    public String name;
    
    public ItemModPickaxe(int par1, EnumToolMaterial par2EnumToolMaterial, float par3, float par4, CreativeTabs par5, String par6, String par7, int par8) {
    
        super(par1, par2EnumToolMaterial);
        this.damageVsEntity = par3;
        this.efficiencyOnProperMaterial = par4;
        this.setCreativeTab(par5);
        this.setMaxStackSize(1);
        this.setUnlocalizedName(par6);
        this.name = par7;
        this.setMaxDamage(par8);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {
    
        this.itemIcon = reg.registerIcon(Constants.Mod.MODID + ":" + this.name);
        
    }
    
}
