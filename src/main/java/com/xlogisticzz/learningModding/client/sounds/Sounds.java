package com.xlogisticzz.learningModding.client.sounds;

import net.minecraft.client.Minecraft;

public enum Sounds {
    BOMB_SPREAD("beep"), BOMB_DROP("bombfall"), OUT_OF_AMMO("emptyclick"), WAND_USE("wand");

    public static final String SOUNDS_LOCATION = "learningmodding";
    private String name;

    Sounds(String name) {

        this.name = name;
    }

    public String getName() {

        return this.name;
    }

    public void play(double x, double y, double z, float volume, float pitch) {

        Minecraft.getMinecraft().sndManager.playSound(SOUNDS_LOCATION + ":" + this.name, (float) x, (float) y, (float) z, volume, pitch);
    }
}
