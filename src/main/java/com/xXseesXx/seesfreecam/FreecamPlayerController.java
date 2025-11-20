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
        // Do nothing - block all block breaking
    }

    @Override
    public boolean onPlayerDestroyBlock(int x, int y, int z, int side) {
        return false; // Block all block destroying
    }

    @Override
    public boolean onPlayerRightClick(EntityPlayer player, World world, ItemStack stack, int x, int y, int z, int side,
        Vec3 hitVec) {
        return false; // Block all right clicks
    }

    @Override
    public void attackEntity(EntityPlayer player, Entity target) {
        // Do nothing - no entity attacks
    }

    @Override
    public void clickBlock(int x, int y, int z, int side) {
        // Do nothing - block all block clicks
    }

    @Override
    public void resetBlockRemoving() {
        // Do nothing - ignore break progress reset
    }
}
