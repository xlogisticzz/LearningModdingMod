package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.items.ModItems;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockOre;
import net.minecraft.block.StepSound;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

import java.util.Random;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockRubyOre extends BlockOre {

    public String name;

    public BlockRubyOre(int par1, float par2, StepSound par3, String par4, CreativeTabs par5, String par6) {

        super(par1);
        this.setHardness(par2);
        this.setStepSound(par3);
        this.setUnlocalizedName(par4);
        this.setCreativeTab(par5);
        this.name = par6;

    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {

        return ModItems.ruby.itemID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {

        this.blockIcon = reg.registerIcon(Constants.Mod.MODID + ":" + this.name);

    }

}
