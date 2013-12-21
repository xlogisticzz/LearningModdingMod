package com.xlogisticzz.learningModding.dispenser;

import com.xlogisticzz.learningModding.entities.EntityBlockEntityTeleport;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DispenserBehaviourBlockEntityTeleport extends BehaviorProjectileDispense {

    @Override
    protected IProjectile getProjectileEntity(World world, IPosition iposition) {

        EntityBlockEntityTeleport entity = new EntityBlockEntityTeleport(world, iposition.getX(), iposition.getY(), iposition.getZ());
        entity.setLaunchPos(iposition.getX(), iposition.getY(), iposition.getZ());
        return entity;
    }

}
