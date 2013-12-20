package com.xlogisticzz.learningModding.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.entities.EntityBlockEntityTeleport;
import com.xlogisticzz.learningModding.lib.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemEntityBlockTeleport extends Item {
    
    public ItemEntityBlockTeleport(int par1) {
    
        super(par1);
        
        this.setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        this.setUnlocalizedName(Constants.UnLocalisedNames.ENTITY_BLOCK_TELEPORTER);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
    
        this.itemIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.ENTITY_BLOCK_TELEPORTER);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
    
        if (!player.capabilities.isCreativeMode){
            --stack.stackSize;
        }
        if (!world.isRemote){
            EntityBlockEntityTeleport launched = new EntityBlockEntityTeleport(world, player);
            
            launched.setLaunchPos(player.posX, player.posY, player.posZ);
            
            world.spawnEntityInWorld(launched);
        }
        
        return stack;
    }
}
