package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;
import java.util.Random;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockModOreStorage extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockModOreStorage() {
        super(Material.rock);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setHardness(3F);
        setResistance(5F);
        setStepSound(Block.soundTypeStone);
        setBlockName(Constants.UnLocalisedNames.ORE_STORAGE_BLOCKS);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        switch (meta) {

            default:
                return Item.getItemFromBlock(this);
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        icons = new IIcon[Constants.Icons.ORE_STORAGE_BLOCKS.length];

        for (int i = 0; i < Constants.Icons.ORE_STORAGE_BLOCKS.length; i++) {
            icons[i] = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.ORE_STORAGE_BLOCKS[i]);
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[meta];
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List par3List) {

        for (int i = 0; i < Constants.Icons.ORE_STORAGE_BLOCKS.length; i++) {
            par3List.add(new ItemStack(Item.getItemFromBlock(this), 1, i));
        }
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        switch (meta) {
            default:
                return 1;
        }
    }

}
