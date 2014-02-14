package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.entities.EntityLaunched;
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
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemEntityLauncher extends Item {

    public ItemEntityLauncher() {
        super();
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setUnlocalizedName(Constants.UnLocalisedNames.ENTITY_LAUNCHER);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        itemIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.ENTITY_LAUNCHER);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            EntityLaunched launched = new EntityLaunched(world);
            launched.setlaunchPos(x, y, z);
            world.spawnEntityInWorld(launched);
            if (!player.capabilities.isCreativeMode) {
                stack.stackSize--;
            }
            return true;
        } else {
            return false;
        }
    }
}
