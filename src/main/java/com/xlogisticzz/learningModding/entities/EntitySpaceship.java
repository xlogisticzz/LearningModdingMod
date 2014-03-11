package com.xlogisticzz.learningModding.entities;

import com.xlogisticzz.learningModding.LearningModding;
import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.client.sounds.SoundHandler;
import com.xlogisticzz.learningModding.network.PacketPipeline;
import com.xlogisticzz.learningModding.network.PacketSpaceShipInventory;
import com.xlogisticzz.learningModding.network.PacketSpaceshipBomb;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
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
    private String customName;

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
        boolean bombState = Minecraft.getMinecraft().gameSettings.keyBindAttack.isPressed();
        if (bombState && !this.lastPressedBombState && this.charged && this.riddenByEntity == Minecraft.getMinecraft().thePlayer) {
            boolean hasAmmo = false;
            for (int i = 0; i < getSizeInventory(); i++) {
                ItemStack stack = getStackInSlot(i);
                if (stack != null && Block.getBlockFromItem(stack.getItem()) == ModBlocks.bomb) {
                    hasAmmo = true;
                    break;
                }
            }
            if (!hasAmmo) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("You don't have enough ammo left");
                SoundHandler.playOnEntity(this, "emptyClick", 1, 0);
            } else {
                PacketPipeline.sendToServer(new PacketSpaceshipBomb(getEntityId()));
            }
        }
        this.lastPressedBombState = bombState;

        if (GameSettings.isKeyDown(LearningModding.dropBomb)) {
            if (this.charged && this.riddenByEntity == Minecraft.getMinecraft().thePlayer) {
                PacketPipeline.sendToServer(new PacketSpaceShipInventory(getEntityId()));
            }
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

        this.charged = compound.getBoolean("charged");

        NBTTagList items = compound.getTagList("Items", 10);

        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound item = (NBTTagCompound) items.getCompoundTagAt(i);
            int slot = item.getByte("Slot");

            if (slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }

        if (compound.hasKey("CustomName", 8)) {
            this.customName = compound.getString("CustomName");
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

        if (this.hasCustomInventoryName()) {
            compound.setString("CustomName", this.customName);
        }
    }


    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeBoolean(this.charged);

    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        this.charged = additionalData.readBoolean();
    }


    public void dropBomb() {
        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack stack = getStackInSlot(i);
            if (stack != null && Block.getBlockFromItem(stack.getItem()) == ModBlocks.bomb) {
                decrStackSize(i, 1);
                EntityBomb bomb = new EntityBomb(this.worldObj);

                bomb.posX = this.posX;
                bomb.posY = this.posY;
                bomb.posZ = this.posZ;

                this.worldObj.spawnEntityInWorld(bomb);
                SoundHandler.playOnEntity(this, "bombFall", 1, 0);
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
                markDirty();
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
        return entityplayer.getDistanceSq(posX + 0.5, posY + 0.5, posZ + 0.5) <= 64;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return Block.getBlockFromItem(itemstack.getItem()) == ModBlocks.bomb;
    }

    @Override
    public void markDirty() {
        if (worldObj != null) {
            worldObj.updateEntity(this);
        }
    }

    public void openShipInventory() {
        if (this.riddenByEntity instanceof EntityPlayer) {
            FMLNetworkHandler.openGui((EntityPlayer) this.riddenByEntity, LearningModding.instance, 2, worldObj, (int) posX, (int) posY, (int) posZ);
        }
    }
}
