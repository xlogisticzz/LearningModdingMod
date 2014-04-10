package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.utils.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * @author xLoGisTicZz
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemTestWand extends Item {

    public ItemTestWand() {
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setUnlocalizedName(Constants.UnLocalisedNames.TEST_WAND);
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        itemIcon = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.TEST_WAND);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        ChatComponentText component = new ChatComponentText("Block type: " + world.getBlock(x, y, z).getLocalizedName() + " Meta: " + world.getBlockMetadata(x, y, z) + " X:  " + x + " Y: " + y + " Z: " + z + " HitX: " + hitX + " HitY: " + hitY + " HitZ: " + hitZ);
        player.addChatComponentMessage(component);
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase livingBase) {
        if (livingBase.getHeldItem() == null) {
            ChatComponentText component = new ChatComponentText("ID: " + livingBase.getEntityId() + " Name: " + livingBase.getCommandSenderName() + " Age: " + livingBase.getAge() + " Potion Effects: " + livingBase.getActivePotionEffects() + " Current Health: " + livingBase.getHealth() + " Max Health: " + livingBase.getMaxHealth());
            player.addChatComponentMessage(component);
            return true;
        }
        ChatComponentText component = new ChatComponentText("ID: " + livingBase.getEntityId() + " Name: " + livingBase.getCommandSenderName() + " Age: " + livingBase.getAge() + " Potion Effects: " + livingBase.getActivePotionEffects() + " Current Health: " + livingBase.getHealth() + " Max Health: " + livingBase.getMaxHealth() + " Held item: " + StringUtils.localize(livingBase.getHeldItem().getItem().getUnlocalizedName()));
        player.addChatComponentMessage(component);
        return true;
    }
}
