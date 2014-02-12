package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.tileEntites.TileEntityBomb;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockBomb extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon idleIcon;

    public BlockBomb() {

        super(Material.tnt);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setHardness(2.4F);
        setStepSound(soundTypeGrass);
        setBlockName(Constants.UnLocalisedNames.BOMB);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {

        this.blockIcon = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.BOMB);
        this.idleIcon = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.BOMB_IDLE);

    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int metadata) {

        if (metadata < 3) {
            return this.blockIcon;
        } else {
            return this.idleIcon;
        }
    }


    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {

        if (meta > 1) {
            return null;
        }
        return super.getItemDropped(meta, random, fortune);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {

        return new TileEntityBomb();
    }

}
