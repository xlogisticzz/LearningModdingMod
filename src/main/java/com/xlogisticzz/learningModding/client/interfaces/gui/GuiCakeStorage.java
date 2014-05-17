package com.xlogisticzz.learningModding.client.interfaces.gui;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerCakeStorage;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.network.PacketCakeButtonGui;
import com.xlogisticzz.learningModding.network.PacketPipeline;
import com.xlogisticzz.learningModding.tileEntites.TileEntityCakeStorage;
import com.xlogisticzz.learningModding.utils.StringUtils;
import com.xlogisticzz.learningModding.utils.gui.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class GuiCakeStorage extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Constants.Mod.MODID, "textures/gui/cake.png");
    private static String PLACE = StringUtils.localize("tile.cakeStorage.button.placeCake");
    private static String EAT = StringUtils.localize("tile.cakeStorage.button.eatCake");
    private final Entity cake;
    private TileEntityCakeStorage cakeStorage;
    private GuiButton dispense;
    private float yaw;
    private float roll;
    private boolean rollDown;
    private boolean isDragging;
    private int tempScrollPos;

    public GuiCakeStorage(InventoryPlayer inventoryPlayer, TileEntityCakeStorage cakeStorage) {
        super(new ContainerCakeStorage(inventoryPlayer, cakeStorage));
        this.cakeStorage = cakeStorage;
        cake = new EntityFallingBlock(Minecraft.getMinecraft().theWorld, 0, 0, 0, Blocks.cake);

        xSize = 176;
        ySize = 218;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        drawTexturedModalRect(guiLeft + xSize + 2, guiTop, xSize, 34, 80, 32);
        drawTexturedModalRect(guiLeft + xSize + 2, guiTop + 36, xSize, 66, 80, 80);


        float filled = cakeStorage.getCake() / 10F;
        int barHeight = (int) (filled * 34);
        if (barHeight > 0) {
            int srcX = xSize;
            int srcY = 34 - barHeight;

            drawTexturedModalRect(guiLeft + 100, guiTop + 17 + 34 - barHeight, srcX, srcY, 7, barHeight);
        }

        double timerWidth = ((double) cakeStorage.getTimer() / (double) cakeStorage.getTimerMax()) * 48;
        if (timerWidth >= 0 && timerWidth <= 48) {
            int srcX = xSize + 10;
            int srcY = 0;

            drawTexturedModalRect(guiLeft + 119, guiTop + 44, srcX, srcY, (int) timerWidth, 7);
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

        drawTexturedModalRect(guiLeft + xSize + 7, guiTop + 16, 0, 250, 70, 6);
        drawTexturedModalRect(guiLeft + xSize + 7 + (isDragging ? tempScrollPos : cakeStorage.getTimerMax()), guiTop + 13, 70, 245, 6, 11);

        drawCake();


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

        String masterCake = StringUtils.localize("tile.cakeStorage.gui.masterCake");
        GuiUtils.drawCenteredString(fontRendererObj, masterCake, 218, 42, 0x404040);

        String timerSelector = StringUtils.localize("tile.cakeStorage.gui.maxTimer");
        GuiUtils.drawCenteredString(fontRendererObj, timerSelector, 218, 5, 0x404040);

        if (!cakeStorage.isAirInCurrentDir()) {
            Block block = cakeStorage.getBlockAtCurrentPos();
            String str = "Cannot place Cake " + cakeStorage.getCurrentTextDir().toLowerCase() + " as the block is occupied by " + block.getLocalizedName();
            fontRendererObj.drawSplitString(str, 70, 84, 100, 0x404040);
        }

        fontRendererObj.drawString("Buffer", 19, 106, 0x404040);
        GuiUtils.drawCenteredString(fontRendererObj, Integer.toString(cakeStorage.getBufferMax()), 35, 116, 0x404040);
        GuiUtils.drawCenteredString(fontRendererObj, Double.toString(((double) cakeStorage.getTimerMax() * 5) / 20), 143, 20, 0x404040);
        GuiUtils.drawCenteredString(fontRendererObj, "secs", 143, 30, 0x404040);
    }

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

//        buttonList.add(new GuiButton(2, guiLeft + 114, guiTop + 16, 12, 20, "<"));
//        buttonList.add(new GuiButton(3, guiLeft + 160, guiTop + 16, 12, 20, ">"));

        buttonList.add(new GuiButton(4, guiLeft + 5, guiTop + 106, 12, 20, "<"));
        buttonList.add(new GuiButton(5, guiLeft + 53, guiTop + 106, 12, 20, ">"));

        String stateName = cakeStorage.getPlace() ? PLACE : EAT;
        buttonList.add(new GuiButton(6, guiLeft + 108, guiTop + 58, 64, 20, stateName));


    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        PacketPipeline.sendToServer(new PacketCakeButtonGui(0, par1GuiButton.id, isShiftKeyDown(), isCtrlKeyDown()));
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
        } else if (par1GuiButton.id == 2) {
            cakeStorage.decreaseMaxTime(isShiftKeyDown(), isCtrlKeyDown());
        } else if (par1GuiButton.id == 3) {
            cakeStorage.increaseMaxTime(isShiftKeyDown(), isCtrlKeyDown());
        } else if (par1GuiButton.id == 4) {
            cakeStorage.decreaseMaxBuffer(isShiftKeyDown(), isCtrlKeyDown());
        } else if (par1GuiButton.id == 5) {
            cakeStorage.increaseMaxBuffer(isShiftKeyDown(), isCtrlKeyDown());
        } else if (par1GuiButton.id == 6) {
            cakeStorage.setPlace(!cakeStorage.getPlace());
            par1GuiButton.displayString = par1GuiButton.displayString.equals(EAT) ? PLACE : EAT;
        }
    }

    private void drawCake() {

        GL11.glPushMatrix();

        GL11.glTranslatef(guiLeft + 216, guiTop + 80, 100);

        float scale = 50F;
        GL11.glScalef(-scale, scale, scale);

        RenderHelper.enableStandardItemLighting();

        GL11.glRotatef(180, 0, 0, 1);
        GL11.glRotatef(roll, 1, 0, 0);
        GL11.glRotatef(yaw, 0, 1, 0);

        RenderManager.instance.renderEntityWithPosYaw(cake, 0, 0, 0, 0, 0);

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
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);

        x -= guiLeft;
        y -= guiTop;

        if (182 + cakeStorage.getTimerMax() <= x && x <= 189 + cakeStorage.getTimerMax() && 13 <= y && y <= 24) {
            isDragging = true;
            tempScrollPos = cakeStorage.getTimerMax();
        }
    }

    @Override
    protected void mouseClickMove(int x, int y, int button, long timeSinceClicked) {
        super.mouseClickMove(x, y, button, timeSinceClicked);

        x -= guiLeft;
        y -= guiTop;

        if (isDragging) {
            tempScrollPos = x - 185;
            if (tempScrollPos < 0) {
                tempScrollPos = 0;
            } else if (tempScrollPos > 64) {
                tempScrollPos = 64;
            }
        }
    }

    @Override
    protected void mouseMovedOrUp(int x, int y, int button) {
        super.mouseMovedOrUp(x, y, button);

        if (isDragging) {
            PacketPipeline.sendToServer(new PacketCakeButtonGui(1, tempScrollPos, isShiftKeyDown(), isCtrlKeyDown()));
            cakeStorage.setTimerMax(tempScrollPos);
            isDragging = false;
        }
    }
}