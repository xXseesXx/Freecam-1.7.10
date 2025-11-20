package com.xXseesXx.seesfreecam;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        KeyHandler.registerKeys();
        FMLCommonHandler.instance()
            .bus()
            .register(new KeyHandler());
        FMLCommonHandler.instance()
            .bus()
            .register(new TickHandler());
        FMLCommonHandler.instance()
            .bus()
            .register(new RenderHandler());
        FMLCommonHandler.instance()
            .bus()
            .register(new MovementHandler());
        FMLCommonHandler.instance()
            .bus()
            .register(new MouseWheelHandler());
        FMLCommonHandler.instance()
            .bus()
            .register(new SpeedDisplay());

        // Register interaction canceller on both event buses
        InteractionCanceller interactionCanceller = new InteractionCanceller();
        MinecraftForge.EVENT_BUS.register(interactionCanceller);
        FMLCommonHandler.instance()
            .bus()
            .register(interactionCanceller);

        MyMod.LOG.info("Freecam client-side handlers registered");
    }
}
