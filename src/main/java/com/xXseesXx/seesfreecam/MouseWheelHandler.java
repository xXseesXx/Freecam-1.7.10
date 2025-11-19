package com.xXseesXx.seesfreecam;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class MouseWheelHandler {

    private static int savedHotbarSlot = -1;
    private static int lastHotbarSlot = -1;

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (event.phase != Phase.END) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) {
            return;
        }

        if (!FreecamHandler.isFreecamEnabled()) {
            savedHotbarSlot = -1;
            lastHotbarSlot = -1;
            return;
        }

        // Save hotbar slot when entering freecam
        if (savedHotbarSlot == -1) {
            savedHotbarSlot = mc.thePlayer.inventory.currentItem;
            lastHotbarSlot = savedHotbarSlot;
        }

        FreecamEntity freecam = FreecamHandler.getFreecamEntity();
        if (freecam == null) {
            return;
        }

        // Check mouse wheel
        int wheel = Mouse.getDWheel();
        if (wheel != 0) {
            // Adjust speed based on wheel direction
            freecam.adjustSpeed(wheel > 0 ? 1 : -1);
        }

        // Silently keep hotbar at saved slot (only update if it changed)
        if (mc.thePlayer.inventory.currentItem != lastHotbarSlot) {
            mc.thePlayer.inventory.currentItem = savedHotbarSlot;
            lastHotbarSlot = savedHotbarSlot;
        }
    }
}
