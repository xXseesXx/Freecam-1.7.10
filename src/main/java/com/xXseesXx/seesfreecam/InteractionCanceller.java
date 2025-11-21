package com.xXseesXx.seesfreecam;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class InteractionCanceller {

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Cancel all interactions while freecam is enabled (both modes)
        if (FreecamHandler.isFreecamEnabled()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onEntityInteract(EntityInteractEvent event) {
        // Cancel all interactions while freecam is enabled (both modes)
        if (FreecamHandler.isFreecamEnabled()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {
        // Cancel all attacks while freecam is enabled (both modes)
        if (FreecamHandler.isFreecamEnabled()) {
            event.setCanceled(true);

            // Extra protection: prevent self-attack
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.thePlayer != null && event.target == mc.thePlayer) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (event.phase != Phase.START) {
            return;
        }

        if (!FreecamHandler.isFreecamEnabled()) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();

        // Prevent targeting the player entity
        if (mc.objectMouseOver != null && mc.objectMouseOver.entityHit == mc.thePlayer) {
            mc.objectMouseOver = null;
        }

        // Block all interactions in both modes
        // Cancel any ongoing block breaking
        if (mc.playerController != null) {
            mc.playerController.resetBlockRemoving();
        }

        // Consume mouse clicks
        while (mc.gameSettings.keyBindAttack.isPressed()) {
            // Consume left click
        }
        while (mc.gameSettings.keyBindUseItem.isPressed()) {
            // Consume right click
        }
        while (mc.gameSettings.keyBindPickBlock.isPressed()) {
            // Consume middle click
        }
    }
}
