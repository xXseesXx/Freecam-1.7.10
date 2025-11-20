package com.xXseesXx.seesfreecam;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class FreecamEntity extends EntityLivingBase {

    private float speed = 1.0f;
    private static final float MIN_SPEED = 0.2f;
    private static final float MAX_SPEED = 10.0f;
    private static final float SPEED_STEP = 0.2f;

    public FreecamEntity(World world, EntityPlayerSP player) {
        super(world);

        // Set initial speed from config
        this.speed = Config.freecamSpeed;

        // Set position at player's eye level
        double x = player.posX;
        double y = player.posY + player.getEyeHeight();
        double z = player.posZ;

        this.setPosition(x, y, z);

        // CRITICAL: Set all position fields to avoid interpolation flicker
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.lastTickPosX = x;
        this.lastTickPosY = y;
        this.lastTickPosZ = z;

        // Set rotation
        this.rotationYaw = player.rotationYaw;
        this.rotationPitch = player.rotationPitch;
        this.prevRotationYaw = player.rotationYaw;
        this.prevRotationPitch = player.rotationPitch;

        this.noClip = true;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {}

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {}

    @Override
    public ItemStack getHeldItem() {
        return null;
    }

    @Override
    public ItemStack getEquipmentInSlot(int slot) {
        return null;
    }

    @Override
    public void setCurrentItemOrArmor(int slot, ItemStack stack) {}

    @Override
    public ItemStack[] getLastActiveItems() {
        return new ItemStack[0];
    }

    @Override
    public void onUpdate() {
        // Do nothing - we handle updates manually
    }

    @Override
    public void onLivingUpdate() {
        // Do nothing - we handle updates manually
    }

    public void adjustSpeed(int direction) {
        if (direction > 0) {
            speed = Math.min(speed + SPEED_STEP, MAX_SPEED);
        } else if (direction < 0) {
            speed = Math.max(speed - SPEED_STEP, MIN_SPEED);
        }
        SpeedDisplay.setDisplaySpeed(speed);
    }

    public void updateRotation(Minecraft mc) {
        // Store previous rotation
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;

        // Don't update rotation if a GUI is open
        if (mc.currentScreen != null) {
            return;
        }

        // Handle mouse input
        float sensitivity = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
        float mouseSensitivity = sensitivity * sensitivity * sensitivity * 8.0F;

        float yawChange = mc.mouseHelper.deltaX * mouseSensitivity * 0.15F;
        float pitchChange = mc.mouseHelper.deltaY * mouseSensitivity * 0.15F;

        this.rotationYaw += yawChange;
        this.rotationPitch -= pitchChange;

        // Clamp pitch
        if (this.rotationPitch > 90.0F) {
            this.rotationPitch = 90.0F;
        }
        if (this.rotationPitch < -90.0F) {
            this.rotationPitch = -90.0F;
        }
    }

    public void updateMovement(Minecraft mc) {
        // Store previous values for smooth interpolation
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;

        // Handle movement
        float forward = 0;
        float strafe = 0;
        float vertical = 0;

        if (mc.gameSettings.keyBindForward.getIsKeyPressed()) {
            forward -= 1;
        }
        if (mc.gameSettings.keyBindBack.getIsKeyPressed()) {
            forward += 1;
        }
        if (mc.gameSettings.keyBindLeft.getIsKeyPressed()) {
            strafe -= 1;
        }
        if (mc.gameSettings.keyBindRight.getIsKeyPressed()) {
            strafe += 1;
        }
        if (mc.gameSettings.keyBindJump.getIsKeyPressed()) {
            vertical += 1;
        }
        if (mc.gameSettings.keyBindSneak.getIsKeyPressed()) {
            vertical -= 1;
        }

        // Apply sprint multiplier
        float effectiveSpeed = speed;
        if (mc.gameSettings.keyBindSprint.getIsKeyPressed()) {
            effectiveSpeed *= 2.0f;
        }

        double motionX = 0;
        double motionY = vertical * effectiveSpeed;
        double motionZ = 0;

        if (forward != 0 || strafe != 0) {
            double angle = Math.atan2(-strafe, forward);
            double yawRad = Math.toRadians(this.rotationYaw);
            motionX = Math.sin(yawRad + angle) * effectiveSpeed;
            motionZ = -Math.cos(yawRad + angle) * effectiveSpeed;
        }

        this.posX += motionX;
        this.posY += motionY;
        this.posZ += motionZ;

        // Update bounding box
        this.setPosition(this.posX, this.posY, this.posZ);
    }
}
