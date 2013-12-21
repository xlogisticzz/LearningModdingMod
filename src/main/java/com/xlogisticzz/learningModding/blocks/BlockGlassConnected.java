package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockGlassConnected extends Block {

    public int render;
    private Icon[] icons;

    public BlockGlassConnected(int par1, boolean par2) {

        super(par1, Material.glass);
        this.setStepSound(soundGlassFootstep);
        this.setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        this.render = par2 ? 1 : 0;
        this.setUnlocalizedName(Constants.UnLocalisedNames.GLASS_CONNECTED);
        this.setHardness(0.4f);
    }

    @Override
    public boolean isOpaqueCube() {

        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {

        return false;
    }

    @Override
    public int getRenderBlockPass() {

        return this.render;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {

        int id = par1IBlockAccess.getBlockId(par2, par3, par4);
        return id == this.blockID ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

    @Override
    public Icon getIcon(int par1, int par2) {

        return this.icons[0];

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {

        this.icons = new Icon[Constants.Icons.GLASS_CONNECTED.length];

        for (int i = 0; i < Constants.Icons.GLASS_CONNECTED.length; i++) {
            this.icons[i] = par1IconRegister.registerIcon(Constants.Mod.MODID + ":glass/png/" + Constants.Icons.GLASS_CONNECTED[i]);
        }
    }

    public boolean shouldConnect(int par1) {

        return par1 == this.blockID;
    }

    // Args: iBlockAccess, x, y, z, side(0 == bottom, 1 == top , 2 == north , 3
    // == south , 4 == west, 5 == east)
    @Override
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {

        boolean isOpenUp = false, isOpenDown = false, isOpenLeft = false, isOpenRight = false;

        switch (par5) {

            // bottom
            case 0:
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3, par4 - 1))) {
                    isOpenUp = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3, par4 + 1))) {
                    isOpenDown = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2 - 1, par3, par4))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2 + 1, par3, par4))) {
                    isOpenRight = true;
                }
                break;
            // top
            case 1:
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3, par4 - 1))) {
                    isOpenUp = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3, par4 + 1))) {
                    isOpenDown = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2 - 1, par3, par4))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2 + 1, par3, par4))) {
                    isOpenRight = true;
                }
                break;
            // north
            case 2:
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3 + 1, par4))) {
                    isOpenUp = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3 - 1, par4))) {
                    isOpenDown = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2 + 1, par3, par4))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2 - 1, par3, par4))) {
                    isOpenRight = true;
                }
                break;
            // south
            case 3:
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3 + 1, par4))) {
                    isOpenUp = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3 - 1, par4))) {
                    isOpenDown = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2 - 1, par3, par4))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2 + 1, par3, par4))) {
                    isOpenRight = true;
                }
                break;
            // west
            case 4:
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3 + 1, par4))) {
                    isOpenUp = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3 - 1, par4))) {
                    isOpenDown = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3, par4 - 1))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3, par4 + 1))) {
                    isOpenRight = true;
                }
                break;
            // east
            case 5:
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3 + 1, par4))) {
                    isOpenUp = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3 - 1, par4))) {
                    isOpenDown = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3, par4 + 1))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(par1IBlockAccess.getBlockId(par2, par3, par4 - 1))) {
                    isOpenRight = true;
                }
                break;

        }
        if (isOpenDown && isOpenUp && isOpenLeft && isOpenRight) {
            return this.icons[15];
        } else if (isOpenUp && isOpenLeft && isOpenRight) {
            return this.icons[14];
        } else if (isOpenUp && isOpenDown && isOpenRight) {
            return this.icons[13];
        } else if (isOpenUp && isOpenDown && isOpenLeft) {
            return this.icons[12];
        } else if (isOpenDown && isOpenLeft && isOpenRight) {
            return this.icons[11];
        } else if (isOpenUp && isOpenRight) {
            return this.icons[10];
        } else if (isOpenUp && isOpenLeft) {
            return this.icons[9];
        } else if (isOpenUp && isOpenDown) {
            return this.icons[8];
        } else if (isOpenLeft && isOpenRight) {
            return this.icons[7];
        } else if (isOpenDown && isOpenRight) {
            return this.icons[6];
        } else if (isOpenDown && isOpenLeft) {
            return this.icons[5];
        } else if (isOpenUp) {
            return this.icons[4];
        } else if (isOpenRight) {
            return this.icons[3];
        } else if (isOpenLeft) {
            return this.icons[2];
        } else if (isOpenDown) {
            return this.icons[1];
        } else {
            return this.icons[0];
        }
    }

}
