package com.xXseesXx.seesfreecam;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInput;

public class FreecamMovementInput extends MovementInput {

    @Override
    public void updatePlayerMoveState() {
        // Only freeze player movement in FREECAM mode
        if (FreecamHandler.getMovementMode() == FreecamHandler.MovementMode.FREECAM) {
            // Completely disable all player movement input
            this.moveForward = 0.0F;
            this.moveStrafe = 0.0F;
            this.jump = false;
            this.sneak = false;
        } else {
            // In PLAYER mode, allow normal movement
            GameSettings settings = Minecraft.getMinecraft().gameSettings;
            this.moveForward = 0.0F;
            this.moveStrafe = 0.0F;

            if (settings.keyBindForward.getIsKeyPressed()) {
                this.moveForward += 1.0F;
            }
            if (settings.keyBindBack.getIsKeyPressed()) {
                this.moveForward -= 1.0F;
            }
            if (settings.keyBindLeft.getIsKeyPressed()) {
                this.moveStrafe += 1.0F;
            }
            if (settings.keyBindRight.getIsKeyPressed()) {
                this.moveStrafe -= 1.0F;
            }

            this.jump = settings.keyBindJump.getIsKeyPressed();
            this.sneak = settings.keyBindSneak.getIsKeyPressed();

            if (this.sneak) {
                this.moveStrafe = (float) ((double) this.moveStrafe * 0.3D);
                this.moveForward = (float) ((double) this.moveForward * 0.3D);
            }
        }
    }
}
