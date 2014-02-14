package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemModPickaxe extends ItemPickaxe {

    private String textureName;

    public ItemModPickaxe(ToolMaterial toolMaterial, float efficencyOnMaterial, String name, String texture, int maxDamage) {
        super(toolMaterial);
        efficiencyOnProperMaterial = efficencyOnMaterial;
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setMaxStackSize(1);
        setUnlocalizedName(name);
        textureName = texture;
        setMaxDamage(maxDamage);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        itemIcon = reg.registerIcon(Constants.Mod.MODID + ":" + textureName);
    }
}
