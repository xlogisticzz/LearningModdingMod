package com.xlogisticzz.learningModding;

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.configuration.ConfigurationHandler;
import com.xlogisticzz.learningModding.crafting.Recipies;
import com.xlogisticzz.learningModding.dispenser.DispenserBehaviourBlockEntityTeleport;
import com.xlogisticzz.learningModding.entities.ModEntities;
import com.xlogisticzz.learningModding.handler.EntityStruckByLightningEventHandler;
import com.xlogisticzz.learningModding.handler.GuiHandler;
import com.xlogisticzz.learningModding.items.ModItems;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.network.PacketPipeline;
import com.xlogisticzz.learningModding.proxies.CommonProxy;
import com.xlogisticzz.learningModding.utils.LogHelper;
import com.xlogisticzz.learningModding.world.WorldGenerationHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import java.io.File;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(modid = Constants.Mod.MODID, name = Constants.Mod.MODID, version = Constants.Mod.VERSION)
public class LearningModding {

    public static final PacketPipeline packetPipeline = new PacketPipeline();
    /* Mod instance */
    @Instance(Constants.Mod.MODID)
    public static LearningModding instance;
    /* Says where the client and server 'proxy' code is loaded. */
    @SidedProxy(clientSide = Constants.Proxies.CLIENT, serverSide = Constants.Proxies.COMMON)
    public static CommonProxy proxy;

    public static KeyBinding dropBomb;

    /* PreInitialization */
    @EventHandler
    public void PreInt(FMLPreInitializationEvent event) {

        ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Constants.Mod.CHANNEL_NAME + File.separator + Constants.Mod.MODID + ".cfg"));

        ModItems.init();
        ModBlocks.init();

        proxy.initSounds();
        proxy.initRenderers();

        MinecraftForge.EVENT_BUS.register(new EntityStruckByLightningEventHandler());


    }

    /* Initialisation */
    @EventHandler
    public void load(FMLInitializationEvent event) {

        dropBomb = new KeyBinding("SpaceShip Inventory", Keyboard.KEY_F, "Learning Modding");
        ClientRegistry.registerKeyBinding(dropBomb);
        packetPipeline.initialize();
        packetPipeline.registerPackets();

        ModEntities.init();
        Recipies.initVanilla();
        ModEntities.initInfo();
        new WorldGenerationHandler();
        new GuiHandler();

        BlockDispenser.dispenseBehaviorRegistry.putObject(ModItems.entityBlockTeleporter, new DispenserBehaviourBlockEntityTeleport());

    }

    /* PostInitialization */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        packetPipeline.postInitialise();
        LogHelper.info("Learning Modding loaded");
    }

    /* When the server starts */
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {

    }

}
