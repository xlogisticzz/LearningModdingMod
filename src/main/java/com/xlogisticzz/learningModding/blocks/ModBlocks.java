package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.lib.Ids;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ModBlocks {

    public static Block ores = GameData.blockRegistry.getObjectById(Ids.ORE);
    public static Block orestorageblocks = GameData.blockRegistry.getObjectById(Ids.STORAGE_BLOCK);
    public static Block machineblock = GameData.blockRegistry.getObjectById(Ids.MACHINEBLOCK);
    public static Block glassConnected = GameData.blockRegistry.getObjectById(Ids.GLASS_CONNECTED);
    public static Block markerBlock = GameData.blockRegistry.getObjectById(Ids.MARKERS);
    public static Block bomb = GameData.blockRegistry.getObjectById(Ids.BOMB);
    public static Block superBomb = GameData.blockRegistry.getObjectById(Ids.SUPERBOMB);
    public static Block numbers = GameData.blockRegistry.getObjectById(Ids.NUMBER);
    public static Block clicker = GameData.blockRegistry.getObjectById(Ids.CLICKER);
    public static Block poisonBlock = GameData.blockRegistry.getObjectById(Ids.POISON);
    public static Block particleBlock = GameData.blockRegistry.getObjectById(Ids.HEIGHTPARTICLE);
    public static Block noteSequencer = GameData.blockRegistry.getObjectById(Ids.NOTE_SEQUENCER);
    public static Block cakeStorage = GameData.blockRegistry.getObjectById(Ids.CAKE_STORAGE);
    public static Block customFurnace = GameData.blockRegistry.getObjectById(Ids.CUSTOM_FURNACE);

    public static void init() {

        GameData.blockRegistry.addObject(Ids.ORE, "ores",(new BlockModOre()));
        GameData.blockRegistry.addObject(Ids.STORAGE_BLOCK, "oreStorage", (new BlockModOreStorage()));
        GameData.blockRegistry.addObject(Ids.MACHINEBLOCK, "machineBlock", (new BlockMachine()));
        GameData.blockRegistry.addObject(Ids.GLASS_CONNECTED, "glassConnected", (new BlockGlassConnected()));
        GameData.blockRegistry.addObject(Ids.MARKERS, "markers", (new BlockMarker()));
        GameData.blockRegistry.addObject(Ids.BOMB, "bomb", (new BlockBomb()));
        GameData.blockRegistry.addObject(Ids.SUPERBOMB, "superBomb", (new BlockSuperBomb()));
        GameData.blockRegistry.addObject(Ids.NUMBER, "numbers", (new BlockNumber()));
        GameData.blockRegistry.addObject(Ids.CLICKER, "clicker", (new BlockClicker()));
        GameData.blockRegistry.addObject();
        GameData.blockRegistry.addObject();
        GameData.blockRegistry.addObject();
        GameData.blockRegistry.addObject();
        GameData.blockRegistry.addObject();


    }
}
