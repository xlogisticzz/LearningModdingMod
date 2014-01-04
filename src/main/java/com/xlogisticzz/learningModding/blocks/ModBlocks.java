package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.items.ItemMachine;
import com.xlogisticzz.learningModding.items.ItemMarker;
import com.xlogisticzz.learningModding.items.ItemOre;
import com.xlogisticzz.learningModding.items.ItemOreStorage;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.lib.Ids;
import com.xlogisticzz.learningModding.proxies.CommonProxy;
import com.xlogisticzz.learningModding.tileEntites.*;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ModBlocks {

    public static BlockModOre ores;
    public static BlockModOreStorage orestorageblocks;
    public static BlockMachine machineblock;
    public static BlockGlassConnected glassConnected;
    public static BlockMarker markerBlock;
    public static BlockBomb bomb;
    public static BlockSuperBomb superBomb;
    public static BlockNumber numbers;
    public static BlockClicker clicker;
    public static BlockPoison poisonBlock;
    public static BlockHeightParticle particleBlock;
    public static BlockNoteSequencer noteSequencer;
    public static BlockCakeStorage cakeStorage;

    public static void init() {

        ores = new BlockModOre(Ids.ORE);
        orestorageblocks = new BlockModOreStorage(Ids.STORAGE_BLOCK);
        machineblock = new BlockMachine(Ids.MACHINEBLOCK);
        glassConnected = new BlockGlassConnected(Ids.GLASS_CONNECTED);
        markerBlock = new BlockMarker(Ids.MARKERS);
        bomb = new BlockBomb(Ids.BOMB);
        superBomb = new BlockSuperBomb(Ids.SUPERBOMB);
        numbers = new BlockNumber(Ids.NUMBER);
        clicker = new BlockClicker(Ids.CLICKER);
        poisonBlock = new BlockPoison(Ids.POISON);
        particleBlock = new BlockHeightParticle(Ids.HEIGHTPARTICLE);
        noteSequencer = new BlockNoteSequencer(Ids.NOTE_SEQUENCER);
        cakeStorage = new BlockCakeStorage(Ids.CAKE_STORAGE);

    }

    public static void initInfo() {

        for (int i = 0; i < Constants.BlockNames.MACHINE_BLOCK_NAME.length; i++) {
            CommonProxy.addName(machineblock, Constants.BlockNames.MACHINE_BLOCK_NAME[i]);
            CommonProxy.setBlockHarvestLevel(machineblock, i, "pickaxe", 2);
        }
        CommonProxy.registerBlock(machineblock, 0, ItemMachine.class);
        CommonProxy.registerTileEntity(TileEntityMachine.class);

        for (int i = 0; i < Constants.BlockNames.MARKERS_NAME.length; i++) {
            CommonProxy.addName(markerBlock, Constants.BlockNames.MARKERS_NAME[i]);
            CommonProxy.setBlockHarvestLevel(markerBlock, i, "pickaxe", 2);
        }
        CommonProxy.registerBlock(markerBlock, 0, ItemMarker.class);

        for (int i = 0; i < Constants.BlockNames.ORES.length; i++) {
            CommonProxy.addName(ores, Constants.BlockNames.ORES[i]);
            CommonProxy.setBlockHarvestLevel(ores, i, "pickaxe", 2);
        }
        CommonProxy.registerBlock(ores, 0, ItemOre.class);

        for (int i = 0; i < Constants.BlockNames.ORESTORAGEBLOCKS.length; i++) {
            CommonProxy.addName(orestorageblocks, Constants.BlockNames.ORESTORAGEBLOCKS[i]);
            CommonProxy.setBlockHarvestLevel(orestorageblocks, i, "pickaxe", 2);
        }
        CommonProxy.registerBlock(orestorageblocks, 0, ItemOreStorage.class);

        CommonProxy.registerBlock(glassConnected, 0, null, 1, Constants.BlockNames.GLASS_CONNECTED);
        CommonProxy.registerBlock(bomb, 0, null, 1, Constants.BlockNames.BOMB_NAME);
        CommonProxy.registerTileEntity(TileEntityBomb.class);
        CommonProxy.registerBlock(superBomb, 0, null, 1, Constants.BlockNames.SUPER_BOMB_NAME);
        CommonProxy.registerBlock(numbers, 0, "pickaxe", 2, Constants.BlockNames.NUMBER_NAME);
        CommonProxy.registerTileEntity(TileEntityNumbers.class);
        CommonProxy.registerBlock(clicker, 0, "pickaxe", 2, Constants.BlockNames.CLICKER_NAME);
        CommonProxy.registerTileEntity(TileEntityClicker.class);
        CommonProxy.registerBlock(poisonBlock, 0, "pickaxe", 2, Constants.BlockNames.POISON_NAME);
        CommonProxy.registerBlock(particleBlock, 0, "pickaxe", 2, Constants.BlockNames.HEIGHT_PARTICLE_NAME);
        CommonProxy.registerBlock(noteSequencer, 0, "pickaxe", 2, Constants.BlockNames.NOTE_SEQUENCER_NAME);
        CommonProxy.registerTileEntity(TileEntityNoteSequencer.class);
        CommonProxy.registerBlock(cakeStorage, 0, "pickaxe", 2, Constants.BlockNames.CAKESTORAGE);
        CommonProxy.registerTileEntity(TileEntityCakeStorage.class);

    }
}
