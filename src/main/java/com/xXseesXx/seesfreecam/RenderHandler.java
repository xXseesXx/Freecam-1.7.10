package com.xXseesXx.seesfreecam;

import net.minecraft.client.Minecraft;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class RenderHandler {

    @SubscribeEvent
    public void onRenderTick(RenderTickEvent event) {
        if (!FreecamHandler.isFreecamEnabled()) {
            return;
        }

        FreecamEntity freecam = FreecamHandler.getFreecamEntity();
        if (freecam == null) {
            return;
        }

        // Update rotation during render for smooth camera movement
        Minecraft mc = Minecraft.getMinecraft();
        freecam.updateRotation(mc);
    }
}
