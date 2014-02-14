package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemModArmor extends ItemArmor {

    public String texturePath = Constants.Mod.MODID + ":";
    public String iconPath = Constants.Mod.MODID + ":";

    public ItemModArmor(ArmorMaterial material, int renderIndex, int armorType, String type) {
        super(material, renderIndex, armorType);
        setMaxStackSize(1);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        SetArmorType(type, armorType);
    }

    // 0 = helmet
    // 1 = chestplate
    // 2 = leggings
    // 3 = boots
    private void SetArmorType(String type, int par4) {
        switch (par4) {
            case 0:
                setUnlocalizedName(type + "Helmet");
                texturePath += type + "_layer_1.png";
                iconPath += type + "_helmet";
                break;
            case 1:
                setUnlocalizedName(type + "Chest");
                texturePath += type + "_layer_1.png";
                iconPath += type + "_chestplate";
                break;
            case 2:
                setUnlocalizedName(type + "Leggings");
                texturePath += type + "_layer_2.png";
                iconPath += type + "_leggings";
                break;
            case 3:
                setUnlocalizedName(type + "Boots");
                texturePath += type + "_layer_1.png";
                iconPath += type + "_boots";
                break;
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return texturePath;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        itemIcon = reg.registerIcon(iconPath);
    }
}
