package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author xLoGisTicZz
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemSpawnLighting extends Item {

    public ItemSpawnLighting() {
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setUnlocalizedName(Constants.UnLocalisedNames.SPAWN_LIGHTING);
        setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        EntityLightningBolt lightning = new EntityLightningBolt(world, x, world.getPrecipitationHeight(x, z), z);
        world.spawnEntityInWorld(lightning);

        return false;
    }

}
