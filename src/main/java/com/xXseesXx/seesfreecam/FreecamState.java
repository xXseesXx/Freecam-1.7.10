package com.xXseesXx.seesfreecam;

public class FreecamState {

    public double posX, posY, posZ;
    public double prevPosX, prevPosY, prevPosZ;
    public float rotationYaw, rotationPitch;
    public float prevRotationYaw, prevRotationPitch;

    public FreecamState(double x, double y, double z, float yaw, float pitch) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.rotationYaw = yaw;
        this.rotationPitch = pitch;
        this.prevRotationYaw = yaw;
        this.prevRotationPitch = pitch;
    }

    public void updatePrevious() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }
}
