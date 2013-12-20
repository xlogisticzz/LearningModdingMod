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
    
    public void attackButton(boolean isAttackButton) {
    
        if (isAttackButton){
            this.clickOffset = this.clickOffset - 1;
        }else{
            this.clickOffset = this.clickOffset + 1;
        }
        if (this.clickOffset >= 15){
            this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 15, 3);
        }else if (this.clickOffset <= -7){
            this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 3);
        }
        
        switch (this.clickOffset) {
            case -7 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 3);
                break;
            case -6 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 1, 3);
                break;
            case -5 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 2, 3);
                break;
            case -4 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 3, 3);
                break;
            case -3 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 4, 3);
                break;
            case -2 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 5, 3);
                break;
            case -1 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 6, 3);
                break;
            case 0 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 7, 3);
                break;
            case 1 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 8, 3);
                break;
            case 2 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 9, 3);
                break;
            case 3 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 10, 3);
                break;
            case 4 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 11, 3);
                break;
            case 5 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 12, 3);
                break;
            case 6 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 13, 3);
                break;
            case 7 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 14, 3);
                break;
            case 8 :
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 15, 3);
                break;
        
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
