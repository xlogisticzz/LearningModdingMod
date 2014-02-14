package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemAxe;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemModAxe extends ItemAxe {

    private String textureName;

    public ItemModAxe(ToolMaterial toolMaterial, String unlocalisedName, String texture, int maxDamage) {

        super(toolMaterial);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setMaxDamage(maxDamage);
        setMaxStackSize(1);
        setUnlocalizedName(unlocalisedName);
        textureName = texture;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        itemIcon = reg.registerIcon(Constants.Mod.MODID + ":" + textureName);
    }
}
