package com.xXseesXx.seesfreecam;

import net.minecraft.client.Minecraft;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class TickHandler {

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (event.phase == Phase.END) {
            FreecamHandler.updateFreecam();
        }

        if (event.phase == Phase.START) {
            Minecraft mc = Minecraft.getMinecraft();

            if (FreecamHandler.isFreecamEnabled()) {
                // Prevent F5 perspective change while in freecam
                if (mc.gameSettings.thirdPersonView != 0) {
                    mc.gameSettings.thirdPersonView = 0;
                }
            }
        }
    }
}
