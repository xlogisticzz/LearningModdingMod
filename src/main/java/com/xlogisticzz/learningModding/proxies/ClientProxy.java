package com.xlogisticzz.learningModding.proxies;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.RenderSnowball;

import com.xlogisticzz.learningModding.client.RenderSpaceship;
import com.xlogisticzz.learningModding.client.sounds.SoundHandler;
import com.xlogisticzz.learningModding.entities.EntityBlockEntityTeleport;
import com.xlogisticzz.learningModding.entities.EntityPigConverter;
import com.xlogisticzz.learningModding.entities.EntitySpaceship;
import com.xlogisticzz.learningModding.items.ModItems;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class ClientProxy extends CommonProxy {
    
    @Override
    public void initSounds() {
    
        new SoundHandler();
    }
    
    @Override
    public void initRenderers() {
    
        RenderingRegistry.registerEntityRenderingHandler(EntitySpaceship.class, new RenderSpaceship());
        RenderingRegistry.registerEntityRenderingHandler(EntityPigConverter.class, new RenderSnowball(ModItems.pigConverter));
        RenderingRegistry.registerEntityRenderingHandler(EntityBlockEntityTeleport.class, new RenderSnowball(ModItems.entityBlockTeleporter));
        
    }

    @Override
    public Object getClient() {
        return FMLClientHandler.instance().getClient();
    }

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    public void removeEntity(Entity entity) {
        super.removeEntity(entity);

        if(entity.worldObj.isRemote){
            ((WorldClient) entity.worldObj).removeEntityFromWorld(entity.entityId);
        }
    }

    @Override
    public String getCurrentLang() {
        return Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode();
    }


}
