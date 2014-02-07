package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.tileEntites.TileEntityNumbers;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
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

public class BlockNumber extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    protected BlockNumber() {

        super(Material.rock);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setHardness(1F);
        setStepSound(soundTypeStone);
        setBlockName(Constants.UnLocalisedNames.NUMBER);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {

        return new TileEntityNumbers();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {

        this.icons = new IIcon[Constants.Misc.NUMBER_COUNT];
        for (int i = 0; i < this.icons.length; i++) {
            this.icons[i] = register.registerIcon(Constants.Mod.MODID + ":numbers/" + Constants.Icons.NUMBERS[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {

        TileEntityNumbers tile = (TileEntityNumbers) world.getTileEntity(x, y, z);
        return this.icons[tile.getNumber()];
    }
}
