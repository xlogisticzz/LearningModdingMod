package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.entities.EntityBlockEntityTeleport;
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
public class ItemEntityBlockTeleport extends Item {

    public ItemEntityBlockTeleport() {
        super();
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setUnlocalizedName(Constants.UnLocalisedNames.ENTITY_BLOCK_TELEPORTER);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        itemIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.ENTITY_BLOCK_TELEPORTER);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
        }
        if (!world.isRemote) {
            EntityBlockEntityTeleport launched = new EntityBlockEntityTeleport(world, player);
            launched.setLaunchPos(player.posX, player.posY, player.posZ);
            world.spawnEntityInWorld(launched);
        }
        return stack;
    }
}
