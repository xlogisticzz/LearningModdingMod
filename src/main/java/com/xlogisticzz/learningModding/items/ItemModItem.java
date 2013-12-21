package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemModItem extends net.minecraft.item.Item {

    public String name;

    public ItemModItem(int par1, CreativeTabs par2, int par3, String par4, String par5) {

        super(par1);

        this.setCreativeTab(par2);
        this.setMaxStackSize(par3);
        this.setUnlocalizedName(par4);
        this.name = par5;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {

        this.itemIcon = reg.registerIcon(Constants.Mod.MODID + ":" + this.name);

    }
}
