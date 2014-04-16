package com.xlogisticzz.learningModding.client.interfaces.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import org.lwjgl.opengl.GL11;

/**
 * @author xLoGisTicZz
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

@SideOnly(Side.CLIENT)
public class GuiTabPreview extends GuiTab {

    private final Entity gravel;

    private float yaw;
    private float roll;
    private boolean rollDown;

    public GuiTabPreview(int id) {
        super("Gravel Preview", id);
        gravel = new EntityFallingBlock(Minecraft.getMinecraft().theWorld, 0, 0, 0, Blocks.gravel);
    }


    @Override
    public void drawBackground(GuiMachine gui, int x, int y) {
        GL11.glPushMatrix();

        GL11.glTranslatef(gui.getLeft() + 90, gui.getTop() + 100, 100);

        float scale = 30F;
        GL11.glScalef(-scale, scale, scale);

        RenderHelper.enableStandardItemLighting();

        GL11.glRotatef(180, 0, 0, 1);
        GL11.glRotatef(roll, 1, 0, 0);
        GL11.glRotatef(yaw, 0, 1, 0);

        RenderManager.instance.renderEntityWithPosYaw(gravel, 0, 0, 0, 0, 0);

        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();

        yaw += 0.5F;
        if (rollDown) {
            roll -= 0.05F;
            if (roll < -5) {
                rollDown = false;
                roll = -5;
            }
        } else {
            roll += 0.05F;
            if (roll > 25) {
                rollDown = true;
                roll = 25;
            }
        }
    }

    @Override
    public void drawForeground(GuiMachine gui, int x, int y) {

    }
}
