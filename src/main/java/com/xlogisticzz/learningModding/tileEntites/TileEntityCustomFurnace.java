package com.xlogisticzz.learningModding.tileEntites;

/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityCustomFurnace extends TileEntity implements ISidedInventory {

    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_bottom = new int[]{2, 1};
    private static final int[] slots_sides = new int[]{1};
    public ItemStack[] items;

    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;

    public TileEntityCustomFurnace() {
        items = new ItemStack[3];
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0 ? slots_bottom : (side == 1 ? slots_top : slots_sides);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
        return isItemValidForSlot(slot, itemstack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
        return side != 0 || slot != 1 || itemstack.itemID == Item.bucketEmpty.itemID;
    }

    @Override
    public int getSizeInventory() {
        return items.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return items[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amount);
                onInventoryChanged();
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        setInventorySlotContents(slot, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        items[slot] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }
        onInventoryChanged();
    }

    @Override
    public String getInvName() {
        return "Quartz Furnace";
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
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        return slot == 2 ? false : (slot == 1 ? isItemFuel(itemstack) : true);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
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
        compound.setTag("Items", items);
        compound.setShort("BurnTime", (short) this.furnaceBurnTime);
        compound.setShort("CookTime", (short) this.furnaceCookTime);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList items = compound.getTagList("Items");
        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound item = (NBTTagCompound) items.tagAt(i);
            int slot = item.getByte("Slot");
            if (slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }
        this.furnaceBurnTime = compound.getShort("BurnTime");
        this.furnaceCookTime = compound.getShort("CookTime");
        this.currentItemBurnTime = TileEntityFurnace.getItemBurnTime(this.items[1]);
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1) {
        return furnaceCookTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1) {
        if (currentItemBurnTime == 0) {
            currentItemBurnTime = 200;
        }
        return furnaceBurnTime * par1 / currentItemBurnTime;
    }

    public boolean isBurning() {
        return furnaceBurnTime > 0;
    }

    public void updateEntity() {
        boolean flag = furnaceBurnTime > 0;
        boolean flag1 = false;
        if (furnaceBurnTime > 0) {
            --furnaceBurnTime;
        }
        if (!worldObj.isRemote) {
            if (furnaceBurnTime == 0 && canSmelt()) {
                currentItemBurnTime = furnaceBurnTime = TileEntityFurnace.getItemBurnTime(items[1]);
                if (furnaceBurnTime > 0) {
                    flag1 = true;
                    if (items[1] != null) {
                        --items[1].stackSize;
                        if (items[1].stackSize == 0) {
                            items[1] = items[1].getItem().getContainerItemStack(items[1]);
                        }
                    }
                }
            }
            if (isBurning() && canSmelt()) {
                ++furnaceCookTime;
                if (furnaceCookTime == 200) {
                    furnaceCookTime = 0;
                    smeltItem();
                    flag1 = true;
                }
            } else {
                furnaceCookTime = 0;
            }
            if (flag != furnaceBurnTime > 0) {
                flag1 = true;
                int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
                if (furnaceBurnTime > 0) {
                    worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta + 4, 2);
                } else {
                    worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta - 4, 2);
                }
            }
        }
        if (flag1) {
            onInventoryChanged();
        }
    }

    private boolean canSmelt() {
        if (this.items[0] == null) {
            return false;
        } else {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[0]);
            if (itemstack == null) return false;
            if (this.items[2] == null) return true;
            if (!this.items[2].isItemEqual(itemstack)) return false;
            int result = items[2].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[0]);

            if (this.items[2] == null) {
                this.items[2] = itemstack.copy();
            } else if (this.items[2].isItemEqual(itemstack)) {
                items[2].stackSize += itemstack.stackSize;
            }

            --this.items[0].stackSize;

            if (this.items[0].stackSize <= 0) {
                this.items[0] = null;
            }
        }
    }


    public static boolean isItemFuel(ItemStack par0ItemStack) {
        return TileEntityFurnace.getItemBurnTime(par0ItemStack) > 0;
    }
}
