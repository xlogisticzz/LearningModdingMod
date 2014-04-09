package com.xlogisticzz.learningModding.client.interfaces.gui;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerCakeStorage;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.network.PacketCakeButton;
import com.xlogisticzz.learningModding.network.PacketPipeline;
import com.xlogisticzz.learningModding.tileEntites.TileEntityCakeStorage;
import com.xlogisticzz.learningModding.utils.GuiUtils;
import com.xlogisticzz.learningModding.utils.StringUtils;
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

        double timerWidth = ((double)cakeStorage.getTimer()/ (double)cakeStorage.getTimerMax()) * 48;
        if (timerWidth >= 0 && timerWidth <= 48) {
            int srcX = xSize + 10;
            int srcY = 0;

            drawTexturedModalRect(guiLeft + 119, guiTop + 44, srcX, srcY, (int)timerWidth, 7);
        }

        filled = cakeStorage.getBuffer() / (float) cakeStorage.getBufferMax();
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
        String title = StringUtils.localize("tile.cakeStorage.name");
        fontRendererObj.drawString(title, 8, 6, 0x404040);

        if (!cakeStorage.isAirInCurrentDir()) {
            Block block = cakeStorage.getBlockAtCurrentPos();
            String str = "Cannot place Cake " + cakeStorage.getCurrentTextDir().toLowerCase() + " as the block is occupied by " + block.getLocalizedName();
            fontRendererObj.drawSplitString(str, 70, 84, 100, 0x404040);
        }

        fontRendererObj.drawString("Buffer", 19, 106, 0x404040);
        GuiUtils.drawCenteredString(fontRendererObj, Integer.toString(cakeStorage.getBufferMax()), 35, 116, 0x404040);

        fontRendererObj.drawString(Double.toString(((double)cakeStorage.getTimerMax()* 5)/20), 130, 20, 0x404040);
        fontRendererObj.drawString("secs", 130, 30, 0x404040);
    }

    private GuiButton dispense;

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();

        String dirName = StringUtils.localize("tile.cakeStorage.button.changeDir");

        GuiButton changeDir = new GuiButton(1, guiLeft + 5, guiTop + 58, 60, 20, dirName);
        buttonList.add(changeDir);

        String dispenseName = StringUtils.localize("tile.cakeStorage.button.dispense");
        dispense = new GuiButton(0, guiLeft + 5, guiTop + 82, 60, 20, dispenseName);
        dispense.enabled = cakeStorage.isAirInCurrentDir();

        buttonList.add(dispense);

        buttonList.add(new GuiButton(2, guiLeft + 114, guiTop + 16, 12, 20, "<"));
        buttonList.add(new GuiButton(3, guiLeft + 158, guiTop + 16, 12, 20, ">"));

        buttonList.add(new GuiButton(4, guiLeft + 5, guiTop + 106, 12, 20, "<"));
        buttonList.add(new GuiButton(5, guiLeft + 53, guiTop + 106, 12, 20, ">"));


    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        PacketPipeline.sendToServer(new PacketCakeButton(par1GuiButton.id, isShiftKeyDown(), isCtrlKeyDown()));
        if (par1GuiButton.id == 0) {
            par1GuiButton.enabled = false;
        } else if (par1GuiButton.id == 1) {
            par1GuiButton.enabled = true;
            cakeStorage.increaseDir();
            if (cakeStorage.isAirInCurrentDir()) {
                dispense.enabled = true;
            } else {
                dispense.enabled = false;
            }
        } else if (par1GuiButton.id == 2){
            cakeStorage.decreaseMaxTime(isShiftKeyDown(), isCtrlKeyDown());
        } else if (par1GuiButton.id == 3){
            cakeStorage.increaseMaxTime(isShiftKeyDown(), isCtrlKeyDown());
        }else if (par1GuiButton.id == 4){
            cakeStorage.decreaseMaxBuffer(isShiftKeyDown(), isCtrlKeyDown());
        } else if (par1GuiButton.id == 5){
            cakeStorage.increaseMaxBuffer(isShiftKeyDown(), isCtrlKeyDown());
        }
    }
}
