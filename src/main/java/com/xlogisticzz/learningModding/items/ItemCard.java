package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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

public class ItemCard extends Item {

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public ItemCard(int par1) {

        super(par1);
        this.setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        this.setHasSubtypes(true);

    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {

        return Constants.UnLocalisedNames.CARD + itemstack.getItemDamage();

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {

        this.icons = new Icon[Constants.Icons.CARD_ICONS.length];

        for (int i = 0; i < this.icons.length; i++) {
            this.icons[i] = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.CARD_ICONS[i]);

        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int damage) {

        return this.icons[damage];
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tab, List list) {

        for (int i = 0; i < Constants.ItemNames.CARD.length; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

        if (!world.isRemote && world.getBlockId(x, y, z) == ModBlocks.machineblock.blockID) {
            int meta = world.getBlockMetadata(x, y, z);

            int disabled = meta % 2;

            int type = stack.getItemDamage() + 1;

            int newMeta = type * 2 + disabled;

            world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);

            if (!player.capabilities.isCreativeMode) {
                stack.stackSize--;
            }
            return true;
        } else {
            return false;
        }
    }
}
