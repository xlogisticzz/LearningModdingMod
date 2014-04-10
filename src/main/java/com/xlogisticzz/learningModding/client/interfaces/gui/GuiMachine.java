package com.xlogisticzz.learningModding.client.interfaces.gui;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerMachine;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.network.PacketMachineGui;
import com.xlogisticzz.learningModding.network.PacketPipeline;
import com.xlogisticzz.learningModding.tileEntites.TileEntityMachine;
import com.xlogisticzz.learningModding.utils.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiMachine extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation(Constants.Mod.MODID, "textures/gui/machine.png");
    private static final GuiRectangle[] rectangles;
    static {
        rectangles = new GuiRectangle[49];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                rectangles[i * 7 + j] = new GuiRectangle(57 + i * 9, 70 + j * 9, 8, 8);
            }
        }
    }
    private static final String ENABLE_TEXT = StringUtils.localize("tile.machineBlock.button.enable");
    private static final String DISABLE_TEXT = StringUtils.localize("tile.machineBlock.button.disable");
    private TileEntityMachine entityMachine;
    private boolean currentDragMode;

    public GuiMachine(InventoryPlayer inventoryPlayer, TileEntityMachine entityMachine) {
        super(new ContainerMachine(inventoryPlayer, entityMachine));
        this.entityMachine = entityMachine;

        xSize = 176;
        ySize = 218;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int meta = entityMachine.getWorldObj().getBlockMetadata(entityMachine.xCoord, entityMachine.yCoord, entityMachine.zCoord);
        int type = meta / 2;

        if (type != 0) {
            int srcX = 20 * (type - 1);
            int srcY = ySize;

            drawTexturedModalRect(guiLeft + 16, guiTop + 42, srcX, srcY, 20, 20);
        }

        float filled = entityMachine.getGravel() / 192F;
        int barHeight = (int) (filled * 27);
        if (barHeight > 0) {
            int srcX = xSize;
            int srcY = 27 - barHeight;

            drawTexturedModalRect(guiLeft + 157, guiTop + 40 + 27 - barHeight, srcX, srcY, 7, barHeight);
        }

        if (type == 4) {
            for (int i = 0; i < rectangles.length; i++) {
                GuiRectangle rect = rectangles[i];
                int srcX = xSize;

                if (rect.inRect(this, x, y)) {
                    srcX += 8;
                }

                rect.draw(this, srcX, 27);

                if (entityMachine.customSetup[i]) {
                    rect.draw(this, xSize, 35);
                }
            }

        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        drawTexturedModelRectFromIcon(guiLeft + 63, guiTop + 17, ModBlocks.machineblock.getIcon(1, meta), 16, 16);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        String title = StringUtils.localize("tile.machineBlock.name");
        fontRendererObj.drawString(title, 8, 6, 0x404040);

        int type = entityMachine.getWorldObj().getBlockMetadata(entityMachine.xCoord, entityMachine.yCoord, entityMachine.zCoord) / 2;

        String str;
        boolean invalid = true;
        if (type == 0) {
            str = "No type selected";
        } else {
            int count;
            if (type == 1) {
                count = 5;
            } else if (type == 4) {
                count = entityMachine.getCustomGravel();
            } else {
                count = 12;
            }

            if (entityMachine.getGravel() >= count) {
                invalid = false;
            }

            str = "Requires " + count + " gravel blocks per drop";
        }

        int color = invalid ? 0xD30000 : 0x404040;
        fontRendererObj.drawSplitString(str, 45, 44, 100, color);
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();

        buttonList.add(new GuiButton(0, guiLeft + 80, guiTop + 14, 48, 20, entityMachine.getBlockMetadata() % 2 == 0 ? DISABLE_TEXT : ENABLE_TEXT));

        String clearName = StringUtils.localize("tile.machineBlock.button.clear");
        GuiButton clear = new GuiButton(1, guiLeft + 130, guiTop + 14, 40, 20, clearName);
        clear.enabled = entityMachine.getBlockMetadata() / 2 != 0;
        buttonList.add(clear);
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        PacketPipeline.sendToServer(new PacketMachineGui(par1GuiButton.id));
        if (par1GuiButton.id == 0) {
            par1GuiButton.displayString = par1GuiButton.displayString.equals(DISABLE_TEXT) ? ENABLE_TEXT : DISABLE_TEXT;
        } else if (par1GuiButton.id == 1) {
            par1GuiButton.enabled = false;
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);

        for (int i = 0; i < rectangles.length; i++) {
            GuiRectangle rect = rectangles[i];

            if (rect.inRect(this, x, y)) {
                PacketPipeline.sendToServer(new PacketMachineGui(2 + i));
                currentDragMode = entityMachine.customSetup[i];
                entityMachine.setCustomGravel(i, !currentDragMode);
                break;
            }
        }
    }

    @Override
    protected void mouseClickMove(int x, int y, int button, long timeSinceClick) {
        super.mouseClickMove(x, y, button, timeSinceClick);

        for (int i = 0; i < rectangles.length; i++) {
            GuiRectangle rect = rectangles[i];

            if (entityMachine.customSetup[i] == currentDragMode && rect.inRect(this, x, y)) {
                PacketPipeline.sendToServer(new PacketMachineGui(2 + i));
                entityMachine.setCustomGravel(i, !currentDragMode);
                break;
            }
        }
    }

    protected int getLeft() {
        return guiLeft;
    }

    protected int getTop() {
        return guiTop;
    }
}
