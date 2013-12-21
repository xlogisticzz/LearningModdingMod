package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class BlockMarker extends Block {

    public Icon[] icons;

    public BlockMarker(int par1) {

        super(par1, Material.iron);
        this.setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        this.setHardness(2.5F);
        this.setUnlocalizedName(Constants.UnLocalisedNames.MARKERS);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {

        this.icons = new Icon[Constants.Icons.MARKERS.length];

        for (int i = 0; i < Constants.Icons.MARKERS.length; i++) {

            this.icons[i] = par1IconRegister.registerIcon(Constants.Mod.MODID + ":markers/" + Constants.Icons.MARKERS[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata) {

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
    public void getSubBlocks(int id, CreativeTabs par2CreativeTabs, List par3List) {

        for (int i = 0; i < Constants.Icons.MARKERS.length / 2; i++) {
            par3List.add(new ItemStack(id, 1, i * 2));
        }
    }
}
