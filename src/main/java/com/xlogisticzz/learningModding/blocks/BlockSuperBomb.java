package com.xlogisticzz.learningModding.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockSuperBomb extends Block {
    
    public BlockSuperBomb(int par1) {
    
        super(par1, Material.tnt);
        this.setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        this.setHardness(2.4F);
        this.setStepSound(Block.soundPowderFootstep);
        this.setUnlocalizedName(Constants.UnLocalisedNames.SUPER_BOMB);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
    
        this.blockIcon = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.SUPER_BOMB);
        
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
    
        if (!world.isRemote && world.isBlockIndirectlyGettingPowered(x, y, z)){
            world.createExplosion(null, x + 0.5, y + 0.5, z + 0.5, 30, true);
        }
    }
    
    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion par5Explosion) {
    
        if (!world.isRemote){
            world.createExplosion(null, x + 0.5, y + 0.5, z + 0.5, 30, true);
        }
    }
    
    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float offsetX, float offsetY, float offsetZ) {
    
        if (!par1World.isRemote){
            
            par1World.createExplosion(null, x + 0.5, y + 0.5, z + 0.5, 30, true);
            
        }
        return true;
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
    
        return -1;
    }
}
