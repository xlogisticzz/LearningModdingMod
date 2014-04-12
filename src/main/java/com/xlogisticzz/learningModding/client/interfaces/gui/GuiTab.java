package com.xlogisticzz.learningModding.client.interfaces.gui;

/**
 * @author xLoGisTicZz
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public abstract class GuiTab extends GuiRectangle {

    private String name;

    public GuiTab(String name, int id) {
        super( 8, 80 + id*16, 20, 16);

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void drawBackground(GuiMachine gui, int x, int y);
    public abstract void drawForeground(GuiMachine gui, int x, int y);
    public void mouseClick(GuiMachine gui, int x, int y, int button){}
    public void mouseMovedClick(GuiMachine gui, int x, int y, int button, long timeSinceClicked){}
    public void mouseReleased(GuiMachine gui, int x, int y, int button){}
}
