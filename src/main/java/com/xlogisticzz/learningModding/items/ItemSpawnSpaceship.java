package com.xlogisticzz.learningModding.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.entities.EntitySpaceship;
import com.xlogisticzz.learningModding.lib.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ItemSpawnSpaceship extends Item {
    
    public ItemSpawnSpaceship(int par1) {
    
        super(par1);
        this.setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        this.setUnlocalizedName(Constants.UnLocalisedNames.SPAWN_SPACESHIP);
        this.setMaxStackSize(1);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
    
        this.itemIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.SPAWN_SPACESHIP);
    }
    
    private boolean isCharged(int dmg) {
    
        return dmg >= 5;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List info, boolean useExtraInformation) {
    
        if (isCharged(itemstack.getItemDamage())){
            info.add("This item is charged");
        }
    }
    
    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    
        if (!world.isRemote){
            EntitySpaceship ship = new EntitySpaceship(world);
            
            ship.posX = x + 0.5;
            ship.posY = y + 1.5;
            ship.posZ = z + 0.5;
            
            if (isCharged(stack.getItemDamage())){
                ship.setCharged(true);
                
                stack.setItemDamage(0);
            }else{
                stack.setItemDamage(stack.getItemDamage() + 1);
            }
            
            world.spawnEntityInWorld(ship);
            
            return true;
        }else{
            return false;
        }
        
    }
    
}
