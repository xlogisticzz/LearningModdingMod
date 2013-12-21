package com.xlogisticzz.learningModding.tileEntites;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMachine extends TileEntity implements IInventory {

    private ItemStack[] items;

    public TileEntityMachine() {
        items = new ItemStack[3];
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
                stack = stack.splitStack(j);
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
        return "Machine Inventory";
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
        return itemstack.itemID == Block.gravel.blockID;
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
    }

    public void reciveButtonEvent(byte buttonId) {
        switch (buttonId) {
            case 0:
                int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
                int selectedType = metadata / 2;
                int isDisabled = metadata % 2 == 1 ? 0 : 1;
                int newMetadata = selectedType * 2 + isDisabled;
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, newMetadata, 3);
                break;

            case 1:
                int metadata2 = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata2 % 2, 3);

                ItemStack stack = new ItemStack(ModItems.card, 1, (metadata2 / 2) - 1);

                if (stack != null) {
                    float spawnX = xCoord + worldObj.rand.nextFloat();
                    float spawnY = yCoord + worldObj.rand.nextFloat();
                    float spawnZ = zCoord + worldObj.rand.nextFloat();

                    EntityItem droppedItem = new EntityItem(worldObj, spawnX, spawnY, spawnZ, stack);

                    float mult = 0.05F;

                    droppedItem.motionX = (-0.5F + worldObj.rand.nextFloat()) * mult;
                    droppedItem.motionX = (4 + worldObj.rand.nextFloat()) * mult;
                    droppedItem.motionX = (-0.5F + worldObj.rand.nextFloat()) * mult;

                    worldObj.spawnEntityInWorld(droppedItem);
                }
        }
    }

    private int gravel = -1;

    public int getGravel() {
        if (gravel == -1) {
            calculateGravelCount();
        }
        return gravel;
    }

    private void calculateGravelCount() {
        gravel = 0;
        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack stack = getStackInSlot(i);
            if (stack != null && isItemValidForSlot(i, stack)) {
                gravel += stack.stackSize;
            }
        }
    }

    @Override
    public void onInventoryChanged() {
        super.onInventoryChanged();

        gravel = -1;
    }
}
