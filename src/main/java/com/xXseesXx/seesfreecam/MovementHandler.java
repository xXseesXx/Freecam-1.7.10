package com.xXseesXx.seesfreecam;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class MovementHandler {

    private static float frozenYaw, frozenPitch;
    private static boolean frozenSneak;
    private static boolean initialized = false;

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (event.phase != Phase.END) {
            return;
        }

        if (!FreecamHandler.isFreecamEnabled()) {
            initialized = false;
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) {
            return;
        }

        EntityClientPlayerMP player = mc.thePlayer;

        // Save initial state
        if (!initialized) {
            frozenYaw = player.rotationYaw;
            frozenPitch = player.rotationPitch;
            frozenSneak = player.isSneaking();
            initialized = true;
        }

        // Freeze rotation (movement is handled by FreecamMovementInput)
        player.rotationYaw = frozenYaw;
        player.rotationPitch = frozenPitch;
        player.prevRotationYaw = frozenYaw;
        player.prevRotationPitch = frozenPitch;
        player.rotationYawHead = frozenYaw;
        player.prevRotationYawHead = frozenYaw;

        // Freeze sneak state
        player.setSneaking(frozenSneak);
    }
}
