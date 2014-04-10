package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.client.sounds.SoundHandler;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemRubyWand extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon chargedIcon;

    public ItemRubyWand() {
        super();
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setMaxStackSize(1);
        setUnlocalizedName(Constants.UnLocalisedNames.RUBY_WAND);
        setFull3D();
        setMaxDamage(6);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase target) {
        if (!target.worldObj.isRemote) {
            target.motionY = 2;
            if (isCharged(itemstack.getItemDamage())) {
                target.motionX = (target.posX - player.posX) * 2;
                target.motionZ = (target.posZ - player.posZ) * 2;
                itemstack.setItemDamage(0);
                SoundHandler.playOnEntity(player, "wand", 1, 3);
            } else {
                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                SoundHandler.playOnEntity(player, "wand", 1, 0);
            }
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        itemIcon = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.RUBY_WAND);
        chargedIcon = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.RUBY_WAND_CHARGED);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List info, boolean useExtraInfo) {
        switch (itemstack.getItemDamage()) {
            case 1:
                info.add("This wand is 20% charged");
                break;
            case 2:
                info.add("This wand is 40% charged");
                break;
            case 3:
                info.add("This wand is 60% charged");
                break;
            case 4:
                info.add("This wand is 80% charged");
                break;
            case 5:
                info.add("This wand is 100% charged");
                break;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int dmg) {
        if (isCharged(dmg)) {
            return chargedIcon;
        } else {
            return itemIcon;
        }
    }

    private boolean isCharged(int dmg) {
        return dmg >= 5;
    }
}
