package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.items.ModItems;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import java.util.List;
import java.util.Random;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockModOre extends BlockOre {

    Icon[] icons;

    public BlockModOre(int par1) {
        super(par1);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setHardness(3F);
        setResistance(5F);
        setStepSound(Block.soundStoneFootstep);
        setUnlocalizedName(Constants.UnLocalisedNames.ORE);
    }

    @Override
    public int idDropped(int meta, Random rand, int fortune) {
        switch (meta) {
            // ruby
            case 0:
                return ModItems.ruby.itemID;


            default:
                return this.blockID;
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        icons = new Icon[Constants.Icons.ORES.length];

        for (int i = 0; i < Constants.Icons.ORES.length; i++) {
            icons[i] = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.ORES[i]);
        }
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return icons[meta];
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void getSubBlocks(int id, CreativeTabs par2CreativeTabs, List par3List) {

        for (int i = 0; i < Constants.Icons.ORES.length; i++) {
            par3List.add(new ItemStack(id, 1, i));
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