package com.xlogisticzz.learningModding.tileEntites;

import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.lib.Ids;
import net.minecraft.tileentity.TileEntity;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class TileEntityNumbers extends TileEntity {

    private int number;
    private int tick;

    @Override
    public void updateEntity() {

        if (!this.worldObj.isRemote && ++this.tick == 20) {
            this.number = (this.number + 1) % Constants.Misc.NUMBER_COUNT;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, Ids.NUMBER, 1, this.number);
            this.tick = 0;
        }
    }

    @Override
    public boolean receiveClientEvent(int id, int value) {

        if (this.worldObj.isRemote && id == 1) {
            this.number = value;
            this.worldObj.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
        return true;
    }

    public int getNumber() {

        return this.number;
    }

}
