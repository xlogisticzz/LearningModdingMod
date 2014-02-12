package com.xlogisticzz.learningModding.tileEntites;

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.client.sounds.Sounds;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class TileEntityBomb extends TileEntity {

    private static final int SPREAD_TIME = 7;
    private static final int SPREAD_LEVELS = 30;

    private int timer;
    private int spreadLevel;
    private boolean activated = false;
    private boolean spreaded = false;

    public TileEntityBomb() {

        this.timer = SPREAD_TIME;
        this.spreadLevel = 0;
    }

    public boolean isBlockIdle() {

        return this.timer < 0;
    }

    public void setActive() {

        this.activated = true;
    }

    @Override
    public void updateEntity() {

        if (!this.worldObj.isRemote) {

            if (this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)) {
                this.activated = true;
            }
            if (this.activated && !this.spreaded) {
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 2, 3);
                if (this.spreadLevel == 0) {
                    this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 1, 3);
                }
            }
            if (this.timer == 0 && this.spreadLevel < SPREAD_LEVELS) {
                this.spreaded = true;
                spread(this.xCoord + 1, this.yCoord, this.zCoord);
                spread(this.xCoord - 1, this.yCoord, this.zCoord);
                spread(this.xCoord, this.yCoord, this.zCoord + 1);
                spread(this.xCoord, this.yCoord, this.zCoord - 1);
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 3, 3);
                Sounds.BOMB_SPREAD.play(this.xCoord, this.yCoord, this.zCoord, 1, 0);
            } else if (this.timer == SPREAD_TIME * (this.spreadLevel - SPREAD_LEVELS)) {
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 3, 3);
                this.worldObj.createExplosion(null, this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, 4, true);
            }

            if (this.activated) {
                this.timer--;
            }
        }
    }

    private void spread(int x, int y, int z) {

        if (this.worldObj.isAirBlock(x, y, z)) {
            this.worldObj.setBlock(x, y, z, ModBlocks.bomb);

            TileEntityBomb bomb = (TileEntityBomb) this.worldObj.getTileEntity(x, y, z);
            bomb.spreadLevel = this.spreadLevel + 1;
            bomb.activated = true;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound NBTTagCompound) {

        super.writeToNBT(NBTTagCompound);

        NBTTagCompound.setShort("Timer", (short) this.timer);
        NBTTagCompound.setByte("Level", (byte) this.spreadLevel);
        NBTTagCompound.setBoolean("Activated", this.activated);
        NBTTagCompound.setBoolean("Spreaded", this.spreaded);
    }

    @Override
    public void readFromNBT(NBTTagCompound NBTTagCompound) {

        super.readFromNBT(NBTTagCompound);

        this.timer = NBTTagCompound.getShort("Timer");
        this.spreadLevel = NBTTagCompound.getByte("Level");
        this.activated = NBTTagCompound.getBoolean("Activated");
        this.spreaded = NBTTagCompound.getBoolean("Spreaded");
    }

}
