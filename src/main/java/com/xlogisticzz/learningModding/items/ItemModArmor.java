package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
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

    public ItemModArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4, String type, CreativeTabs par5) {

        super(par1, par2EnumArmorMaterial, par3, par4);

        this.setMaxStackSize(1);
        this.setCreativeTab(par5);
        this.SetArmorType(type.toLowerCase(), par4);
    }

    // 0 = helmet
    // 1 = chestplate
    // 2 = leggings
    // 3 = boots
    private void SetArmorType(String type, int par4) {

        switch (par4) {
            case 0:
                this.setUnlocalizedName(type + "Helmet");
                this.texturePath += type + "_layer_1.png";
                this.iconPath += type + "_helmet";
                break;
            case 1:
                this.setUnlocalizedName(type + "Chest");
                this.texturePath += type + "_layer_1.png";
                this.iconPath += type + "_chestplate";
                break;
            case 2:
                this.setUnlocalizedName(type + "Leggings");
                this.texturePath += type + "_layer_2.png";
                this.iconPath += type + "_leggings";
                break;
            case 3:
                this.setUnlocalizedName(type + "Boots");
                this.texturePath += type + "_layer_1.png";
                this.iconPath += type + "_boots";
                break;
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {

        return this.texturePath;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {

        this.itemIcon = reg.registerIcon(this.iconPath);
    }
}
