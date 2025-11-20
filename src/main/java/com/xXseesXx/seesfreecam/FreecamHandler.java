package com.xXseesXx.seesfreecam;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.MovementInput;

public class FreecamHandler {

    private static boolean freecamEnabled = false;
    private static FreecamEntity freecamEntity;
    private static float savedYaw, savedPitch;
    private static boolean savedSneaking;
    private static MovementInput savedMovementInput;
    private static PlayerControllerMP savedPlayerController;

    public static boolean isFreecamEnabled() {
        return freecamEnabled;
    }

    public static FreecamEntity getFreecamEntity() {
        return freecamEntity;
    }

    public static void toggleFreecam() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null || mc.theWorld == null) {
            return;
        }

        if (!freecamEnabled) {
            enableFreecam();
        } else {
            disableFreecam();
        }
    }

    private static void enableFreecam() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;

        // Save player state
        savedYaw = player.rotationYaw;
        savedPitch = player.rotationPitch;
        savedSneaking = player.isSneaking();
        savedMovementInput = player.movementInput;
        savedPlayerController = mc.playerController;

        // Replace movement input to freeze player
        player.movementInput = new FreecamMovementInput();

        // Replace player controller to block all interactions
        mc.playerController = new FreecamPlayerController(mc, mc.getNetHandler());

        // Create freecam entity
        freecamEntity = new FreecamEntity(mc.theWorld, player);

        // Set as render view entity
        mc.renderViewEntity = freecamEntity;

        freecamEnabled = true;
        MyMod.LOG.info("Freecam enabled");
    }

    private static void disableFreecam() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;

        // Restore render view to player
        mc.renderViewEntity = player;

        // Restore player state
        if (player != null) {
            player.rotationYaw = savedYaw;
            player.rotationPitch = savedPitch;
            player.prevRotationYaw = savedYaw;
            player.prevRotationPitch = savedPitch;
            player.setSneaking(savedSneaking);

            // Restore original movement input
            player.movementInput = savedMovementInput;
        }

        // Restore original player controller
        mc.playerController = savedPlayerController;

        // Clean up freecam entity
        if (freecamEntity != null) {
            freecamEntity.setDead();
            freecamEntity = null;
        }

        freecamEnabled = false;
        MyMod.LOG.info("Freecam disabled");
    }

    public static void updateFreecam() {
        if (!freecamEnabled || freecamEntity == null) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        freecamEntity.updateMovement(mc);
    }
}
