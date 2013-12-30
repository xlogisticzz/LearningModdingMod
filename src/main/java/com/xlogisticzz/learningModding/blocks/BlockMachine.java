package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModding;
import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.tileEntites.TileEntityMachine;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockMachine extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private Icon topIcon;
    @SideOnly(Side.CLIENT)
    private Icon bottomIcon;
    @SideOnly(Side.CLIENT)
    private Icon[] sideIcons;
    @SideOnly(Side.CLIENT)
    private Icon disableIcon;

    public BlockMachine(int par1) {

        super(par1, Material.iron);
        this.setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        this.setHardness(2.5F);
        this.setUnlocalizedName(Constants.UnLocalisedNames.MACHINE_BLOCK);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister IconRegister) {

        this.topIcon = IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.MACHINE_TOP);
        this.bottomIcon = IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.MACHINE_BOTTOM);
        this.disableIcon = IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.MACHINE_DISABLED);

        this.sideIcons = new Icon[Constants.Icons.MACHINE_SIDES.length];
        for (int i = 0; i < Constants.Icons.MACHINE_SIDES.length; i++) {
            this.sideIcons[i] = IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.MACHINE_SIDES[i]);
        }

    }

    private void spawnGravel(World world, IInventory inventory, int x, int y, int z) {
        if (world.isAirBlock(x, y, z)) {
            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack stack = inventory.getStackInSlot(i);
                if (stack != null && stack.itemID == Block.gravel.blockID) {
                    inventory.decrStackSize(i, 1);
                    world.setBlock(x, y, z, Block.gravel.blockID);
                    return;
                }
            }
        }
    }

    @Override
    public void onEntityWalking(World par1World, int x, int y, int z, Entity par5Entity) {

        if (!par1World.isRemote && par1World.getBlockMetadata(x, y, z) % 2 == 0) {
            TileEntity tileEntity = par1World.getBlockTileEntity(x, y, z);
            if (tileEntity != null && tileEntity instanceof TileEntityMachine) {
                TileEntityMachine machine = (TileEntityMachine) tileEntity;
                spawnGravel(par1World, machine, x, y + 20, z);
                spawnGravel(par1World, machine, x, y + 21, z);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int id) {

        int meta = world.getBlockMetadata(x, y, z);
        if (!world.isRemote && world.isBlockIndirectlyGettingPowered(x, y, z) && meta % 2 == 0) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity != null && tileEntity instanceof TileEntityMachine) {
                TileEntityMachine machine = (TileEntityMachine) tileEntity;
                switch (meta / 2) {
                    case 1:
                        for (int i = 0; i < 5; i++) {
                            spawnGravel(world, machine, x, y + 20 + i, z);
                        }
                        break;
                    case 2:
                        for (int i = -1; i <= 1; i++) {
                            spawnGravel(world, machine, x + i, y + 20, z - 2);
                            spawnGravel(world, machine, x + i, y + 20, z + 2);
                            spawnGravel(world, machine, x - 2, y + 20, z + i);
                            spawnGravel(world, machine, x + 2, y + 20, z + i);
                        }
                        break;
                    case 3:
                        for (int i = 1; i <= 3; i++) {
                            spawnGravel(world, machine, x + i, y + 20, z);
                            spawnGravel(world, machine, x - i, y + 20, z);
                            spawnGravel(world, machine, x, y + 20, z + i);
                            spawnGravel(world, machine, x, y + 20, z - i);
                        }
                        break;

                    case 4:
                        for (int i = 0; i < machine.customSetup.length; i++) {
                            if (machine.customSetup[i]) {
                                int dropX = x + i / 7 - 3;
                                int dropZ = z + i % 7 - 3;

                                spawnGravel(world, machine, dropX, y + 20, dropZ);
                            }
                        }

                        break;
                }
            }
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata) {

        switch (side) {

            case 0:
                return this.bottomIcon;

            case 1:
                return metadata % 2 == 1 ? this.disableIcon : this.topIcon;

            default:
                return this.sideIcons[metadata / 2];

        }

    }

    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float offsetX, float offsetY, float offsetZ) {
        if (!par1World.isRemote) {
            if (!par5EntityPlayer.isSneaking()) {
                FMLNetworkHandler.openGui(par5EntityPlayer, LearningModding.instance, 0, par1World, x, y, z);
            }
        }
        return true;
    }

    @Override
    public int damageDropped(int metadata) {

        return metadata;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void getSubBlocks(int id, CreativeTabs par2CreativeTabs, List par3List) {

        for (int i = 0; i < Constants.Icons.MACHINE_SIDES.length; i++) {
            par3List.add(new ItemStack(id, 1, i * 2));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityMachine();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null && te instanceof IInventory) {
            IInventory inventory = (IInventory) te;

            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack stack = inventory.getStackInSlotOnClosing(i);

                if (stack != null) {
                    float spawnX = x + world.rand.nextFloat();
                    float spawnY = y + world.rand.nextFloat();
                    float spawnZ = z + world.rand.nextFloat();

                    EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, stack);

                    float mult = 0.05F;

                    droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * mult;
                    droppedItem.motionX = (4 + world.rand.nextFloat()) * mult;
                    droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * mult;

                    world.spawnEntityInWorld(droppedItem);
                }
            }
        }
        super.breakBlock(world, x, y, z, id, meta);
    }
}
