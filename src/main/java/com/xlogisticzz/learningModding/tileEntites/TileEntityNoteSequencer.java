package com.xlogisticzz.learningModding.tileEntites;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityNoteSequencer extends TileEntity {

    private int timer;
    private int noteNumber;
    public boolean active = false;

    private static String[] music = {"harp", "bd", "snare", "hat", "bassattack"};

    /*
     * (non-Javadoc)
     * @see net.minecraft.tileentity.TileEntity#updateEntity()
     */
    @Override
    public void updateEntity() {

        if (!this.worldObj.isRemote && this.active) {
            if (++this.timer == 5) {
                int notePitch = this.noteNumber % 24;
                int instrument = this.noteNumber / 24;

                String currentInst = "note." + music[instrument];
                float pitch = (float) Math.pow(2.0D, (notePitch - 12) / 12D);

                worldObj.playSoundEffect(this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, currentInst, 1F, pitch);
                worldObj.spawnParticle("note", (double) xCoord + 0.5D, (double) yCoord + 1.2D, (double) zCoord + 0.5D, (double) noteNumber / 24.0D, 0.0D, 0.0D);


                this.noteNumber = (this.noteNumber + 1) % 120;
                this.timer = 0;

            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {

        super.readFromNBT(par1nbtTagCompound);

        this.timer = par1nbtTagCompound.getByte("Timer");
        this.noteNumber = par1nbtTagCompound.getByte("Note");
        this.active = par1nbtTagCompound.getBoolean("Active");

    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {

        super.writeToNBT(par1nbtTagCompound);

        par1nbtTagCompound.setByte("Timer", (byte) this.timer);
        par1nbtTagCompound.setByte("Note", (byte) this.noteNumber);
        par1nbtTagCompound.setBoolean("Active", this.active);

    }

}
