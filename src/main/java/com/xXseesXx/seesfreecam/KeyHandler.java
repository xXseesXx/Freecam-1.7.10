package com.xXseesXx.seesfreecam;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyHandler {

    public static KeyBinding freecamKey;

    public static void registerKeys() {
        freecamKey = new KeyBinding("Toggle Freecam", Keyboard.KEY_F4, "Freecam");
        ClientRegistry.registerKeyBinding(freecamKey);
    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        if (freecamKey.isPressed()) {
            FreecamHandler.toggleFreecam();
        }
    }
}
