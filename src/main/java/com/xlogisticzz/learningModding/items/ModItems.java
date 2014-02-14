package com.xlogisticzz.learningModding.items;

import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

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

    public static Item.ToolMaterial materialRuby = EnumHelper.addToolMaterial("ruby", 3, Constants.Misc.RUBY_DURABILITY, 4.1F, 3.0F, 40);
    public static ItemArmor.ArmorMaterial materialArmorRuby = EnumHelper.addArmorMaterial("ruby", Constants.Misc.RUBY_DURABILITY, new int[]{Constants.Armor.RUBY_HELMET_REDUCTION, Constants.Armor.RUBY_CHESTPLATE_REDUCTION, Constants.Armor.RUBY_LEGGINGS_REDUCTION, Constants.Armor.RUBY_BOOTS_REDUCTION}, 40);

    public static void init() {

        items = new ItemModItem();
        rubyPickaxe = new ItemModPickaxe(materialRuby, 10.0F, Constants.UnLocalisedNames.RUBY_PICKAXE, Constants.Icons.RUBY_PICKAXE, Constants.Misc.RUBY_DURABILITY);
        rubySpade = new ItemModSpade(materialRuby, Constants.UnLocalisedNames.RUBY_SPADE, Constants.Icons.RUBY_SPADE, Constants.Misc.RUBY_DURABILITY);
        rubySword = new ItemModSword(materialRuby, Constants.UnLocalisedNames.RUBY_SWORD, Constants.Icons.RUBY_SWORD, Constants.Misc.RUBY_DURABILITY);
        rubyAxe = new ItemModAxe(materialRuby, Constants.UnLocalisedNames.RUBY_AXE, Constants.Icons.RUBY_AXE, Constants.Misc.RUBY_DURABILITY);
        rubyHoe = new ItemModHoe(materialRuby, Constants.UnLocalisedNames.RUBY_HOE, Constants.Icons.RUBY_HOE, Constants.Misc.RUBY_DURABILITY);
        rubyWand = new ItemRubyWand();
        card = new ItemCard();
        deathstone = new ItemDeathstone();
        wand = new ItemWand();
        spawnSpaceship = new ItemSpawnSpaceship();
        entityLauncher = new ItemEntityLauncher();
        pigConverter = new ItemPigConverter();
        entityBlockTeleporter = new ItemEntityBlockTeleport();
        rubyHelmet = new ItemModArmor(materialArmorRuby, 0, 0, "ruby");
        rubyChestplate = new ItemModArmor(materialArmorRuby, 0, 1, "ruby");
        rubyLeggings = new ItemModArmor(materialArmorRuby, 0, 2, "ruby");
        rubyBoots = new ItemModArmor(materialArmorRuby, 0, 3, "ruby");

        initLang();
    }

    public static void initLang() {


        GameRegistry.registerItem(rubyWand, "rubyWand");
        GameRegistry.registerItem(wand, "wand");
        GameRegistry.registerItem(spawnSpaceship, "spawnSpaceship");
        GameRegistry.registerItem(entityLauncher, "entityLauncher");
        GameRegistry.registerItem(pigConverter, "pigConverter");
        GameRegistry.registerItem(entityBlockTeleporter, "entityBlockTeleporter");
        GameRegistry.registerItem(rubyPickaxe, "rubyPickaxe");
        GameRegistry.registerItem(rubySpade, "rubySpade");
        GameRegistry.registerItem(rubySword, "rubySword");
        GameRegistry.registerItem(rubyAxe, "rubyAxe");
        GameRegistry.registerItem(rubyHoe, "rubyHoe");
        GameRegistry.registerItem(rubyHelmet, "rubyHelmet");
        GameRegistry.registerItem(rubyChestplate, "rubyChestplate");
        GameRegistry.registerItem(rubyLeggings, "rubyLeggings");
        GameRegistry.registerItem(rubyBoots, "rubyBoots");
        GameRegistry.registerItem(card, "card");
        GameRegistry.registerItem(items, "modItems");
        GameRegistry.registerItem(deathstone, "deathstone");

    }
}
