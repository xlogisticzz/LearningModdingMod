package com.xlogisticzz.learningModding.client.interfaces.gui;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerSpaceship;
import com.xlogisticzz.learningModding.entities.EntitySpaceship;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.utils.StringUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiSpaceship extends GuiContainer {

    //TODO CREATE GUI TEXTURE AND UPDATE HERE
    private static final ResourceLocation texture = new ResourceLocation(Constants.Mod.MODID, "textures/gui/spaceship.png");

    public GuiSpaceship(InventoryPlayer inventoryPlayer, EntitySpaceship spaceship) {
        super(new ContainerSpaceship(inventoryPlayer, spaceship));

        xSize = 176;
        ySize = 218;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String title = StringUtils.localize("entity.learningModdingMod:EntitySpaceship.name");
        fontRendererObj.drawString(title, 8, 6, 0x404040);
    }
}
