package com.xlogisticzz.learningModding.client;

import com.xlogisticzz.learningModding.entities.EntitySpaceship;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz / this class is from VSWE's Entities lecture and this code belongs to him. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

@SideOnly(Side.CLIENT)
public class RenderSpaceship extends Render {

    private static final ResourceLocation texture = new ResourceLocation(Constants.Mod.MODID, "textures/entities/spaceship.png");
    private static final ResourceLocation chargedTexture = new ResourceLocation(Constants.Mod.MODID, "textures/entities/spaceship_charged.png");

    protected ModelSpaceship model;

    public RenderSpaceship() {

        this.shadowSize = 0.5F;
        this.model = new ModelSpaceship();
    }

    public void renderSpaceship(EntitySpaceship spaceship, double x, double y, double z, float yaw, float partialTickTime) {

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);

        bindEntityTexture(spaceship);

        this.model.render(spaceship, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {

        this.renderSpaceship((EntitySpaceship) entity, x, y, z, yaw, partialTickTime);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {

        return ((EntitySpaceship) entity).isCharged() ? chargedTexture : texture;
    }
}
