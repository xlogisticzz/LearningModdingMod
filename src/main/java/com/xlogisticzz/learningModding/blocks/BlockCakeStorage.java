package com.xlogisticzz.learningModding.blocks;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.LearningModding;
import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.proxies.CommonProxy;
import com.xlogisticzz.learningModding.tileEntites.TileEntityCakeStorage;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCakeStorage extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;

    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;

    public BlockCakeStorage() {
        super(Material.cake);
        setResistance(3F);
        setBlockName(Constants.UnLocalisedNames.CAKE_STORAGE);
        setStepSound(soundTypeCloth);
        setHardness(4F);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        topIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.CAKE_TOP);
        sideIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.CAKE_SIDE);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return topIcon;
        } else {
            return sideIcon;
        }
    }

    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (!par1World.isRemote && !par5EntityPlayer.isSneaking()) {
            FMLNetworkHandler.openGui(par5EntityPlayer, LearningModding.instance, 1, par1World, x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCakeStorage();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMeta) {
        Random rand = new Random();
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity != null && tileEntity instanceof IInventory) {
            IInventory inv = (IInventory) tileEntity;
            CommonProxy.dropItemsFromInventoryOnBlockBreak(inv, world, x, y, z, rand);
        }
        super.breakBlock(world, x, y, z, oldBlock, oldMeta);
    }
}
