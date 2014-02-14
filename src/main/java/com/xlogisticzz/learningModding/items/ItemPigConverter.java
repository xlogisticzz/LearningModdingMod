package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.entities.EntityPigConverter;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemPigConverter extends Item {

    public ItemPigConverter() {

        super();
        setMaxStackSize(16);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setUnlocalizedName(Constants.UnLocalisedNames.PIG_CONVERTER);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        itemIcon = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.PIG_CONVERTER);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
        }
        if (!world.isRemote) {
            world.spawnEntityInWorld(new EntityPigConverter(world, player));
        }
        return stack;
    }

}
