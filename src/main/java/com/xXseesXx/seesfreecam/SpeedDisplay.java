package com.xXseesXx.seesfreecam;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class SpeedDisplay {

    private static float displaySpeed = 0.5f;
    private static int displayTicks = 0;
    private static final int DISPLAY_DURATION = 40; // 2 seconds at 20 tps

    public static void setDisplaySpeed(float speed) {
        displaySpeed = speed;
        displayTicks = DISPLAY_DURATION;
    }

    @SubscribeEvent
    public void onRenderTick(RenderTickEvent event) {
        if (!FreecamHandler.isFreecamEnabled() || displayTicks <= 0) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        FontRenderer fr = mc.fontRenderer;

        String speedText = String.format("Speed: %.1fx", displaySpeed);
        int width = fr.getStringWidth(speedText);
        int x = (sr.getScaledWidth() - width) / 2;
        int y = sr.getScaledHeight() - 60;

        // Draw with shadow for better visibility
        fr.drawStringWithShadow(speedText, x, y, 0xFFFFFF);

        displayTicks--;
    }
}
