package com.xlogisticzz.learningModding.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityPigConverter extends EntityThrowable {
    
    public EntityPigConverter(World par1World) {
    
        super(par1World);
        
    }
    
    public EntityPigConverter(World par1World, EntityLivingBase par2EntityLivingBase) {
    
        super(par1World, par2EntityLivingBase);
        
    }
    
    public EntityPigConverter(World par1World, double par2, double par4, double par6) {
    
        super(par1World, par2, par4, par6);
        
    }
    
    @Override
    protected void onImpact(MovingObjectPosition position) {
    
        if (!this.worldObj.isRemote){
            if (position.entityHit != null && position.entityHit instanceof EntityPig){
                EntityPig pig = (EntityPig) position.entityHit;
                EntityPigZombie zombie = new EntityPigZombie(this.worldObj);
                
                zombie.setLocationAndAngles(pig.posX, pig.posY, pig.posZ, pig.rotationYaw, pig.rotationPitch);
                
                pig.setDead();
                this.worldObj.spawnEntityInWorld(zombie);
                
            }
            this.setDead();
        }
    }
}
