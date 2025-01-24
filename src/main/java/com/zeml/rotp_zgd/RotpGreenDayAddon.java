package com.zeml.rotp_zgd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zeml.rotp_zgd.capability.entity.CapabilityHandler;
import com.zeml.rotp_zgd.init.*;
import com.zeml.rotp_zgd.network.AddonPackets;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RotpGreenDayAddon.MOD_ID)
public class RotpGreenDayAddon {
    // The value here should match an entry in the META-INF/mods.toml file
    public static final String MOD_ID = "rotp_zgd";
    private static final Logger LOGGER = LogManager.getLogger();

    public RotpGreenDayAddon() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InitEntities.ENTITIES.register(modEventBus);
        InitSounds.SOUNDS.register(modEventBus);
        InitStands.ACTIONS.register(modEventBus);
        InitStands.STANDS.register(modEventBus);
        InitStatusEffect.EFFECTS.register(modEventBus);

        modEventBus.addListener(this::preInit);
    }

    private void preInit(FMLCommonSetupEvent event){
        AddonPackets.init();
        CapabilityHandler.commonSetupRegister();
        InitTags.iniTags();
        InitStatusEffect.afterEffectsRegister();
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
