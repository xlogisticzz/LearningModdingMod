package com.xlogisticzz.learningModding.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityBlockEntityTeleport extends EntityThrowable {

    private int launchX;
    private int launchY;
    private int launchZ;

    public EntityBlockEntityTeleport(World par1World) {

        super(par1World);

    }

    public EntityBlockEntityTeleport(World par1World, EntityLivingBase par2EntityLivingBase) {

        super(par1World, par2EntityLivingBase);

    }

    public EntityBlockEntityTeleport(World par1World, double par2, double par4, double par6) {

        super(par1World, par2, par4, par6);

    }

    @Override
    protected void onImpact(MovingObjectPosition position) {

        if (!this.worldObj.isRemote) {
            if (position.entityHit != null && position.typeOfHit == EnumMovingObjectType.ENTITY) {
                Entity entity = position.entityHit;

                entity.setPosition(this.launchX, this.launchY + 1, this.launchZ);
            } else {
                int hitX = position.blockX;
                int hitY = position.blockY;
                int hitZ = position.blockZ;

                int hitBlock = this.worldObj.getBlockId(hitX, hitY, hitZ);
                if (!(Block.blocksList[hitBlock] == Block.bedrock)) {
                    this.worldObj.setBlock(hitX, hitY, hitZ, 0);
                    Block.blocksList[hitBlock].dropBlockAsItem(this.worldObj, this.launchX, this.launchY + 1, this.launchZ, this.worldObj.getBlockMetadata(hitX, hitY, hitZ), 0);
                }
            }
            this.setDead();
        }
    }

    public void setLaunchPos(double posX, double posY, double posZ) {

        this.launchX = (int) posX;
        this.launchY = (int) posY;
        this.launchZ = (int) posZ;
    }
}
