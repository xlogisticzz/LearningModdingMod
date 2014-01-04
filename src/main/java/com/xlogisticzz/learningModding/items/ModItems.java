package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.lib.Ids;
import com.xlogisticzz.learningModding.proxies.CommonProxy;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ModItems {

    // Mod ItemNames
    public static ItemModItem items;
    public static ItemModPickaxe rubyPickaxe;
    public static ItemModSpade rubySpade;
    public static ItemModSword rubySword;
    public static ItemModAxe rubyAxe;
    public static ItemModHoe rubyHoe;
    public static ItemModArmor rubyHelmet;
    public static ItemModArmor rubyChestplate;
    public static ItemModArmor rubyLeggings;
    public static ItemModArmor rubyBoots;
    public static ItemRubyWand rubyWand;
    public static ItemCard card;
    public static ItemDeathstone deathstone;
    public static ItemWand wand;
    public static ItemSpawnSpaceship spawnSpaceship;
    public static ItemEntityLauncher entityLauncher;
    public static ItemPigConverter pigConverter;
    public static ItemEntityBlockTeleport entityBlockTeleporter;

    public static EnumToolMaterial materialRuby = EnumHelper.addToolMaterial("ruby", 3, Constants.Misc.RUBY_DURABILITY, 4.1F, 3.0F, 40);
    public static EnumArmorMaterial materialArmorRuby = EnumHelper.addArmorMaterial("ruby", Constants.Misc.RUBY_DURABILITY, new int[]{Constants.Armor.RUBY_HELMET_REDUCTION, Constants.Armor.RUBY_CHESTPLATE_REDUCTION, Constants.Armor.RUBY_LEGGINGS_REDUCTION, Constants.Armor.RUBY_BOOTS_REDUCTION}, 40);

    public static void init() {

        items = new ItemModItem(Ids.ITEMS);
        rubyPickaxe = new ItemModPickaxe(Ids.RUBY_PICKAXE, materialRuby, 5.0F, 10.0F, LearningModdingCreativeTab.tabLearningModding, Constants.UnLocalisedNames.RUBY_PICKAXE, Constants.Icons.RUBY_PICKAXE, Constants.Misc.RUBY_DURABILITY);
        rubySpade = new ItemModSpade(Ids.RUBY_SPADE, materialRuby, LearningModdingCreativeTab.tabLearningModding, Constants.Misc.RUBY_DURABILITY, Constants.UnLocalisedNames.RUBY_SPADE, Constants.Icons.RUBY_SPADE);
        rubySword = new ItemModSword(Ids.RUBY_SWORD, materialRuby, LearningModdingCreativeTab.tabLearningModding, Constants.Misc.RUBY_DURABILITY, Constants.UnLocalisedNames.RUBY_SWORD, Constants.Icons.RUBY_SWORD);
        rubyAxe = new ItemModAxe(Ids.RUBY_AXE, materialRuby, LearningModdingCreativeTab.tabLearningModding, Constants.Misc.RUBY_DURABILITY, Constants.UnLocalisedNames.RUBY_AXE, Constants.Icons.RUBY_AXE);
        rubyHoe = new ItemModHoe(Ids.RUBY_HOE, materialRuby, LearningModdingCreativeTab.tabLearningModding, Constants.Misc.RUBY_DURABILITY, Constants.UnLocalisedNames.RUBY_HOE, Constants.Icons.RUBY_HOE);
        rubyWand = new ItemRubyWand(Ids.RUBY_WAND);
        card = new ItemCard(Ids.CARD);
        deathstone = new ItemDeathstone(Ids.DEATHSTONES);
        wand = new ItemWand(Ids.WAND);
        spawnSpaceship = new ItemSpawnSpaceship(Ids.SPAWN_SPACESHIP);
        entityLauncher = new ItemEntityLauncher(Ids.ENTITY_LAUNCHER);
        pigConverter = new ItemPigConverter(Ids.PIG_CONVERTER);
        entityBlockTeleporter = new ItemEntityBlockTeleport(Ids.ENTITY_BLOCK_TELEPORTER);
        rubyHelmet = new ItemModArmor(Ids.RUBY_HELMET, materialArmorRuby, 0, 0, "ruby", LearningModdingCreativeTab.tabLearningModding);
        rubyChestplate = new ItemModArmor(Ids.RUBY_CHESTPLATE, materialArmorRuby, 0, 1, "ruby", LearningModdingCreativeTab.tabLearningModding);
        rubyLeggings = new ItemModArmor(Ids.RUBY_LEGGINGS, materialArmorRuby, 0, 2, "ruby", LearningModdingCreativeTab.tabLearningModding);
        rubyBoots = new ItemModArmor(Ids.RUBY_BOOTS, materialArmorRuby, 0, 3, "ruby", LearningModdingCreativeTab.tabLearningModding);

        initLang();
    }

    public static void initLang() {

        CommonProxy.registerItem(rubyWand, Constants.ItemNames.RUBY_WAND_NAME);
        CommonProxy.registerItem(wand, Constants.ItemNames.WAND_NAME);
        CommonProxy.registerItem(spawnSpaceship, Constants.ItemNames.SPAWN_SPACESHIP_NAME);
        CommonProxy.registerItem(entityLauncher, Constants.ItemNames.ENTITY_LAUNCHER_NAME);
        CommonProxy.registerItem(pigConverter, Constants.ItemNames.PIG_CONVERTER_NAME);
        CommonProxy.registerItem(entityBlockTeleporter, Constants.ItemNames.ENTITY_BLOCK_TELEPORTER_NAME);
        CommonProxy.registerItem(rubyPickaxe, Constants.ItemNames.RUBY_PICKAXE_NAME);
        CommonProxy.registerItem(rubySpade, Constants.ItemNames.RUBY_SPADE_NAME);
        CommonProxy.registerItem(rubySword, Constants.ItemNames.RUBY_SWORD_NAME);
        CommonProxy.registerItem(rubyAxe, Constants.ItemNames.RUBY_AXE_NAME);
        CommonProxy.registerItem(rubyHoe, Constants.ItemNames.RUBY_HOE_NAME);
        CommonProxy.registerItem(rubyHelmet, Constants.ItemNames.RUBY_HELMET_NAME);
        CommonProxy.registerItem(rubyChestplate, Constants.ItemNames.RUBY_CHESTPLATE_NAME);
        CommonProxy.registerItem(rubyLeggings, Constants.ItemNames.RUBY_LEGGINGS_NAME);
        CommonProxy.registerItem(rubyBoots, Constants.ItemNames.RUBY_BOOTS_NAME);

        for (int i = 0; i < Constants.ItemNames.CARD_NAMES.length; i++) {
            CommonProxy.registerItem(card, new ItemStack(card, 1, i), Constants.ItemNames.CARD_NAMES[i]);
        }
        for (int i = 0; i < Constants.ItemNames.ITEMS_NAME.length; i++) {
            CommonProxy.registerItem(card, new ItemStack(card, 1, i), Constants.ItemNames.ITEMS_NAME[i]);
        }

        for (int i = 0; i < Constants.ItemNames.DEATHSTONES_NAMES.length; i++) {
            CommonProxy.registerItem(deathstone, new ItemStack(deathstone, 1, i), Constants.ItemNames.DEATHSTONES_NAMES[i]);
        }
    }
}
