package com.xlogisticzz.learningModding.client.interfaces.gui;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerCakeStorage;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.tileEntites.TileEntityCakeStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class GuiCakeStorage extends GuiContainer {
    private TileEntityCakeStorage cakeStorage;

    public GuiCakeStorage(InventoryPlayer inventoryPlayer, TileEntityCakeStorage cakeStorage) {
        super(new ContainerCakeStorage(inventoryPlayer, cakeStorage));
        this.cakeStorage = cakeStorage;

        xSize = 176;
        ySize = 218;
    }

    private static final ResourceLocation texture = new ResourceLocation(Constants.Mod.MODID, "textures/gui/cake.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        float filled = cakeStorage.getCake() / 10F;
        int barHeight = (int) (filled * 34);
        if (barHeight > 0) {
            int srcX = xSize;
            int srcY = 34 - barHeight;

            drawTexturedModalRect(guiLeft + 100, guiTop + 17 + 34 - barHeight, srcX, srcY, 7, barHeight);
        }

        int timerWidth = cakeStorage.getTimer();
        if (timerWidth > 0) {
            int srcX = xSize + 10;
            int srcY = 0;

            drawTexturedModalRect(guiLeft + 119, guiTop + 44, srcX, srcY, timerWidth, 7);
        }

        filled = cakeStorage.getBuffer() / 6F;
        barHeight = (int) (filled * 34);
        if (barHeight > 0) {
            int srcX = xSize + 7;
            int srcY = 34 - barHeight;

            drawTexturedModalRect(guiLeft + 108, guiTop + 17 + 34 - barHeight, srcX, srcY, 3, barHeight);
        }


        int srcX = cakeStorage.getCurrentDir() * 20;
        int srcY = ySize;
        drawTexturedModalRect(guiLeft + 70, guiTop + 58, srcX, srcY, 20, 22);


        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);

        if (!cakeStorage.isAirInCurrentDir()) {
            Block block = cakeStorage.getBlockAtCurrentPos();
            if (block != null) {
                drawTexturedModelRectFromIcon(guiLeft + 90, guiTop + 60, block.getIcon(1, 0), 16, 16);
            }
        }

    }


    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRendererObj.drawString("Cake Storage", 8, 6, 0x404040);

        if (!cakeStorage.isAirInCurrentDir()) {
            Block block = cakeStorage.getBlockAtCurrentPos();
            String str = "Cannot place Cake " + cakeStorage.getCurrentTextDir().toLowerCase() + " as the block is occupied by " + block.getLocalizedName();
            fontRendererObj.drawSplitString(str, 70, 84, 100, 0x404040);
        }
    }

    private GuiButton place;

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();

        place = new GuiButton(0, guiLeft + 5, guiTop + 82, 60, 20, "Place Cake");
        place.enabled = cakeStorage.isAirInCurrentDir();
        buttonList.add(place);

        GuiButton changeDir = new GuiButton(1, guiLeft + 5, guiTop + 58, 60, 20, "Change Dir");
        buttonList.add(changeDir);

        buttonList.add(new GuiButton(2, guiLeft + 5, guiTop + 106, 50, 20, "Dispense"));

    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        //TODO sendCakeButtonPacket((byte) par1GuiButton.id);
        if (par1GuiButton.id == 0) {
            par1GuiButton.enabled = false;
        } else if (par1GuiButton.id == 1) {
            par1GuiButton.enabled = true;
            cakeStorage.increaseDir();
            if (cakeStorage.isAirInCurrentDir()) {
                place.enabled = true;
            } else {
                place.enabled = false;
            }
        }
    }
}
