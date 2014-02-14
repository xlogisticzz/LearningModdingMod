package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.tileEntites.TileEntityNoteSequencer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockNoteSequencer extends BlockContainer {

    public BlockNoteSequencer() {
        super(Material.circuits);

        setStepSound(soundTypeMetal);
        setHardness(3F);
        setBlockName(Constants.UnLocalisedNames.NOTE_SEQUENCER);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        blockIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.NOTE_SEQUENCER);
    }


    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote) {
            if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
                TileEntityNoteSequencer tile = (TileEntityNoteSequencer) world.getTileEntity(x, y, z);
                tile.active = true;
            } else {
                TileEntityNoteSequencer tile = (TileEntityNoteSequencer) world.getTileEntity(x, y, z);
                tile.active = false;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityNoteSequencer();
    }
}
