package com.zeml.rotp_zgd.init;

import java.util.function.Supplier;

import com.zeml.rotp_zgd.RotpGreenDayAddon;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.util.mc.OstSoundList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RotpGreenDayAddon.MOD_ID);
    
    public static final RegistryObject<SoundEvent> USER_GRREN_DAY_STAND = SOUNDS.register("gd_user", 
            () -> new SoundEvent(new ResourceLocation(RotpGreenDayAddon.MOD_ID, "gd_user")));

    public static final RegistryObject<SoundEvent> USER_PUNCH = SOUNDS.register("gd_user_punch",
            ()->new SoundEvent(new ResourceLocation(RotpGreenDayAddon.MOD_ID,"gd_user_punch")));

    public static final Supplier<SoundEvent> GREEN_DAY_SUMMON = SOUNDS.register("gd_summon",
            ()->new SoundEvent(new ResourceLocation(RotpGreenDayAddon.MOD_ID,"gd_summon")));
    
    public static final Supplier<SoundEvent> GREEN_DAY_UNSUMMON = ModSounds.STAND_UNSUMMON_DEFAULT;
    
    public static final Supplier<SoundEvent> GREEN_DAY_PUNCH_LIGHT = ModSounds.STAND_PUNCH_LIGHT;
    
    public static final Supplier<SoundEvent> GREEN_DAY_PUNCH_HEAVY = SOUNDS.register("gd_kick",
            () -> new SoundEvent(new ResourceLocation(RotpGreenDayAddon.MOD_ID, "gd_kick")));
    
    public static final Supplier<SoundEvent> GREEN_DAY_BARRAGE = ModSounds.STAND_PUNCH_LIGHT;
    
    public static final RegistryObject<SoundEvent> GREEN_DAY_ORA = SOUNDS.register("gd_ora", 
            () -> new SoundEvent(new ResourceLocation(RotpGreenDayAddon.MOD_ID, "gd_ora")));
    
    public static final RegistryObject<SoundEvent> GREEN_DAY_ORA_LONG = SOUNDS.register("gd_ora_long",
            () -> new SoundEvent(new ResourceLocation(RotpGreenDayAddon.MOD_ID, "gd_ora_long")));
    
    public static final RegistryObject<SoundEvent> GREEN_DAY_ORA_ORA_ORA = SOUNDS.register("gd_ora_ora_ora",
            () -> new SoundEvent(new ResourceLocation(RotpGreenDayAddon.MOD_ID, "gd_ora_ora_ora")));

    public static final Supplier<SoundEvent> MOLD = SOUNDS.register("gd_mold",
            () -> new SoundEvent(new ResourceLocation(RotpGreenDayAddon.MOD_ID, "gd_mold")));

    static final OstSoundList GREEN_DAY_OST = new OstSoundList(new ResourceLocation(RotpGreenDayAddon.MOD_ID, "gd_ost"), SOUNDS);

}
