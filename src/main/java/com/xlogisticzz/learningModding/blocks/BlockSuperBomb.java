package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockSuperBomb extends Block {

    public BlockSuperBomb() {

        super(Material.tnt);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setHardness(2.4F);
        setStepSound(soundTypeGrass);
        setBlockName(Constants.UnLocalisedNames.SUPER_BOMB);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        blockIcon = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.SUPER_BOMB);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote && world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.createExplosion(null, x + 0.5, y + 0.5, z + 0.5, 30, true);
        }
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion par5Explosion) {
        if (!world.isRemote) {
            world.createExplosion(null, x + 0.5, y + 0.5, z + 0.5, 30, true);
        }
    }

    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float offsetX, float offsetY, float offsetZ) {
        if (!par1World.isRemote) {
            par1World.createExplosion(null, x + 0.5, y + 0.5, z + 0.5, 30, true);
        }
        return true;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        return null;
    }
}
