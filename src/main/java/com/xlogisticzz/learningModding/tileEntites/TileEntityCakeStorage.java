package com.xlogisticzz.learningModding.tileEntites;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCakeStorage extends TileEntity implements IInventory {

    private ItemStack[] items;
    public byte currentDir;

    public TileEntityCakeStorage() {
        items = new ItemStack[10];
        currentDir = 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);

        NBTTagList items = par1NBTTagCompound.getTagList("Items");

        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound item = (NBTTagCompound) items.tagAt(i);
            int slot = item.getByte("Slot");

            if (slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }
        currentDir = par1NBTTagCompound.getByte("currentDir");
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
                onInventoryChanged();
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
        onInventoryChanged();
    }

    @Override
    public String getInvName() {
        return "Cake Storage";
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
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
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        if (itemstack.itemID == Block.cake.blockID) {
            return true;
        } else return itemstack.itemID == Item.cake.itemID;
    }

    public void reciveButtonEvent(byte buttonId) {
        switch (buttonId) {
            case 0:
                if (getCake() > 0) {
                    placeCakeInCurrentDir();

                }
                break;

            case 1:
                increaseDir();
                System.out.println("incresaed dir to  " + getCurrentDir());

                break;

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
    public void onInventoryChanged() {
        super.onInventoryChanged();

        cake = -1;
    }

    public byte getCurrentDir() {
        return currentDir;
    }

    public String getCurrentTextDir(){
        String str;
        switch (currentDir){
            case 0 :
                str = "Up";
                break;
            case 1 :
                str = "Down";
                break;
            case 2 :
                str = "North";
                break;
            case 3 :
                str = "South";
                break;
            case 4 :
                str = "East";
                break;
            case 5 :
                str = "West";
                break;
            default:
                str = "error";
                break;
        }
        return str;
    }

    public boolean placeCakeInCurrentDir() {
        boolean state = false;
        if (!isAirInCurrentDir()) {
            state = false;
        } else {
            switch (getCurrentDir()) {
                case 0:
                    if (worldObj.setBlock(xCoord, yCoord + 1, zCoord, Block.cake.blockID, 0, 2)) {
                        state = true;
                    }
                    break;
                case 1:
                    if (worldObj.setBlock(xCoord, yCoord - 1, zCoord, Block.cake.blockID, 0, 2)) {
                        state = true;
                    }
                    break;
                case 2:
                    if (worldObj.setBlock(xCoord, yCoord, zCoord - 1, Block.cake.blockID, 0, 2)) {
                        state = true;
                    }
                    break;
                case 3:
                    if (worldObj.setBlock(xCoord, yCoord, zCoord + 1, Block.cake.blockID, 0, 2)) {
                        state = true;
                    }
                    break;
                case 4:
                    if (worldObj.setBlock(xCoord + 1, yCoord, zCoord, Block.cake.blockID, 0, 2)) {
                        state = true;
                    }
                    break;
                case 5:
                    if (worldObj.setBlock(xCoord - 1, yCoord, zCoord, Block.cake.blockID, 0, 2)) {
                        state = true;
                    }
                    break;
            }
        }
        if (state) {
            for (int i = 0; i < getSizeInventory(); i++) {
                ItemStack stack = getStackInSlot(i);
                if (stack != null && stack.itemID == Item.cake.itemID) {
                    decrStackSize(i, 1);
                    break;
                }
            }
        }
        if (!state) {
            System.out.println("could not place " + getCurrentDir());
        } else {
            System.out.println("placed cake " + getCurrentDir());
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
        //System.out.println("checking" + getCurrentDir());
        switch (getCurrentDir()) {
            case 0:
                if (worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == 0) {
                    return true;
                }
                break;
            case 1:
                if (worldObj.getBlockId(xCoord, yCoord - 1, zCoord) == 0) {
                    return true;
                }
                break;
            case 2:
                if (worldObj.getBlockId(xCoord, yCoord, zCoord - 1) == 0) {
                    return true;
                }
                break;
            case 3:
                if (worldObj.getBlockId(xCoord, yCoord, zCoord + 1) == 0) {
                    return true;
                }
                break;
            case 4:
                if (worldObj.getBlockId(xCoord + 1, yCoord, zCoord) == 0) {
                    return true;
                }
                break;
            case 5:
                if (worldObj.getBlockId(xCoord - 1, yCoord, zCoord) == 0) {
                    return true;
                }
                break;
        }
        return false;
    }

    public int getBlockIdAtCurrentPos() {
        int id = 0;

        if (isAirInCurrentDir()) {
            return id;
        } else {
            switch (getCurrentDir()) {
                case 0:
                    id = worldObj.getBlockId(xCoord, yCoord + 1, zCoord);
                    break;
                case 1:
                    id = worldObj.getBlockId(xCoord, yCoord - 1, zCoord);

                    break;
                case 2:
                    id = worldObj.getBlockId(xCoord, yCoord, zCoord - 1);

                    break;
                case 3:
                    id = worldObj.getBlockId(xCoord, yCoord, zCoord + 1);
                    break;
                case 4:
                    id = worldObj.getBlockId(xCoord + 1, yCoord, zCoord);

                    break;
                case 5:
                    id = worldObj.getBlockId(xCoord - 1, yCoord, zCoord);
                    break;

            }
            return id;
        }
    }
}
