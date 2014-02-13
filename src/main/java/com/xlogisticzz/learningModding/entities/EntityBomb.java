package com.xlogisticzz.learningModding.entities;

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.tileEntites.TileEntityBomb;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class EntityBomb extends Entity {

    public EntityBomb(World world) {

        super(world);

        this.motionY = -0.6;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {

    }

    @Override
    public void onUpdate() {

        super.onUpdate();

        if (!this.worldObj.isRemote) {
            if (this.worldObj.isAirBlock((int) this.posX, (int) this.posY, (int) this.posZ) && !this.worldObj.isAirBlock((int) this.posX, (int) this.posY - 1, (int) this.posZ)) {
                this.worldObj.setBlock((int) this.posX, (int) this.posY, (int) this.posZ, ModBlocks.bomb);
                TileEntityBomb tile = (TileEntityBomb) this.worldObj.getTileEntity((int) this.posX, (int) this.posY, (int) this.posZ);
                tile.setActive();
                this.setDead();

            }
        }

        this.setPosition(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
    }
}
