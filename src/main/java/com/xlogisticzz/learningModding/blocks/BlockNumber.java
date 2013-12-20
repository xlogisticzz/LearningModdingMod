package com.xlogisticzz.learningModding.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.tileEntites.TileEntityNumbers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockNumber extends BlockContainer {
    
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
    
    protected BlockNumber(int id) {
    
        super(id, Material.rock);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setHardness(1F);
        setStepSound(Block.soundStoneFootstep);
        setUnlocalizedName(Constants.UnLocalisedNames.NUMBER);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
    
        return new TileEntityNumbers();
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
    
        this.icons = new Icon[Constants.Misc.NUMBER_COUNT];
        for (int i = 0; i < this.icons.length; i++){
            this.icons[i] = register.registerIcon(Constants.Mod.MODID + ":numbers/" + Constants.Icons.NUMBERS[i]);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
    
        TileEntityNumbers tile = (TileEntityNumbers) world.getBlockTileEntity(x, y, z);
        return this.icons[tile.getNumber()];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
    
        return this.icons[0];
    }
}
