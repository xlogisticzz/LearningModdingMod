package com.xlogisticzz.learningModding.client.interfaces.gui;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import java.util.Arrays;

public class GuiRectangle {

    private int x;
    private int y;
    private int w;
    private int h;

    public GuiRectangle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean inRect(GuiMachine guiMachine, int mouseX, int mouseY) {
        mouseX -= guiMachine.getLeft();
        mouseY -= guiMachine.getTop();

        return x <= mouseX && mouseX <= x + w && y <= mouseY && mouseY <= y + h;
    }

    public void draw(GuiMachine guiMachine, int srcX, int srcY) {
        guiMachine.drawTexturedModalRect(guiMachine.getLeft() + x, guiMachine.getTop() + y, srcX, srcY, w, h);
    }

    public void drawHoverText(GuiMachine guiMachine, int x, int y, String string){
        if(inRect(guiMachine, x ,y)){
            guiMachine.drawHoverString(Arrays.asList(string.split("\n")), x - guiMachine.getLeft(), y  - guiMachine.getTop());
        }
    }
}
