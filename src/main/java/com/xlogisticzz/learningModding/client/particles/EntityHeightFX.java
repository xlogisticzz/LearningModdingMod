package com.xlogisticzz.learningModding.client.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

import com.xlogisticzz.learningModding.blocks.ModBlocks;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityHeightFX extends EntityFX {
    
    public EntityHeightFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
    
        super(world, x, y, z, motionX, motionY, motionZ);
        
        this.setParticleIcon(ModBlocks.particleBlock.ParticleIcon);
        
        this.motionY = motionY;
        setColor();
        
    }
    
    /*
     * (non-Javadoc)
     * @see net.minecraft.client.particle.EntityFX#onUpdate()
     */
    @Override
    public void onUpdate() {
    
        super.onUpdate();
        
        setColor();
        
    }
    
    private void setColor() {
    
        this.particleRed = (float) Math.min(1, this.posY / 86);
        this.particleGreen = (float) Math.max(0, Math.min(1, (this.posY - 86) / 86));
        this.particleBlue = (float) Math.max(0, Math.min(1, (this.posY - 172) / 86));
        
    }
    
    /*
     * (non-Javadoc)
     * @see net.minecraft.client.particle.EntityFX#getFXLayer()
     */
    @Override
    public int getFXLayer() {
    
        return 1;
    }
}
