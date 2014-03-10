package com.xlogisticzz.learningModding.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityLaunched extends Entity {

    private int startPosY;
    private boolean goingUp;

    public EntityLaunched(World par1World) {

        super(par1World);

    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

        this.startPosY = compound.getInteger("StartPosY");
        this.goingUp = compound.getBoolean("GoingUp");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

        compound.setInteger("StartPosY", this.startPosY);
        compound.setBoolean("GoingUp", this.goingUp);
    }

    public void setlaunchPos(double x, double y, double z) {

        this.startPosY = (int) y;
        this.goingUp = true;

        this.setPosition(x + 0.5, y, z + 0.5);
    }

    @Override
    public void onUpdate() {

        super.onUpdate();

        if (!this.worldObj.isRemote) {
            if (this.goingUp) {
                this.motionY = 0.4F;
                if (this.posY > this.startPosY + 30) {
                    this.goingUp = false;
                }
            } else {
                this.motionY = -1.8F;
                if (this.posY < this.startPosY) {
                    this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 6, true);
                    this.setDead();
                }
            }

        }
        setPosition(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
    }
}
