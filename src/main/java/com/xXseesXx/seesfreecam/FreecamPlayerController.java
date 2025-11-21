package com.xXseesXx.seesfreecam;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class FreecamPlayerController extends PlayerControllerMP {

    public FreecamPlayerController(Minecraft mc, NetHandlerPlayClient netHandler) {
        super(mc, netHandler);
    }

    @Override
    public void onPlayerDamageBlock(int x, int y, int z, int side) {
        // Block all interactions in both modes
    }

    @Override
    public boolean onPlayerDestroyBlock(int x, int y, int z, int side) {
        // Block all interactions in both modes
        return false;
    }

    @Override
    public boolean onPlayerRightClick(EntityPlayer player, World world, ItemStack stack, int x, int y, int z, int side,
        Vec3 hitVec) {
        // Block all interactions in both modes
        return false;
    }

    @Override
    public void attackEntity(EntityPlayer player, Entity target) {
        // Block all attacks in both modes - prevents self-attack crash
        // Do nothing - don't call super
    }

    @Override
    public void clickBlock(int x, int y, int z, int side) {
        // Block all interactions in both modes
    }

    @Override
    public void resetBlockRemoving() {
        // Always allow resetting (used by InteractionCanceller)
        super.resetBlockRemoving();
    }
}
