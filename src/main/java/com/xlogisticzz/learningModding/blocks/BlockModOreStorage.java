package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockOreStorage;
import net.minecraft.block.StepSound;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockModOreStorage extends BlockOreStorage {

    public String name;

    public BlockModOreStorage(int par1, StepSound par2, CreativeTabs par3, float par4, float par5, String par6, String par7) {

        super(par1);
        this.setStepSound(par2);
        this.setCreativeTab(par3);
        this.setHardness(par4);
        this.setResistance(par5);
        this.setUnlocalizedName(par6);
        this.name = par7;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {

        this.blockIcon = reg.registerIcon(Constants.Mod.MODID + ":" + this.name);

    }

}
