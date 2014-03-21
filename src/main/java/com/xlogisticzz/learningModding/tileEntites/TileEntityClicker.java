package com.xlogisticzz.learningModding.tileEntites;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class TileEntityClicker extends TileEntity {

    private int clickOffset = 0;

    public void increment(boolean isAttackButton) {
        if (isAttackButton) {
            clickOffset--;
        } else {
            clickOffset++;
        }
        if (clickOffset >= 9) {
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 15, 3);
        } else if (this.clickOffset <= -7) {
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
        }else{
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 7 + clickOffset, 3);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("ClickOffset", this.clickOffset);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.clickOffset = compound.getInteger("ClickOffset");
    }
}
