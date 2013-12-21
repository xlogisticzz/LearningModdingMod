package com.xlogisticzz.learningModding.client.particles;

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class EntityPosionFX extends EntityFX {

    public EntityPosionFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {

        super(par1World, par2, par4, par6, par8, par10, par12);

        this.setParticleIcon(ModBlocks.poisonBlock.ParticleIcon);

        this.particleScale = this.rand.nextFloat();
        this.particleAlpha = this.rand.nextFloat();
        this.particleBlue = this.rand.nextFloat() * 0.5F;
        this.particleRed = this.rand.nextFloat() * 0.5F;
        this.particleGreen = this.rand.nextFloat() * 0.5F + 0.5F;

    }

    /*
     * (non-Javadoc)
     * @see net.minecraft.client.particle.EntityFX#onUpdate()
     */
    @Override
    public void onUpdate() {

        super.onUpdate();

        this.particleScale = (1 - (float) this.particleAge / this.particleMaxAge) * 2;

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
