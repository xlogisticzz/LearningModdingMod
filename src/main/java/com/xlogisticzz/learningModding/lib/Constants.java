package com.xlogisticzz.learningModding.lib;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class Constants {
    
    public class Mod {
        
        public static final String MODID = "learningmodding";
        public static final String NAME = "Learning Modding";
        public static final String CHANNEL_NAME = MODID;
        public static final String VERSION = "@VERSION@";
        public static final String FINGERPRINT = "@FINGERPRINT@";
    }
    
    public class Proxies {
        
        public static final String CLIENT = "com.xlogisticzz.learningModding.proxies.ClientProxy";
        public static final String COMMON = "com.xlogisticzz.learningModding.proxies.CommonProxy";
        
    }
    
    public static class BlockNames {

        public static final String[] ORES = {"Ruby Ore", "Titanium Ore"};
        public static final String TITANIUM_BLOCK_NAME = "Titanium Block";
        public static final String RUBY_BLOCK_NAME = "Ruby Block";
        public static final String[] MACHINE_BLOCK_NAME = { "Machine Block", "Machine Block Disabled", "Machine Block Arrow", "Machine Block Arrow Disabled", "Machine Block Border", "Machine Block Border Disabled", "Machine Block Cross", "Machine Block Cross Disabled" };
        public static final String GLASS_CONNECTED = "Glass Connected";
        public static final String[] MARKERS_NAME = { "Blue Marker", "Blue Marker Marked", "Brown Marker", "Brown Marker Marked", "Green Marker", "Green Marker Marked", "Grey Marker", "Grey Marker Marked", "Orange Marker", "Orange Marker Marked", "Purple Marker", "Purple Marker Marked", "Red Marker", "Red Marker Marked", "Yellow Marker", "Yellow Marker Marked" };
        public static final String BOMB_NAME = "Bomb";
        public static final String SUPER_BOMB_NAME = "Super Bomb";
        public static final String NUMBER_NAME = "Number Block";
        public static final String CLICKER_NAME = "Clicker Block";
        public static final String POISON_NAME = "Block Of Poison";
        public static final String HEIGHT_PARTICLE_NAME = "Particle Emmiter";
        public static final String NOTE_SEQUENCER_NAME = "Note Sequencer";
        
    }
    
    public static class ItemNames {
        
        public static final String RUBY_NAME = "Ruby";
        public static final String TITANIUM_INGOT_NAME = "Titanium Ingot";
        public static final String RUBY_PICKAXE_NAME = "Ruby Pickaxe";
        public static final String RUBY_SPADE_NAME = "Ruby Shovel";
        public static final String RUBY_SWORD_NAME = "Ruby Sword";
        public static final String RUBY_AXE_NAME = "Ruby Axe";
        public static final String RUBY_HOE_NAME = "Ruby Hoe";
        public static final String RUBY_HELMET_NAME = "Ruby Helmet";
        public static final String RUBY_CHESTPLATE_NAME = "Ruby Chestplate";
        public static final String RUBY_LEGGINGS_NAME = "Ruby Leggings";
        public static final String RUBY_BOOTS_NAME = "Ruby Boots";
        public static final String RUBY_WAND_NAME = "Ruby Wand";
        public static final String[] CARD_NAMES = { "Arrow Card", "Border Card", "Cross Card" };
        public static final String[] DEATHSTONES_NAMES = { "Deathstone Creeper", "Deathstone Enderman", "Deathstone Pig", "Deathstone Player", "Deathstone Skeleton" };
        public static final String WAND_NAME = "Wand";
        public static final String SPAWN_SPACESHIP_NAME = "Spawn Spaceship";
        public static final String ENTITY_LAUNCHER_NAME = "Entity Launcher";
        public static final String PIG_CONVERTER_NAME = "Pig Converter";
        public static final String ENTITY_BLOCK_TELEPORTER_NAME = "Entity Block Teleporter";
        
    }
    
    public static class Icons {
        
        // Items
        public static final String RUBY_WAND = "uncharged_wand";
        public static final String RUBY_WAND_CHARGED = "charged_wand";
        public static final String RUBY = "ruby";
        public static final String TITANIUM_INGOT = "titanium_ingot";
        public static final String RUBY_PICKAXE = "ruby_pickaxe";
        public static final String RUBY_SWORD = "ruby_sword";
        public static final String RUBY_SPADE = "ruby_spade";
        public static final String RUBY_AXE = "ruby_axe";
        public static final String RUBY_HOE = "ruby_hoe";
        public static final String[] CARD_ICONS = { "card_arrow", "card_box", "card_cross" };
        public static final String[] DEATHSTONES_ICONS = { "deathstone_creeper", "deathstone_enderman", "deathstone_pig", "deathstone_player", "deathstone_skeleton" };
        public static final String WAND = "wand";
        public static final String SPAWN_SPACESHIP = "spawn_spaceship";
        public static final String ENTITY_LAUNCHER = "entity_launcher";
        public static final String PIG_CONVERTER = "pig_converter";
        public static final String ENTITY_BLOCK_TELEPORTER = "entity_block_teleporter";
        
        // Blocks
        public static final String[] ORES = {"ruby_ore", "titanium_ore"};
        public static final String RUBY_BLOCK = "ruby_block";
        public static final String TITANIUM_BLOCK = "titanium_block";
        public static final String MACHINE_TOP = "machine_top";
        public static final String MACHINE_BOTTOM = "machine_bottom";
        public static final String MACHINE_DISABLED = "machine_disabled";
        public static final String[] MACHINE_SIDES = { "machine_side", "machine_side_arrow", "machine_side_box", "machine_side_cross" };
        public static final String[] GLASS_CONNECTED = { "Glass", "Glass_1_d", "Glass_1_l", "Glass_1_r", "Glass_1_u", "Glass_2_dl", "Glass_2_dr", "Glass_2_lr", "Glass_2_ud", "Glass_2_ul", "Glass_2_ur", "Glass_3_dlr", "Glass_3_udl", "Glass_3_udr", "Glass_3_ulr", "Glass_4" };
        public static final String[] MARKERS = { "marker_blue", "marker_blue_marked", "marker_brown", "marker_brown_marked", "marker_green", "marker_green_marked", "marker_grey", "marker_grey_marked", "marker_orange", "marker_orange_marked", "marker_purple", "marker_purple_marked", "marker_red", "marker_red_marked", "marker_yellow", "marker_yellow_marked" };
        public static final String BOMB = "bomb";
        public static final String BOMB_IDLE = "bomb_idle";
        public static final String SUPER_BOMB = "super_bomb";
        public static final String[] NUMBERS = { "number_1", "number_2", "number_3", "number_4", "number_5", "number_6", "number_7", "number_8", "number_9", "number_10", "number_11", "number_12", "number_13", "number_14", "number_15", "number_16", "number_17", "number_18" };
        public static final String CLICKER = "clicker";
        public static final String CLICKER_LINKED = "clicker_linked";
        public static final String POISON = "poison_block";
        public static final String HEIGHT_PARTICLE = "height_particle_block";
        public static final String NOTE_SEQUENCER = "note_sequencer";
        
    }
    
    public class UnLocalisedNames {
        
        // Items
        public static final String RUBY = "ruby";
        public static final String TITANIUM_INGOT = "titaniumIngot";
        public static final String RUBY_PICKAXE = "rubyPickaxe";
        public static final String RUBY_SWORD = "rubySword";
        public static final String RUBY_SPADE = "rubySpade";
        public static final String RUBY_AXE = "rubyAxe";
        public static final String RUBY_HOE = "rubyHoe";
        public static final String RUBY_HELMET = "rubyHelmet";
        public static final String RUBY_CHESTPLATE = "rubyChestplate";
        public static final String RUBY_LEGGINGS = "rubyLeggings";
        public static final String RUBY_BOOTS = "rubyBoots";
        public static final String RUBY_WAND = "rubyWand";
        public static final String CARD = "card";
        public static final String DEATHSTONE = "deathstone";
        public static final String WAND = "wand";
        public static final String SPAWN_SPACESHIP = "spawnSpaceship";
        public static final String ENTITY_LAUNCHER = "entityLauncher";
        public static final String PIG_CONVERTER = "pigConverter";
        public static final String ENTITY_BLOCK_TELEPORTER = "entityBlockTeleporter";
        
        // Blocks
        public static final String ORE = "modOre";
        public static final String RUBY_BLOCK = "rubyBlock";
        public static final String TITANIUM_BLOCK = "titaniumBlock";
        public static final String MACHINE_BLOCK = "machineBlock";
        public static final String GLASS_CONNECTED = "glassConnected";
        public static final String MARKERS = "markers";
        public static final String BOMB = "bomb";
        public static final String SUPER_BOMB = "superBomb";
        public static final String NUMBER = "number";
        public static final String CLICKER = "clicker";
        public static final String POISON = "poisonBlock";
        public static final String HEIGHT_PARTICLE = "heightParticleBlock";
        public static final String NOTE_SEQUENCER = "noteSequencerBlock";
        
    }
    
    public class Particles {
        
        public static final String POISON_TEXTURE = "poison_nocolor";
        public static final String HEIGHT_TEXTURE = "height_particle";
        
    }
    
    public static class Misc {
        
        public static final int RUBY_DURABILITY = 1000;
        public static final int NUMBER_COUNT = 18;
        
    }
    
    public static class WorldGen {
        
        public static int RUBY_WORLD_GEN_AMOUNT;
        public static final int RUBY_WORLD_GEN_AMOUNT_DEFAULT = 16;
        public static int RUBY_WORLD_GEN_ITERATIONS;
        public static final int RUBY_WORLD_GEN_ITERATIONS_DEFAULT = 20;
        public static int RUBY_WORLD_GEN_LOWESTY;
        public static final int RUBY_WORLD_GEN_LOWESTY_DEFAULT = 1;
        public static int RUBY_WORLD_GEN_HIGHESTY;
        public static final int RUBY_WORLD_GEN_HIGHESTY_DEFAULT = 128;
        
    }
    
    public static class Armor {
        
        public static int RUBY_HELMET_REDUCTION;
        public static final int RUBY_HELMET_REDUCTION_DEFAULT = 4;
        public static int RUBY_BOOTS_REDUCTION;
        public static final int RUBY_BOOTS_REDUCTION_DEFAULT = 4;
        public static int RUBY_CHESTPLATE_REDUCTION;
        public static final int RUBY_CHESTPLATE_REDUCTION_DEFAULT = 10;
        public static int RUBY_LEGGINGS_REDUCTION;
        public static final int RUBY_LEGGINGS_REDUCTION_DEFAULT = 8;
        
    }
}
