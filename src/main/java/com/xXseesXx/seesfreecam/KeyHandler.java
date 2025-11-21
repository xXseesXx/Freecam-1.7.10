package com.xXseesXx.seesfreecam;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyHandler {

    public static KeyBinding freecamKey;
    private static long lastF5Press = 0;
    private static final long F5_COOLDOWN = 200; // milliseconds

    public static void registerKeys() {
        freecamKey = new KeyBinding("Toggle Freecam", Keyboard.KEY_F4, "Freecam");
        ClientRegistry.registerKeyBinding(freecamKey);
    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        if (freecamKey.isPressed()) {
            FreecamHandler.toggleFreecam();
        }

        // Intercept F5 while in freecam to toggle movement mode
        if (FreecamHandler.isFreecamEnabled() && Keyboard.isKeyDown(Keyboard.KEY_F5)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastF5Press > F5_COOLDOWN) {
                FreecamHandler.toggleMovementMode();
                lastF5Press = currentTime;

                // Immediately reset perspective to prevent change
                Minecraft mc = Minecraft.getMinecraft();
                mc.gameSettings.thirdPersonView = 0;
            }
        }
    }
}
