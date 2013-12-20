package com.xlogisticzz.learningModding.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.entities.EntityPigConverter;
import com.xlogisticzz.learningModding.lib.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemPigConverter extends Item {
    
    public ItemPigConverter(int par1) {
    
        super(par1);
        this.setMaxStackSize(16);
        this.setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        this.setUnlocalizedName(Constants.UnLocalisedNames.PIG_CONVERTER);
        
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
    
        this.itemIcon = register.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.PIG_CONVERTER);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
    
        if (!player.capabilities.isCreativeMode){
            --stack.stackSize;
        }
        if (!world.isRemote){
            world.spawnEntityInWorld(new EntityPigConverter(world, player));
        }
        
        return stack;
    }
    
}
