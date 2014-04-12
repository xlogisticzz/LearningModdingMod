package com.xlogisticzz.learningModding.client.interfaces.gui;

import com.xlogisticzz.learningModding.network.PacketMachineGui;
import com.xlogisticzz.learningModding.network.PacketPipeline;
import com.xlogisticzz.learningModding.utils.gui.GuiColour;

/**
 * @author xLoGisTicZz
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public class GuiTabCustom extends GuiTab {

    private boolean currentDragMode;

    public GuiTabCustom(int id) {
        super("Custom Layout", id);
    }

    @Override
    public void drawBackground(GuiMachine gui, int x, int y) {
        int meta = gui.getMachine().getWorldObj().getBlockMetadata(gui.getMachine().xCoord, gui.getMachine().yCoord, gui.getMachine().zCoord);
        int type = meta / 2;

        if (type == 4) {
            for (int i = 0; i < GuiMachine.getRectangles().length; i++) {
                GuiRectangle rect = GuiMachine.getRectangles()[i];
                int srcX = 176;

                if (rect.inRect(gui, x, y)) {
                    srcX += 8;
                }

                rect.draw(gui, srcX, 27);

                if (gui.getMachine().customSetup[i]) {
                    rect.draw(gui, 176, 35);
                }
            }

        }
    }

    @Override
    public void drawForeground(GuiMachine gui, int x, int y) {
        int meta = gui.getMachine().getWorldObj().getBlockMetadata(gui.getMachine().xCoord, gui.getMachine().yCoord, gui.getMachine().zCoord);
        int type = meta / 2;

        if(type == 4){
            for(int i = 0; i < GuiMachine.getRectangles().length; i++){
                GuiRectangle rectangle = GuiMachine.getRectangles()[i];
                String text;
                if(gui.getMachine().customSetup[i]){
                    text = GuiColour.GREEN + "Active";
                } else {
                    text = GuiColour.RED + "Inactive";
                }
                text += "\n" + GuiColour.YELLOW + "Click to change";
                rectangle.drawHoverText(gui, x, y, text);
            }
        }

    }

    @Override
    public void mouseClick(GuiMachine gui, int x, int y, int button) {

        for (int i = 0; i < GuiMachine.getRectangles().length; i++) {
            GuiRectangle rect = GuiMachine.getRectangles()[i];

            if (rect.inRect(gui, x, y)) {
                PacketPipeline.sendToServer(new PacketMachineGui(2 + i));
                currentDragMode = gui.getMachine().customSetup[i];
                gui.getMachine().setCustomGravel(i, !currentDragMode);
                break;
            }
        }
    }

    @Override
    public void mouseMovedClick(GuiMachine gui, int x, int y, int button, long timeSinceClicked) {
        for (int i = 0; i < GuiMachine.getRectangles().length; i++) {
            GuiRectangle rect = GuiMachine.getRectangles()[i];

            if (gui.getMachine().customSetup[i] == currentDragMode && rect.inRect(gui, x, y)) {
                PacketPipeline.sendToServer(new PacketMachineGui(2 + i));
                gui.getMachine().setCustomGravel(i, !currentDragMode);
                break;
            }
        }
    }
}
