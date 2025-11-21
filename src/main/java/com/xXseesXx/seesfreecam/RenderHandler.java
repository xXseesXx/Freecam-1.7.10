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

        Minecraft mc = Minecraft.getMinecraft();
        FreecamEntity freecam = FreecamHandler.getFreecamEntity();

        if (freecam == null) {
            return;
        }

        // Always use freecam as render view
        // Camera stays at freecam position in both modes
        if (mc.renderViewEntity != freecam) {
            mc.renderViewEntity = freecam;
        }

        // Update rotation during render for smooth camera movement
        // Only in FREECAM mode - in PLAYER mode rotation is static
        if (FreecamHandler.getMovementMode() == FreecamHandler.MovementMode.FREECAM) {
            freecam.updateRotation(mc);
        }
    }
}
