package com.xlogisticzz.learningModding.entities;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.xlogisticzz.learningModding.client.sounds.Sounds;
import com.xlogisticzz.learningModding.network.PacketHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class EntitySpaceship extends Entity implements IEntityAdditionalSpawnData {

    private boolean charged;

    public EntitySpaceship(World world) {

        super(world);
        this.setSize(1.5F, 0.6F);
    }

    @Override
    protected void entityInit() {

        this.dataWatcher.addObject(15, 10);
    }

    public int getAmmunitionAmount() {

        return this.dataWatcher.getWatchableObjectInt(15);

    }

    public void setAmmunitionAmount(int ammo) {

        this.dataWatcher.updateObject(15, ammo);
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
            if (getAmmunitionAmount() == 0) {
                Minecraft.getMinecraft().thePlayer.addChatMessage("You don't have enough ammo left");
                Sounds.OUT_OF_AMMO.play(this.posX, this.posY, this.posZ, 1, 0);
            } else {
                PacketHandler.sendShipBombPacket(this);
            }
        }
        this.lastPressedBombState = bombState;

        boolean forwardState = Minecraft.getMinecraft().gameSettings.keyBindForward.pressed;
        if (forwardState && this.charged && this.riddenByEntity == Minecraft.getMinecraft().thePlayer) {
            PacketHandler.sendShipForwardPacket(this);
        }

        boolean backwardState = Minecraft.getMinecraft().gameSettings.keyBindBack.pressed;
        if (backwardState && this.charged && this.riddenByEntity == Minecraft.getMinecraft().thePlayer) {
            PacketHandler.sendShipBackwardPacket(this);

        }

        boolean UpState = Minecraft.getMinecraft().gameSettings.keyBindJump.pressed;
        if (UpState && this.charged && this.riddenByEntity == Minecraft.getMinecraft().thePlayer) {
            PacketHandler.sendShipUpPacket(this);
        }

        boolean DownState = Minecraft.getMinecraft().gameSettings.keyBindDrop.pressed;
        if (DownState && this.charged && this.riddenByEntity == Minecraft.getMinecraft().thePlayer) {
            PacketHandler.sendShipDownPacket(this);
        }

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

        this.charged = compound.getBoolean("charged");
        setAmmunitionAmount(compound.getByte("ammo"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

        compound.setBoolean("charged", this.charged);
        compound.setByte("ammo", (byte) getAmmunitionAmount());
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

        if (getAmmunitionAmount() > 0) {

            EntityBomb bomb = new EntityBomb(this.worldObj);

            bomb.posX = this.posX;
            bomb.posY = this.posY;
            bomb.posZ = this.posZ;

            this.worldObj.spawnEntityInWorld(bomb);
            setAmmunitionAmount(getAmmunitionAmount() - 1);
            Sounds.BOMB_DROP.play(this.posX, this.posY, this.posZ, 1, 0);
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

    public void forward() {

        float yaw = this.riddenByEntity.rotationYaw;

        if (yaw - 90 > 180) {
            int num = (int) (yaw - 90 - 180);

            int newnum = -180 + num;

            this.setRotation(newnum, this.rotationPitch);

        } else {
            this.setRotation(yaw - 90, this.rotationPitch);
        }

        if (yaw >= 315 && yaw <= 0) {
            if (this.motionZ < 10) {
                this.motionZ = this.motionZ + 0.1F;
            }
        } else if (yaw >= 0 && yaw <= 45) {
            if (this.motionZ < 10) {
                this.motionZ = this.motionZ + 0.1F;
            }
        } else if (yaw >= 45 && yaw <= 135) {
            if (this.motionX > -10) {
                this.motionX = this.motionX - 0.1F;
            }
        } else if (yaw >= 135 && yaw <= 225) {
            if (this.motionZ > -10) {
                this.motionZ = this.motionZ - 0.1F;
            }
        } else if (yaw >= 225 && yaw <= 315) {
            if (this.motionX < 10) {
                this.motionX = this.motionX + 0.1F;
            }
        }
    }

    public void backward() {

        float yaw = this.riddenByEntity.rotationYaw;

        if (yaw >= 315 && yaw <= 360) {
            if (this.motionZ > -10) {
                this.motionZ = this.motionZ - 0.1F;
            }
        } else if (yaw >= 0 && yaw <= 45) {
            if (this.motionZ > -10) {
                this.motionZ = this.motionZ - 0.1F;
            }
        } else if (yaw >= 45 && yaw <= 135) {
            if (this.motionX < 10) {
                this.motionX = this.motionX + 0.1F;
            }
        } else if (yaw >= 135 && yaw <= 225) {
            if (this.motionZ < 10) {
                this.motionZ = this.motionZ + 0.1F;
            }
        } else if (yaw >= 225 && yaw <= 315) {
            if (this.motionX > -10) {
                this.motionX = this.motionX - 0.1F;
            }
        }
    }

    public void up() {

        if (this.motionY < 10) {
            this.motionY = this.motionY + 0.1F;
        }
    }

    public void down() {

        if (this.motionY > -10) {
            this.motionY = this.motionY - 0.1F;
        }
    }
}
