package com.xlogisticzz.learningModding.blocks;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.proxies.CommonProxy;
import com.xlogisticzz.learningModding.tileEntites.TileEntityCustomFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCustomFurnace extends BlockContainer {

    @SideOnly(Side.CLIENT)
    public String IconLocation = Constants.Mod.MODID + ":" + "furnace/";

    @SideOnly(Side.CLIENT)
    public Icon[] icons;

    public BlockCustomFurnace(int par1) {
        super(par1, Material.rock);
        setStepSound(soundStoneFootstep);
        setHardness(0.8F);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setUnlocalizedName(Constants.UnLocalisedNames.CUSTOM_FURNACE);
    }

    @Override
    public Icon getIcon(int side, int meta) {
        switch (side) {
            case 0:
                return icons[3];
            case 1:
                return icons[3];
            default:
                byte active = (isActive(meta));
                byte sideComp = (byte) (side - 2);

                if (active == 1) {
                    if ((meta - 4) != sideComp) {
                        return icons[0];
                    } else {
                        return icons[2];
                    }
                } else {
                    if (meta != sideComp) {
                        return icons[0];
                    } else {
                        return icons[1];
                    }
                }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        icons = new Icon[Constants.Icons.CUSTOM_FURNACE.length];

        for (int i = 0; i < icons.length; i++) {
            icons[i] = iconRegister.registerIcon(IconLocation + Constants.Icons.CUSTOM_FURNACE[i]);
        }
    }

    @Override
    public int damageDropped(int par1) {
        return 0;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z) {
        if (!world.isRemote) {
            int l = world.getBlockId(x, y, z - 1);
            int i1 = world.getBlockId(x, y, z + 1);
            int j1 = world.getBlockId(x - 1, y, z);
            int k1 = world.getBlockId(x + 1, y, z);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1]) {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l]) {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1]) {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1]) {
                b0 = 4;
            }
            byte active = (byte) (isActive(world.getBlockMetadata(x, y, z)) + 1);
            world.setBlockMetadataWithNotify(x, y, z, b0 * active, 2);
        }
    }

    private byte isActive(int meta) {
        byte b0 = 0;
        if (meta < 8 && meta > 3) {
            b0 = 1;
        }
        return b0;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityCustomFurnace();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        byte active = isActive(world.getBlockMetadata(x, y, z));
        byte add = (byte) ((active == 0) ? 0 : 4);

        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 0 + add, 2);
        }

        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 3 + add, 2);
        }

        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 1 + add, 2);
        }

        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 2 + add, 2);
        }
    }

    //TODO open GUI instead of changing state as this was a test
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offsetX, float offsetY, float offsetZ) {
        byte meta = (byte) world.getBlockMetadata(x, y, z);
        if (meta < 4) {
            world.setBlockMetadataWithNotify(x, y, z, meta + 4, 2);
            return true;
        } else if (meta < 8) {
            world.setBlockMetadataWithNotify(x, y, z, meta - 4, 2);
            return true;
        }
        return false;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (isActive(world.getBlockMetadata(x, y, z)) == 1) {
            int l = world.getBlockMetadata(x, y, z);
            float f = (float) x + 0.5F;
            float f1 = (float) y + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
            float f2 = (float) z + 0.5F;
            float f3 = 0.52F;
            float f4 = rand.nextFloat() * 0.6F - 0.3F;

            if (l == 2 || l == 6) {
                world.spawnParticle("smoke", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
            } else if (l == 3 || l == 7) {
                world.spawnParticle("smoke", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
            } else if (l == 0 || l == 4) {
                world.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
            } else if (l == 1 || l == 5) {
                world.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (isActive(world.getBlockMetadata(x, y, z)) == 0) {
            return 0;
        } else {
            return 14;
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int oldId, int oldMeta) {
        Random rand = new Random();
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if(tileEntity != null && tileEntity instanceof IInventory){
            IInventory inv = (IInventory)tileEntity;
            CommonProxy.dropItemsFromInventoryOnBlockBreak(inv, world, x, y, z, rand);
        }

        super.breakBlock(world, x, y, z, oldId, oldMeta);
    }
}
