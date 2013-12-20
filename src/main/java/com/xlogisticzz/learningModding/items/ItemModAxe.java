package com.xlogisticzz.learningModding.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;

import com.xlogisticzz.learningModding.lib.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemModAxe extends ItemAxe {
    
    public String name;
    
    public ItemModAxe(int par1, EnumToolMaterial par2EnumToolMaterial, CreativeTabs par3, int par4, String par5, String par6) {
    
        super(par1, par2EnumToolMaterial);
        this.setCreativeTab(par3);
        this.setMaxDamage(par4);
        this.setMaxStackSize(1);
        this.setUnlocalizedName(par5);
        this.name = par6;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {
    
        this.itemIcon = reg.registerIcon(Constants.Mod.MODID + ":" + this.name);
        
    }
}
