package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.items.ModItems;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
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

public class BlockModOre extends BlockOre {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockModOre() {
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setHardness(3F);
        setResistance(5F);
        setStepSound(Block.soundTypeStone);
        setBlockName(Constants.UnLocalisedNames.ORE);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        switch (meta) {
            // ruby
            case 0:
                return ModItems.items;
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
        icons = new IIcon[Constants.Icons.ORES.length];

        for (int i = 0; i < Constants.Icons.ORES.length; i++) {
            icons[i] = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.ORES[i]);
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[meta];
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < Constants.Icons.ORES.length; i++) {
            par3List.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        switch (meta) {
            case 0:
                return ((2 + random.nextInt(3)) * (1 + fortune));
            default:
                return 1;
        }
    }
}