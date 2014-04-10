package com.xlogisticzz.learningModding.tileEntites;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCakeStorage extends TileEntity implements IInventory {

    private ItemStack[] items;
    public byte currentDir;
    private int timer;
    private int delay;
    private int buffer;
    private int bufferMax;
    private int timerMax;
    private boolean place;
    private String customName;

    public TileEntityCakeStorage() {
        items = new ItemStack[10];
        currentDir = 0;
        bufferMax = 6;
        timerMax = 48;
        place = true;
    }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (++delay == 5) {
                if (++timer >= timerMax) {
                    updateBuffer();
                    if(place){
                        dispenseCake();
                    }else{
                        eatCake();
                    }
                    timer = 0;
                }
                delay = 0;
            }
        }
    }

    public int getTimerMax() {
        return timerMax;
    }

    public void decreaseMaxTime(boolean isShifted, boolean isControl) {
        if (isControl) {
            setTimerMax(getTimerMax() - 64);
            return;
        }
        if (isShifted) {
            setTimerMax(getTimerMax() - 16);
            return;
        }
        setTimerMax(getTimerMax() - 1);
    }

    public void increaseMaxTime(boolean isShifted, boolean isControl) {
        if (isControl) {
            setTimerMax(getTimerMax() + 64);
            return;
        }
        if (isShifted) {
            setTimerMax(getTimerMax() + 16);
            return;
        }
        setTimerMax(getTimerMax() + 1);
    }

    public void setTimerMax(int timerMax) {
        if (timerMax < 1) {
            this.timerMax = 1;
            return;
        }
        this.timerMax = timerMax;
    }

    public void decreaseMaxBuffer(boolean isShifted, boolean isControl) {
        if (isControl) {
            setBufferMax(getBufferMax() - 64);
            return;
        }
        if (isShifted) {
            setBufferMax(getBufferMax() - 16);
            return;
        }
        setBufferMax(getBufferMax() - 1);
    }

    public void increaseMaxBuffer(boolean isShifted, boolean isControl) {
        if (isControl) {
            setBufferMax(getBufferMax() + 64);
            return;
        }
        if (isShifted) {
            setBufferMax(getBufferMax() + 16);
            return;
        }
        setBufferMax(getBufferMax() + 1);
    }

    public int getBufferMax() {
        return bufferMax;
    }

    public void setBufferMax(int bufferMax) {
        if (bufferMax < getBuffer()) {
            this.bufferMax = getBuffer();
            return;
        }
        if (bufferMax < 1) {
            this.bufferMax = 1;
            return;
        }
        this.bufferMax = bufferMax;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);

        NBTTagList items = par1NBTTagCompound.getTagList("Items", 10);

        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound item = items.getCompoundTagAt(i);
            int slot = item.getByte("Slot");

            if (slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }
        currentDir = par1NBTTagCompound.getByte("currentDir");
        buffer = par1NBTTagCompound.getByte("Buffer");
        timer = par1NBTTagCompound.getByte("Timer");
        timerMax = par1NBTTagCompound.getInteger("TimerMax");
        bufferMax = par1NBTTagCompound.getInteger("BufferMax");
        place = par1NBTTagCompound.getBoolean("Place");

        if (par1NBTTagCompound.hasKey("CustomName", 8)) {
            customName = par1NBTTagCompound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);

        NBTTagList items = new NBTTagList();

        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack stack = getStackInSlot(i);
            if (stack != null) {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", (byte) i);
                stack.writeToNBT(item);
                items.appendTag(item);
            }
        }
        par1NBTTagCompound.setTag("Items", items);
        par1NBTTagCompound.setByte("currentDir", currentDir);
        par1NBTTagCompound.setByte("Timer", (byte) timer);
        par1NBTTagCompound.setByte("Buffer", (byte) buffer);
        par1NBTTagCompound.setInteger("TimerMax", timerMax);
        par1NBTTagCompound.setInteger("BufferMax", bufferMax);
        par1NBTTagCompound.setBoolean("Place", place);

        if (hasCustomInventoryName()) {
            par1NBTTagCompound.setString("CustomName", this.customName);
        }
    }


    @Override
    public int getSizeInventory() {
        return items.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return items[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        ItemStack stack = getStackInSlot(i);
        if (stack != null) {
            if (stack.stackSize <= j) {
                setInventorySlotContents(i, null);
            } else {
                stack = stack.splitStack(i);
                markDirty();
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        ItemStack stack = getStackInSlot(i);
        setInventorySlotContents(i, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        items[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }
        markDirty();
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.cakeStorage";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }


    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        if (Block.getBlockFromItem(itemstack.getItem()) == Blocks.cake) {
            return true;
        } else
            return itemstack.getItem() == Items.cake;
    }

    public void reciveButtonEvent(byte buttonId, boolean isShifted, boolean isControl) {
        switch (buttonId) {
            case 0:
                if (getCake() > 0) {
                    dispenseCake();
                }
                break;
            case 1:
                increaseDir();
                System.out.println("incresaed dir to  " + getCurrentDir());
                break;
            case 2:
                decreaseMaxTime(isShifted, isControl);
                break;
            case 3:
                increaseMaxTime(isShifted, isControl);
                break;
            case 4:
                decreaseMaxBuffer(isShifted, isControl);
                break;
            case 5:
                increaseMaxBuffer(isShifted, isControl);
                break;
            case 6:
                setPlace(!getPlace());
        }
    }

    private int cake = -1;

    public int getCake() {
        if (cake == -1) {
            calculateCakeCount();
        }
        return cake;
    }

    private void calculateCakeCount() {
        cake = 0;
        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack stack = getStackInSlot(i);
            if (stack != null && isItemValidForSlot(i, stack)) {
                cake += stack.stackSize;
            }
        }
    }

    @Override
    public void markDirty() {
        super.markDirty();

        cake = -1;
        updateBuffer();
    }

    public byte getCurrentDir() {
        return currentDir;
    }

    public String getCurrentTextDir() {
        String str;
        switch (getCurrentDir()) {
            case 0:
                str = "Up";
                break;
            case 1:
                str = "Down";
                break;
            case 2:
                str = "North";
                break;
            case 3:
                str = "South";
                break;
            case 4:
                str = "East";
                break;
            case 5:
                str = "West";
                break;
            default:
                str = "error";
                break;
        }
        return str;
    }

    public boolean placeCakeInCurrentDir(boolean accountAir, int meta) {
        boolean state = false;
        if (accountAir && !isAirInCurrentDir()) {
            state = false;
        } else {
            switch (getCurrentDir()) {
                case 0:
                    if (worldObj.setBlock(xCoord, yCoord + 1, zCoord, Blocks.cake, meta, 2)) {
                        state = true;
                    }
                    break;
                case 1:
                    if (worldObj.setBlock(xCoord, yCoord - 1, zCoord, Blocks.cake, meta, 2)) {
                        state = true;
                    }
                    break;
                case 2:
                    if (worldObj.setBlock(xCoord, yCoord, zCoord - 1, Blocks.cake, meta, 2)) {
                        state = true;
                    }
                    break;
                case 3:
                    if (worldObj.setBlock(xCoord, yCoord, zCoord + 1, Blocks.cake, meta, 2)) {
                        state = true;
                    }
                    break;
                case 4:
                    if (worldObj.setBlock(xCoord + 1, yCoord, zCoord, Blocks.cake, meta, 2)) {
                        state = true;
                    }
                    break;
                case 5:
                    if (worldObj.setBlock(xCoord - 1, yCoord, zCoord, Blocks.cake, meta, 2)) {
                        state = true;
                    }
                    break;
            }
        }

        if (!state) {
            System.out.println("could not place " + getCurrentDir());
        } else {
            System.out.println("placed cake " + getCurrentDir());
        }
        return state;
    }

    public boolean setAirInCurrentDir() {
        boolean state = false;
            switch (getCurrentDir()) {
                case 0:
                    if (worldObj.setBlock(xCoord, yCoord + 1, zCoord, Blocks.air, 0, 2)) {
                        state = true;
                    }
                    break;
                case 1:
                    if (worldObj.setBlock(xCoord, yCoord - 1, zCoord, Blocks.air, 0, 2)) {
                        state = true;
                    }
                    break;
                case 2:
                    if (worldObj.setBlock(xCoord, yCoord, zCoord - 1, Blocks.air, 0, 2)) {
                        state = true;
                    }
                    break;
                case 3:
                    if (worldObj.setBlock(xCoord, yCoord, zCoord + 1, Blocks.air, 0, 2)) {
                        state = true;
                    }
                    break;
                case 4:
                    if (worldObj.setBlock(xCoord + 1, yCoord, zCoord, Blocks.air, 0, 2)) {
                        state = true;
                    }
                    break;
                case 5:
                    if (worldObj.setBlock(xCoord - 1, yCoord, zCoord, Blocks.air, 0, 2)) {
                        state = true;
                    }
                    break;

        }

        if (!state) {
            System.out.println("could not set air " + getCurrentDir());
        } else {
            System.out.println("set air " + getCurrentDir());
        }
        return state;
    }

    public void increaseDir() {
        if (getCurrentDir() == 5) {
            currentDir = 0;
        } else {
            currentDir = (byte) (currentDir + 1);
        }
    }

    public boolean isAirInCurrentDir() {
        return getBlockAtCurrentPos() == Blocks.air;
    }

    public Block getBlockAtCurrentPos() {
        Block block = Blocks.air;
        switch (getCurrentDir()) {
            case 0:
                block = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
                break;
            case 1:
                block = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
                break;
            case 2:
                block = worldObj.getBlock(xCoord, yCoord, zCoord - 1);
                break;
            case 3:
                block = worldObj.getBlock(xCoord, yCoord, zCoord + 1);
                break;
            case 4:
                block = worldObj.getBlock(xCoord + 1, yCoord, zCoord);
                break;
            case 5:
                block = worldObj.getBlock(xCoord - 1, yCoord, zCoord);
                break;

        }
        return block;

    }

    public int getMetaAtCurrentPos() {
        int id = 0;
        if (isAirInCurrentDir()) {
            return id;
        } else {
            switch (getCurrentDir()) {
                case 0:
                    id = worldObj.getBlockMetadata(xCoord, yCoord + 1, zCoord);
                    break;
                case 1:
                    id = worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord);

                    break;
                case 2:
                    id = worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 1);

                    break;
                case 3:
                    id = worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 1);
                    break;
                case 4:
                    id = worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord);

                    break;
                case 5:
                    id = worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord);
                    break;

            }
            return id;
        }
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getBuffer() {
        return buffer;
    }

    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }

    public boolean getPlace() {
        return place;
    }

    public void setPlace(boolean place) {
        this.place = place;
    }

    public void eatCake() {
        if (getBlockAtCurrentPos() == Blocks.cake) {
            int meta = getMetaAtCurrentPos();
            System.out.print("Meta: " + meta + " ");
            int empty = getBufferMax() - getBuffer();
            if (meta > -1 && meta < 6) {
                int slices = 6 - meta;
                int fill = Math.min(empty, slices);
                System.out.print("Slices: " + slices + " ");
                System.out.print("Empty: " + empty + " ");
                System.out.print("Fill: " + fill + " ");
                if (fill == slices) {
                    setAirInCurrentDir();
                    setBuffer(getBuffer() + slices);
                } else {
                    System.out.print("set: " + (getBuffer() + empty));
                    System.out.print("setMeta: " + (meta - empty));
                    placeCakeInCurrentDir(false, 6 - (slices - empty));
                    setBuffer(getBuffer() + empty);
                }
            }
        }
    }
    private void dispenseCake() {
        if (getBuffer() > 0) {
            int missing = 0;
            if (isAirInCurrentDir()) {
                missing = 6;
            } else if (getBlockAtCurrentPos() == Blocks.cake) {
                missing = getMetaAtCurrentPos();
            }

            if (missing > 0) {
                int generate = Math.min(missing, getBuffer());
                setBuffer(getBuffer() - generate);
                updateBuffer();
                if (generate < missing && getBuffer() > 0) {
                    setBuffer(getBuffer() - (missing - generate));
                    generate = missing;
                }

                if (generate > 0) {
                    placeCakeInCurrentDir(false, missing - generate);
                }
            }
        }
    }

    private void removeCake() {
        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack stack = getStackInSlot(i);
            if (stack != null && isItemValidForSlot(i, stack)) {
                setInventorySlotContents(i, null);
                break;
            }
        }
    }

    private void updateBuffer() {
        if (getCake() == 0) {
            return;
        }
        int empty = getBufferMax() - getBuffer();
        double emptyCakes = Math.floor(empty / 6D);
        System.out.print(emptyCakes);
        if (getCake() < emptyCakes) {
            buffer = buffer + (getCake() * 6);
            for (int i = 0; i < getCake(); i++) {
                removeCake();
            }
        }
        if (emptyCakes > 0) {
            double fill = Math.min(getCake(), emptyCakes);
            for (int i = 0; i < fill; i++) {
                removeCake();
            }
            buffer = buffer + (int) (fill * 6);
        }
    }
}
