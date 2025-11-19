package com.xXseesXx.seesfreecam;

import net.minecraft.util.MovementInput;

public class FreecamMovementInput extends MovementInput {

    @Override
    public void updatePlayerMoveState() {
        // Completely disable all player movement input
        this.moveForward = 0.0F;
        this.moveStrafe = 0.0F;
        this.jump = false;
        this.sneak = false;
    }
}
