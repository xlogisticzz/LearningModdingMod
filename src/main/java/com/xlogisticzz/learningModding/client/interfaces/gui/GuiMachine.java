package com.xlogisticzz.learningModding.client.interfaces.gui;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.client.interfaces.containers.ContainerMachine;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.network.PacketHandler;
import com.xlogisticzz.learningModding.tileEntites.TileEntityMachine;
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

    private TileEntityMachine entityMachine;

    public GuiMachine(InventoryPlayer inventoryPlayer, TileEntityMachine entityMachine) {
        super(new ContainerMachine(inventoryPlayer, entityMachine));
        this.entityMachine = entityMachine;

        xSize = 176;
        ySize = 154;
    }

    private static final ResourceLocation texture = new ResourceLocation(Constants.Mod.MODID, "textures/gui/machine.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int meta = entityMachine.worldObj.getBlockMetadata(entityMachine.xCoord, entityMachine.yCoord, entityMachine.zCoord);
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

        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        drawTexturedModelRectFromIcon(guiLeft + 63, guiTop + 17, ModBlocks.machineblock.getIcon(1, meta), 16, 16);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRenderer.drawString("Machine", 8, 6, 0x404040);

        int type = entityMachine.worldObj.getBlockMetadata(entityMachine.xCoord, entityMachine.yCoord, entityMachine.zCoord) / 2;

        String string;
        boolean invalid = true;
        if (type == 0) {
            string = "No Type Selected";
        } else {
            int count = 0;
            if (type == 1) {
                count = 5;
            } else {
                count = 12;
            }
            if (entityMachine.getGravel() >= count) {
                invalid = false;
            }
            string = "Requires " + count + " gravel blocks per drop";
        }
        int colour = invalid ? 0xD30000 : 0x404040;
        fontRenderer.drawSplitString(string, 45, 44, 100, colour);
    }

    private static final String ENABLE_TEXT = "Enable";
    private static final String DISABLE_TEXT = "Disable";

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();

        buttonList.add(new GuiButton(0, guiLeft + 80, guiTop + 14, 48, 20, entityMachine.getBlockMetadata() % 2 == 0 ? DISABLE_TEXT : ENABLE_TEXT));

        GuiButton clear = new GuiButton(1, guiLeft + 130, guiTop + 14, 40, 20, "Clear");
        clear.enabled = entityMachine.getBlockMetadata() / 2 != 0;
        buttonList.add(clear);
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        PacketHandler.sendMachineButtonPacket((byte) par1GuiButton.id);
        if (par1GuiButton.id == 0) {
            par1GuiButton.displayString = par1GuiButton.displayString.equals(DISABLE_TEXT) ? ENABLE_TEXT : DISABLE_TEXT;
        } else if (par1GuiButton.id == 1) {
            par1GuiButton.enabled = false;
        }
    }
}
