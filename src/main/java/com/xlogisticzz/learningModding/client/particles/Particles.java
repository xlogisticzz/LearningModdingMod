package com.xlogisticzz.learningModding.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityNoteFX;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum Particles {
    POISON, HEIGHT, NOTE;
    
    public void spawnParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
    
        Minecraft mc = Minecraft.getMinecraft();
        if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null){
            int particleSetting = mc.gameSettings.particleSetting;
            
            if (particleSetting == 2 || particleSetting == 1 && world.rand.nextInt(3) == 0){
                return;
            }
            
            double distanceX = mc.renderViewEntity.posX - x;
            double distanceY = mc.renderViewEntity.posY - y;
            double distanceZ = mc.renderViewEntity.posZ - z;
            
            double maxDistance = 16;
            
            if (distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ > maxDistance * maxDistance){
                return;
            }
            
            EntityFX particleEffect = null;
            
            switch (this) {
                case POISON :
                    particleEffect = new EntityPosionFX(world, x, y, z, motionX, motionY, motionZ);
                    break;
                case HEIGHT :
                    particleEffect = new EntityHeightFX(world, x, y, z, motionX, motionY, motionZ);
                    break;
                case NOTE :
                    particleEffect = new EntityNoteFX(world, x, y, z, motionX, motionY, motionZ);
                    break;
                default :
                    break;
            }
            
            if (particleEffect != null){
                mc.effectRenderer.addEffect(particleEffect);
            }
            
        }
        
    }
    
}
