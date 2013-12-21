package com.xlogisticzz.learningModding.configuration;

import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.lib.Ids;
import net.minecraftforge.common.Configuration;

import java.io.File;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ConfigurationHandler {

    public static Configuration configuration;

    public static void init(File configFile) {

        configuration = new Configuration(configFile);

        configuration.load();

        // Blocks
        Ids.TITANIUMORE = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Titanium Ore ID", Ids.TITANIUMORE_DEFAULT).getInt();
        Ids.RUBYORE = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Ruby Ore ID", Ids.RUBYORE_DEFAULT).getInt();
        Ids.RUBYBLOCK = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Ruby Block ID", Ids.RUBYBLOCK_DEFAULT).getInt();
        Ids.TITANIUMBLOCK = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Titanium Block ID", Ids.TITANIUMBLOCK_DEFAULT).getInt();
        Ids.MACHINEBLOCK = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Machine Block ID", Ids.MACHINEBLOCK_DEFAULT).getInt();
        Ids.GLASS_CONNECTED = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Glass Id", Ids.GLASS_CONNECTED_DEFAULT).getInt();
        Ids.TESTBLOCK = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Test Id", Ids.TESTBLOCK_DEFAULT).getInt();
        Ids.MARKERS = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Marker Id", Ids.MARKERS_DEFAULT).getInt();
        Ids.BOMB = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Bomb Id", Ids.BOMB_DEFAULT).getInt();
        Ids.SUPERBOMB = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Super Bomb Id", Ids.SUPERBOMB_DEFAULT).getInt();
        Ids.NUMBER = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Numbers Id", Ids.NUMBER_DEFAULT).getInt();
        Ids.CLICKER = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Clicker Id", Ids.CLICKER_DEFAULT).getInt();
        Ids.POISON = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Posion Id", Ids.POISON_DEFAULT).getInt();
        Ids.HEIGHTPARTICLE = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Height Particle Block Id", Ids.HEIGHTPARTICLE_DEFAULT).getInt();
        Ids.NOTE_SEQUENCER = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Note Sequencer Block Id", Ids.NOTE_SEQUENCER_DEFAULT).getInt();
        Ids.CAKE_STORAGE = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Cake Storage Block Id", Ids.CAKE_STORAGE_DEFAULT).getInt();

        // Item IDs
        Ids.TITANIUMINGOT = configuration.getItem(Configuration.CATEGORY_ITEM, "Titanium Ingot ID", Ids.TITANIUMINGOT_DEFAULT).getInt() - 256;
        Ids.RUBY = configuration.getItem(Configuration.CATEGORY_ITEM, "Ruby ID", Ids.RUBY_DEFAULT).getInt() - 256;
        Ids.RUBY_SWORD = configuration.getItem(Configuration.CATEGORY_ITEM, "Ruby Sword ID", Ids.RUBY_SWORD_DEFAULT).getInt() - 256;
        Ids.RUBY_PICKAXE = configuration.getItem(Configuration.CATEGORY_ITEM, "Ruby Pickaxe ID", Ids.RUBY_PICKAXE_DEFAULT).getInt() - 256;
        Ids.RUBY_SPADE = configuration.getItem(Configuration.CATEGORY_ITEM, "Ruby Shovel ID", Ids.RUBY_SPADE_DEFAULT).getInt() - 256;
        Ids.RUBY_AXE = configuration.getItem(Configuration.CATEGORY_ITEM, "Ruby Axe ID", Ids.RUBY_AXE_DEFAULT).getInt() - 256;
        Ids.RUBY_HOE = configuration.getItem(Configuration.CATEGORY_ITEM, "Ruby Hoe ID", Ids.RUBY_HOE_DEFAULT).getInt() - 256;
        Ids.RUBY_WAND = configuration.getItem(Configuration.CATEGORY_ITEM, "Ruby Wand ID", Ids.RUBY_WAND_DEFAULT).getInt() - 256;
        Ids.CARD = configuration.getItem(Configuration.CATEGORY_ITEM, "Card Id", Ids.CARD_DEFAULT).getInt() - 256;
        Ids.DEATHSTONES = configuration.getItem(Configuration.CATEGORY_ITEM, "Deathstones ID", Ids.DEATHSTONES_DEFAULT).getInt() - 256;
        Ids.WAND = configuration.getItem(Configuration.CATEGORY_ITEM, "Wand ID", Ids.WAND_DEFAULT).getInt() - 256;
        Ids.SPAWN_SPACESHIP = configuration.getItem(Configuration.CATEGORY_ITEM, "Spawn Spaceship ID", Ids.SPAWN_SPACESHIP_DEFAULT).getInt() - 256;
        Ids.ENTITY_LAUNCHER = configuration.getItem(Configuration.CATEGORY_ITEM, "Entity Launcher ID", Ids.ENTITY_LAUNCHER_DEFAULT).getInt() - 256;
        Ids.PIG_CONVERTER = configuration.getItem(Configuration.CATEGORY_ITEM, "Pig Converter ID", Ids.PIG_CONVERTER_DEFAULT).getInt() - 256;
        Ids.ENTITY_BLOCK_TELEPORTER = configuration.getItem(Configuration.CATEGORY_ITEM, "Entity Block Teleporter ID", Ids.ENTITY_BLOCK_TELEPORTER_DEFAULT).getInt() - 256;

        // Ruby Armor
        Ids.RUBY_HELMET = configuration.getItem("Armor", "Ruby Helmet ID", Ids.RUBY_HELMET_DEFAULT).getInt() - 256;
        Ids.RUBY_CHESTPLATE = configuration.getItem("Armor", "Ruby Chestplate ID", Ids.RUBY_CHESTPLATE_DEFAULT).getInt() - 256;
        Ids.RUBY_LEGGINGS = configuration.getItem("Armor", "Ruby Leggings ID", Ids.RUBY_LEGGINGS_DEFAULT).getInt() - 256;
        Ids.RUBY_BOOTS = configuration.getItem("Armor", "Ruby Boots ID", Ids.RUBY_BOOTS_DEFAULT).getInt() - 256;

        Constants.Armor.RUBY_HELMET_REDUCTION = configuration.get("Armor", "Ruby Helmet Damage Reduction", Constants.Armor.RUBY_HELMET_REDUCTION_DEFAULT).getInt();
        Constants.Armor.RUBY_CHESTPLATE_REDUCTION = configuration.get("Armor", "Ruby Chestplate Damage Reduction", Constants.Armor.RUBY_CHESTPLATE_REDUCTION_DEFAULT).getInt();
        Constants.Armor.RUBY_LEGGINGS_REDUCTION = configuration.get("Armor", "Ruby Leggings Damage Reduction", Constants.Armor.RUBY_LEGGINGS_REDUCTION_DEFAULT).getInt();
        Constants.Armor.RUBY_BOOTS_REDUCTION = configuration.get("Armor", "Ruby Boots Damage Reduction", Constants.Armor.RUBY_BOOTS_REDUCTION_DEFAULT).getInt();

        Constants.WorldGen.RUBY_WORLD_GEN_AMOUNT = configuration.get("World Generation", "Ruby OreGeneration Vein Size", Constants.WorldGen.RUBY_WORLD_GEN_AMOUNT_DEFAULT).getInt();
        Constants.WorldGen.RUBY_WORLD_GEN_ITERATIONS = configuration.get("World Generation", "Ruby Ore Veins per Chunk", Constants.WorldGen.RUBY_WORLD_GEN_ITERATIONS_DEFAULT).getInt();
        Constants.WorldGen.RUBY_WORLD_GEN_LOWESTY = configuration.get("World Generation", "Ruby Ore Lowest Y Generation", Constants.WorldGen.RUBY_WORLD_GEN_LOWESTY_DEFAULT).getInt();
        Constants.WorldGen.RUBY_WORLD_GEN_HIGHESTY = configuration.get("World Generation", "Ruby Ore Highest Y Generation", Constants.WorldGen.RUBY_WORLD_GEN_HIGHESTY_DEFAULT).getInt();
        
        /* Save the config */
        configuration.save();

    }
}
