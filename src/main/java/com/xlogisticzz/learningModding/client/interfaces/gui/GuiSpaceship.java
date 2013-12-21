package com.xlogisticzz.learningModding.client.interfaces.gui;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerSpaceship;
import com.xlogisticzz.learningModding.entities.EntitySpaceship;
import com.xlogisticzz.learningModding.lib.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiSpaceship extends GuiContainer {

    public GuiSpaceship(InventoryPlayer inventoryPlayer, EntitySpaceship spaceship) {
        super(new ContainerSpaceship(inventoryPlayer, spaceship));

        xSize = 176;
        ySize = 218;
    }

    private static final ResourceLocation texture = new ResourceLocation(Constants.Mod.MODID, "textures/gui/cake.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
