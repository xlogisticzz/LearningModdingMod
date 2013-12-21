package com.xlogisticzz.learningModding.client.interfaces.gui;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.client.interfaces.contaners.ContainerMachine;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.tileEntites.TileEntityMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiMachine extends GuiContainer {

    public GuiMachine(InventoryPlayer inventoryPlayer, TileEntityMachine entityMachine) {
        super(new ContainerMachine(inventoryPlayer, entityMachine));

        xSize = 176;
        ySize = 154;
    }

    private static final ResourceLocation texture = new ResourceLocation(Constants.Mod.MODID, "textures/gui/machine.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

    }


}
