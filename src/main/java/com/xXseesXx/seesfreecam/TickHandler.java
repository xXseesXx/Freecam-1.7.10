package com.xXseesXx.seesfreecam;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class TickHandler {

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (event.phase == Phase.END) {
            FreecamHandler.updateFreecam();
        }
    }
}
