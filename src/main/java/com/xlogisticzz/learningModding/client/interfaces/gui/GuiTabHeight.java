package com.xlogisticzz.learningModding.client.interfaces.gui;

import com.xlogisticzz.learningModding.network.PacketMachineInterfaceGui;
import com.xlogisticzz.learningModding.network.PacketPipeline;
import com.xlogisticzz.learningModding.utils.gui.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author xLoGisTicZz
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

@SideOnly(Side.CLIENT)
public class GuiTabHeight extends GuiTab {

    private static final GuiRectangle bar = new GuiRectangle(50, 100, 91, 6);
    private static final GuiRectangle slider = new GuiRectangle(75, 97, 6, 11);
    private int tempHeightSetting;
    private boolean isDragging;

    public GuiTabHeight(int id) {
        super("Height Setup", id);
    }

    @Override
    public void drawBackground(GuiMachine gui, int x, int y) {
        bar.draw(gui, 0, 250);
        updateSliderPos(gui);
        slider.draw(gui, 0, 239);
    }

    @Override
    public void drawForeground(GuiMachine gui, int x, int y) {
        GuiUtils.drawCenteredString(gui.getFontRenderer(), "Height: " + getCurrentHeight(gui), 96, 88, 0x404040);
    }

    @Override
    public void mouseClick(GuiMachine gui, int x, int y, int button) {
        updateSliderPos(gui);
        if (slider.inRect(gui, x, y)) {
            isDragging = true;
            tempHeightSetting = gui.getMachine().height;
        }
    }

    @Override
    public void mouseMovedClick(GuiMachine gui, int x, int y, int button, long timeSinceClicked) {
        if (isDragging) {
            tempHeightSetting = x - gui.getLeft() - 50;
            if (tempHeightSetting < 0) {
                tempHeightSetting = 0;
            } else if (tempHeightSetting > 86) {
                tempHeightSetting = 85;
            }
        }
    }

    @Override
    public void mouseReleased(GuiMachine gui, int x, int y, int button) {
        if (isDragging) {
            PacketPipeline.sendToServer(new PacketMachineInterfaceGui(2, tempHeightSetting));
            gui.getMachine().height = tempHeightSetting;
            isDragging = false;
        }
    }

    private void updateSliderPos(GuiMachine gui) {
        slider.setX(50 + (isDragging ? tempHeightSetting : gui.getMachine().height));
    }

    private int getCurrentHeight(GuiMachine gui) {
        return isDragging ? tempHeightSetting : gui.getMachine().height;
    }
}
