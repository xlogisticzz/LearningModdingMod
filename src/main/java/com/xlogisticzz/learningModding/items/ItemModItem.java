package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import java.util.List;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemModItem extends Item {

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public ItemModItem(int par1) {

        super(par1);
        this.setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {

        return Constants.UnLocalisedNames.ITEMS + itemstack.getItemDamage();

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {

        this.icons = new Icon[Constants.Icons.ITEMS.length];

        for (int i = 0; i < this.icons.length; i++) {
            this.icons[i] = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.ITEMS[i]);

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

        for (int i = 0; i < Constants.ItemNames.ITEMS_NAME.length; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }
}
