package com.xlogisticzz.learningModding.client.keyBinds;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.settings.KeyBinding;

import java.util.EnumSet;

public class SpaceshipInventoryKeyBind extends KeyBindingRegistry.KeyHandler {

    private EnumSet tickTypes = EnumSet.of(TickType.CLIENT);

    public static boolean keyPressed = false;
    private boolean keyHasBeenPressed;

    public SpaceshipInventoryKeyBind(KeyBinding[] keyBindings, boolean[] repeatings) {
        super(keyBindings, repeatings);
    }

    @Override
    public String getLabel() {
        return "SpaceShip Inventory";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
        keyHasBeenPressed = true;

    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
        if (keyHasBeenPressed) {
            keyHasBeenPressed = false;
            keyPressed = true;
        }
    }

    @Override
    public EnumSet<TickType> ticks() {
        return tickTypes;
    }


}
