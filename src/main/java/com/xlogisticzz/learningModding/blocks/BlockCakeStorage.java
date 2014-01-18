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
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCakeStorage extends BlockContainer {

    @SideOnly(Side.CLIENT)
    Icon topIcon;

    @SideOnly(Side.CLIENT)
    Icon sideIcon;

    public BlockCakeStorage(int par1) {
        super(par1, Material.cake);
        setResistance(3F);
        setUnlocalizedName(Constants.UnLocalisedNames.CAKE_STORAGE);
        setStepSound(Block.soundClothFootstep);
        setHardness(4F);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        topIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.CAKE_TOP);
        sideIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.CAKE_SIDE);

    }

    @Override
    public Icon getIcon(int side, int meta) {
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
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityCakeStorage();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int oldId, int oldMeta) {
        Random rand = new Random();
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity != null && tileEntity instanceof IInventory) {
            IInventory inv = (IInventory) tileEntity;
            CommonProxy.dropItemsFromInventoryOnBlockBreak(inv, world, x, y, z, rand);
        }

        super.breakBlock(world, x, y, z, oldId, oldMeta);
    }
}
