package com.xlogisticzz.learningModding.entities;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.xlogisticzz.learningModding.LearningModding;
import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.client.SpaceshipInventoryKeyBind;
import com.xlogisticzz.learningModding.client.sounds.Sounds;
import com.xlogisticzz.learningModding.network.PacketHandler;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class EntitySpaceship extends Entity implements IEntityAdditionalSpawnData, IInventory {

    private boolean charged;

    public EntitySpaceship(World world) {

        super(world);
        this.setSize(1.5F, 0.6F);
        items = new ItemStack[10];
    }

    @Override
    protected void entityInit() {

        this.dataWatcher.addObject(15, 10);
    }

    public boolean isCharged() {

        return this.charged;
    }

    public void setCharged(boolean charge) {

        this.charged = charge;
    }

    @Override
    public AxisAlignedBB getBoundingBox() {

        return this.boundingBox;
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity) {

        if (entity != this.riddenByEntity) {
            return entity.getBoundingBox();
        } else {
            return null;
        }
    }

    @Override
    public boolean canBePushed() {

        return true;
    }

    @Override
    public boolean canBeCollidedWith() {

        return !this.isDead;
    }

    @Override
    public boolean interactFirst(EntityPlayer player) {

        if (!this.worldObj.isRemote && this.riddenByEntity == null) {
            player.mountEntity(this);

        }
        return true;
    }

    @Override
    public double getMountedYOffset() {

        return -0.15;
    }

    @Override
    public void onUpdate() {

        super.onUpdate();

        if (!this.worldObj.isRemote) {
            if (this.riddenByEntity == null && this.worldObj.isAirBlock((int) this.posX, (int) this.posY - 1, (int) this.posZ)) {
                this.motionY = -0.3F;
            } else {
                this.motionY = 0F;
            }
        } else {
            sendInfo();
        }

        this.motionX = this.motionX / 1.1;
        this.motionY = this.motionY / 1.1;
        this.motionZ = this.motionZ / 1.1;

        this.setPosition(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

    }

    private boolean lastPressedBombState;

    private void sendInfo() {
        boolean bombState = Minecraft.getMinecraft().gameSettings.keyBindAttack.pressed;
        if (bombState && !this.lastPressedBombState && this.charged && this.riddenByEntity == Minecraft.getMinecraft().thePlayer) {
            boolean hasAmmo = false;
            for (int i = 0; i < getSizeInventory(); i++) {
                ItemStack stack = getStackInSlot(i);
                if (stack != null && stack.itemID == ModBlocks.bomb.blockID) {
                    hasAmmo = true;
                    break;
                }
            }
            if (!hasAmmo) {
                Minecraft.getMinecraft().thePlayer.addChatMessage("You don't have enough ammo left");
                Sounds.OUT_OF_AMMO.play(this.posX, this.posY, this.posZ, 1, 0);
            } else {
                PacketHandler.sendShipPacket(this, 0);
            }
        }
        this.lastPressedBombState = bombState;

        if (SpaceshipInventoryKeyBind.keyPressed) {
            SpaceshipInventoryKeyBind.keyPressed = false;
            if (this.charged && this.riddenByEntity == Minecraft.getMinecraft().thePlayer) {
                PacketHandler.sendShipPacket(this, 1);
            }
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

        this.charged = compound.getBoolean("charged");

        NBTTagList items = compound.getTagList("Items");

        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound item = (NBTTagCompound) items.tagAt(i);
            int slot = item.getByte("Slot");

            if (slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

        compound.setBoolean("charged", this.charged);

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
    }

    @Override
    public void writeSpawnData(ByteArrayDataOutput data) {

        data.writeBoolean(this.charged);
    }

    @Override
    public void readSpawnData(ByteArrayDataInput data) {

        this.charged = data.readBoolean();
    }

    public void dropBomb() {
        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack stack = getStackInSlot(i);
            if (stack != null && stack.itemID == ModBlocks.bomb.blockID) {
                decrStackSize(i, 1);
                EntityBomb bomb = new EntityBomb(this.worldObj);

                bomb.posX = this.posX;
                bomb.posY = this.posY;
                bomb.posZ = this.posZ;

                this.worldObj.spawnEntityInWorld(bomb);
                Sounds.BOMB_DROP.play(this.posX, this.posY, this.posZ, 1, 0);
                break;
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {

        if (!this.worldObj.isRemote && !this.isDead) {
            if (this.isEntityInvulnerable()) {
                return false;
            } else {
                this.setBeenAttacked();

                this.setDead();

                return true;
            }
        }
        return false;
    }

    private ItemStack[] items;

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
        ItemStack itemStack = getStackInSlot(i);
        setInventorySlotContents(i, null);
        return itemStack;
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
        return "Ship Inventory";
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
        return entityplayer.getDistanceSq(posX + 0.5, posY + 0.5, posZ + 0.5) <= 64;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return itemstack.itemID == ModBlocks.bomb.blockID;
    }

    @Override
    public void onInventoryChanged() {
        if (worldObj != null) {
            worldObj.updateEntity(this);
        }
    }


    public void openInventory() {
        if (this.riddenByEntity instanceof EntityPlayer) {
            FMLNetworkHandler.openGui((EntityPlayer) this.riddenByEntity, LearningModding.instance, 2, worldObj, (int) posX, (int) posY, (int) posZ);
        }
    }
}
