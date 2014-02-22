package com.xlogisticzz.learningModding.configuration;

import com.xlogisticzz.learningModding.lib.Constants;
import net.minecraftforge.common.config.Configuration;

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

        Constants.Armor.RUBY_HELMET_REDUCTION = configuration.get("Armor", "Ruby Helmet Damage Reduction", Constants.Armor.RUBY_HELMET_REDUCTION_DEFAULT).getInt();
        Constants.Armor.RUBY_CHESTPLATE_REDUCTION = configuration.get("Armor", "Ruby Chestplate Damage Reduction", Constants.Armor.RUBY_CHESTPLATE_REDUCTION_DEFAULT).getInt();
        Constants.Armor.RUBY_LEGGINGS_REDUCTION = configuration.get("Armor", "Ruby Leggings Damage Reduction", Constants.Armor.RUBY_LEGGINGS_REDUCTION_DEFAULT).getInt();
        Constants.Armor.RUBY_BOOTS_REDUCTION = configuration.get("Armor", "Ruby Boots Damage Reduction", Constants.Armor.RUBY_BOOTS_REDUCTION_DEFAULT).getInt();

        Constants.WorldGen.RUBY_WORLD_GEN_AMOUNT = configuration.get("World Generation", "Ruby Ore Generation Vein Size", Constants.WorldGen.RUBY_WORLD_GEN_AMOUNT_DEFAULT).getInt();
        Constants.WorldGen.RUBY_WORLD_GEN_ITERATIONS = configuration.get("World Generation", "Ruby Ore Veins per Chunk", Constants.WorldGen.RUBY_WORLD_GEN_ITERATIONS_DEFAULT).getInt();
        Constants.WorldGen.RUBY_WORLD_GEN_LOWESTY = configuration.get("World Generation", "Ruby Ore Lowest Y Generation", Constants.WorldGen.RUBY_WORLD_GEN_LOWESTY_DEFAULT).getInt();
        Constants.WorldGen.RUBY_WORLD_GEN_HIGHESTY = configuration.get("World Generation", "Ruby Ore Highest Y Generation", Constants.WorldGen.RUBY_WORLD_GEN_HIGHESTY_DEFAULT).getInt();

        Constants.WorldGen.TITANIUM_WORLD_GEN_AMOUNT = configuration.get("World Generation", "Titanium Ore Generation Vein Size", Constants.WorldGen.TITANIUM_WORLD_GEN_AMOUNT_DEFAULT).getInt();
        Constants.WorldGen.TITANIUM_WORLD_GEN_ITERATIONS = configuration.get("World Generation", "Titanium Ore Veins per Chunk", Constants.WorldGen.TITANIUM_WORLD_GEN_ITERATIONS_DEFAULT).getInt();
        Constants.WorldGen.TITANIUM_WORLD_GEN_LOWESTY = configuration.get("World Generation", "Titanium Ore Lowest Y Generation", Constants.WorldGen.TITANIUM_WORLD_GEN_LOWESTY_DEFAULT).getInt();
        Constants.WorldGen.TITANIUM_WORLD_GEN_HIGHESTY = configuration.get("World Generation", "Titanium Ore Highest Y Generation", Constants.WorldGen.TITANIUM_WORLD_GEN_HIGHESTY_DEFAULT).getInt();


        /* Save the config */
        configuration.save();

    }
}
