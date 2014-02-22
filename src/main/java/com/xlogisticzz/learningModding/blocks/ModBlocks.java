package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.items.ItemMachine;
import com.xlogisticzz.learningModding.items.ItemMarker;
import com.xlogisticzz.learningModding.items.ItemOre;
import com.xlogisticzz.learningModding.items.ItemOreStorage;
import com.xlogisticzz.learningModding.tileEntites.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ModBlocks {

    public static Block ores = new BlockModOre();
    public static Block orestorageblocks = new BlockModOreStorage();
    public static Block machineblock = new BlockMachine();
    public static Block glassConnected = new BlockGlassConnected();
    public static Block markerBlock = new BlockMarker();
    public static Block bomb = new BlockBomb();
    public static Block superBomb = new BlockSuperBomb();
    public static Block numbers = new BlockNumber();
    public static Block clicker = new BlockClicker();
    public static BlockPoison poisonBlock = new BlockPoison();
    public static BlockHeightParticle particleBlock = new BlockHeightParticle();
    public static Block noteSequencer = new BlockNoteSequencer();
    public static Block cakeStorage = new BlockCakeStorage();

    public static void init() {
        GameRegistry.registerBlock(ores, ItemOre.class, "ores");
        GameRegistry.registerBlock(orestorageblocks, ItemOreStorage.class, "oreStorageBlocks");
        GameRegistry.registerBlock(machineblock, ItemMachine.class, "machineBlock");
        GameRegistry.registerBlock(glassConnected, "connectedGlass");
        GameRegistry.registerBlock(markerBlock, ItemMarker.class, "marker");
        GameRegistry.registerBlock(bomb, "bomb");
        GameRegistry.registerBlock(superBomb, "superBomb");
        GameRegistry.registerBlock(numbers, "numbers");
        GameRegistry.registerBlock(clicker, "clicker");
        GameRegistry.registerBlock(poisonBlock, "poisonBlock");
        GameRegistry.registerBlock(particleBlock, "particleBlock");
        GameRegistry.registerBlock(noteSequencer, "noteSequencer");
        GameRegistry.registerBlock(cakeStorage, "cakeStorage");

        GameRegistry.registerTileEntity(TileEntityMachine.class, "tileMachine");
        GameRegistry.registerTileEntity(TileEntityBomb.class, "tileBomb");
        GameRegistry.registerTileEntity(TileEntityNumbers.class, "tileNumber");
        GameRegistry.registerTileEntity(TileEntityClicker.class, "tileClicker");
        GameRegistry.registerTileEntity(TileEntityNoteSequencer.class, "tileNoteSequencer");
        GameRegistry.registerTileEntity(TileEntityCakeStorage.class, "tileCakeStorage");
    }
}
