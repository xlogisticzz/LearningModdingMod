package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockGlassConnected extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockGlassConnected() {
        super(Material.glass);
        setStepSound(soundTypeGlass);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setBlockName(Constants.UnLocalisedNames.GLASS_CONNECTED);
        setHardness(0.4f);
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
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        int id = Block.getIdFromBlock(par1IBlockAccess.getBlock(par2, par3, par4));
        return id == getIdFromBlock(this) ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        icons = new IIcon[Constants.Icons.GLASS_CONNECTED.length];
        for (int i = 0; i < Constants.Icons.GLASS_CONNECTED.length; i++) {
            icons[i] = par1IconRegister.registerIcon(Constants.Mod.MODID + ":glass/png/" + Constants.Icons.GLASS_CONNECTED[i]);
        }
    }

    public boolean shouldConnect(int par1) {
        return par1 == getIdFromBlock(this);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[0];
    }

    // Args: iBlockAccess, x, y, z, side(0 == bottom, 1 == top , 2 == north , 3
    // == south , 4 == west, 5 == east)
    @Override
    public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        boolean isOpenUp = false, isOpenDown = false, isOpenLeft = false, isOpenRight = false;
        switch (par5) {
            // bottom
            case 0:
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3, par4 - 1)))) {
                    isOpenUp = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3, par4 + 1)))) {
                    isOpenDown = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2 - 1, par3, par4)))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2 + 1, par3, par4)))) {
                    isOpenRight = true;
                }
                break;
            // top
            case 1:
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3, par4 - 1)))) {
                    isOpenUp = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3, par4 + 1)))) {
                    isOpenDown = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2 - 1, par3, par4)))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2 + 1, par3, par4)))) {
                    isOpenRight = true;
                }
                break;
            // north
            case 2:
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3 + 1, par4)))) {
                    isOpenUp = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3 - 1, par4)))) {
                    isOpenDown = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2 + 1, par3, par4)))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2 - 1, par3, par4)))) {
                    isOpenRight = true;
                }
                break;
            // south
            case 3:
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3 + 1, par4)))) {
                    isOpenUp = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3 - 1, par4)))) {
                    isOpenDown = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2 - 1, par3, par4)))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2 + 1, par3, par4)))) {
                    isOpenRight = true;
                }
                break;
            // west
            case 4:
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3 + 1, par4)))) {
                    isOpenUp = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3 - 1, par4)))) {
                    isOpenDown = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3, par4 - 1)))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3, par4 + 1)))) {
                    isOpenRight = true;
                }
                break;
            // east
            case 5:
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3 + 1, par4)))) {
                    isOpenUp = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3 - 1, par4)))) {
                    isOpenDown = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3, par4 + 1)))) {
                    isOpenLeft = true;
                }
                if (shouldConnect(getIdFromBlock(par1IBlockAccess.getBlock(par2, par3, par4 - 1)))) {
                    isOpenRight = true;
                }
                break;

        }
        if (isOpenDown && isOpenUp && isOpenLeft && isOpenRight) {
            return icons[15];
        } else if (isOpenUp && isOpenLeft && isOpenRight) {
            return icons[14];
        } else if (isOpenUp && isOpenDown && isOpenRight) {
            return icons[13];
        } else if (isOpenUp && isOpenDown && isOpenLeft) {
            return icons[12];
        } else if (isOpenDown && isOpenLeft && isOpenRight) {
            return icons[11];
        } else if (isOpenUp && isOpenRight) {
            return icons[10];
        } else if (isOpenUp && isOpenLeft) {
            return icons[9];
        } else if (isOpenUp && isOpenDown) {
            return icons[8];
        } else if (isOpenLeft && isOpenRight) {
            return icons[7];
        } else if (isOpenDown && isOpenRight) {
            return icons[6];
        } else if (isOpenDown && isOpenLeft) {
            return icons[5];
        } else if (isOpenUp) {
            return icons[4];
        } else if (isOpenRight) {
            return icons[3];
        } else if (isOpenLeft) {
            return icons[2];
        } else if (isOpenDown) {
            return icons[1];
        } else {
            return icons[0];
        }
    }
}
