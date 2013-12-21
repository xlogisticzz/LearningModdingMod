package com.xlogisticzz.learningModding.entities;

import com.xlogisticzz.learningModding.LearningModding;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ModEntities {

    public static void init() {

        EntityRegistry.registerModEntity(EntitySpaceship.class, "EntitySpaceship", 0, LearningModding.instance, 80, 3, true);
        EntityRegistry.registerModEntity(EntityBomb.class, "EntityBomb", 1, LearningModding.instance, 80, 3, false);
        EntityRegistry.registerModEntity(EntityLaunched.class, "EntityLaunched", 2, LearningModding.instance, 80, 3, true);
        EntityRegistry.registerModEntity(EntityPigConverter.class, "EntityPigConverter", 3, LearningModding.instance, 80, 3, true);
        EntityRegistry.registerModEntity(EntityBlockEntityTeleport.class, "EntityBlockEntityTeleport", 4, LearningModding.instance, 80, 3, true);
    }

    public static void initInfo() {

    }

}
