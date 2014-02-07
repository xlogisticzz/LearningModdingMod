package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockMarker extends Block {

    public IIcon[] icons;

    public BlockMarker() {

        super(Material.iron);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setHardness(2.5F);
        setBlockName(Constants.UnLocalisedNames.MARKERS);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {

        icons = new IIcon[Constants.Icons.MARKERS.length];

        for (int i = 0; i < Constants.Icons.MARKERS.length; i++) {

            this.icons[i] = par1IconRegister.registerIcon(Constants.Mod.MODID + ":markers/" + Constants.Icons.MARKERS[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {

        return this.icons[metadata];

    }

    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float offsetX, float offsetY, float offsetZ) {

        if (!par1World.isRemote) {
            int metadata = par1World.getBlockMetadata(x, y, z);

            int selectedType = metadata / 2;

            int isDisabled = metadata % 2 == 1 ? 0 : 1;

            int newMetadata = selectedType * 2 + isDisabled;

            par1World.setBlockMetadataWithNotify(x, y, z, newMetadata, 3);
        }
        return true;
    }

    @Override
    public int damageDropped(int metadata) {

        return metadata;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List par3List) {

        for (int i = 0; i < Constants.Icons.MARKERS.length / 2; i++) {
            par3List.add(new ItemStack(item, 1, i * 2));
        }
    }
}
