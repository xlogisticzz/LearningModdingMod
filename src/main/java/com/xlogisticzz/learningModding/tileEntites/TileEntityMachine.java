package com.xlogisticzz.learningModding.tileEntites;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMachine extends TileEntity implements IInventory {

    public final boolean[] customSetup;
    private ItemStack[] items;
    private String customName;
    private int gravel = -1;
    private int customGravel = 0;

    public TileEntityMachine() {
        items = new ItemStack[3];
        customSetup = new boolean[49];
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
        return this.hasCustomInventoryName() ? this.customName : "container.machine";
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
        return Block.getBlockFromItem(itemstack.getItem()) == Blocks.gravel;
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

        for (int i = 0; i < customSetup.length; i++) {
            setCustomGravel(i, par1NBTTagCompound.getBoolean("Custom" + i));
        }

        if (par1NBTTagCompound.hasKey("CustomName", 8)) {
            this.customName = par1NBTTagCompound.getString("CustomName");
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

        for (int i = 0; i < customSetup.length; i++) {
            par1NBTTagCompound.setBoolean("Custom" + i, customSetup[i]);
        }

        if (this.hasCustomInventoryName()) {
            par1NBTTagCompound.setString("CustomName", this.customName);
        }
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

            default:
                buttonId -= 2;
                setCustomGravel(buttonId, !customSetup[buttonId]);
        }
    }

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
    public void markDirty() {
        super.markDirty();

        gravel = -1;
    }

    public int getCustomGravel() {
        return customGravel;
    }

    public void setCustomGravel(int i, boolean val) {
        boolean oldVal = customSetup[i];
        if (oldVal && !val) {
            customGravel--;
        } else if (!oldVal && val) {
            customGravel++;
        }
        customSetup[i] = val;
    }
}
