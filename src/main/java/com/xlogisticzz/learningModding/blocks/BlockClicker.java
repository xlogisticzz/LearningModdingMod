package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.tileEntites.TileEntityClicker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockClicker extends BlockContainer {

    @SideOnly(Side.CLIENT)
    public IIcon clicker;
    @SideOnly(Side.CLIENT)
    public IIcon clickerLinked;

    public BlockClicker() {
        super(Material.iron);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setHardness(2.5F);
        setStepSound(soundTypeMetal);
        setBlockName(Constants.UnLocalisedNames.CLICKER);
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        TileEntityClicker tile = (TileEntityClicker) world.getTileEntity(x, y, z);
        tile.attackButton(true);
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int x, int y, int z, int side) {
        return par1IBlockAccess.getBlockMetadata(x, y, z);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (player.isSneaking()) {
            return false;
        } else {
            TileEntityClicker tile = (TileEntityClicker) world.getTileEntity(x, y, z);
            tile.attackButton(false);
            return true;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.clicker = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.CLICKER);
        this.clickerLinked = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.CLICKER_LINKED);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return metadata == 7 ? this.clickerLinked : this.clicker;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityClicker();
    }
}
