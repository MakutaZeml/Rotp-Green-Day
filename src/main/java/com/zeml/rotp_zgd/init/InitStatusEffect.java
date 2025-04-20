package com.zeml.rotp_zgd.init;

import com.github.standobyte.jojo.init.ModStatusEffects;
import com.zeml.rotp_zgd.RotpGreenDayAddon;
import com.zeml.rotp_zgd.effects.LeftArmLessEffect;
import com.zeml.rotp_zgd.effects.MoldEffect;
import com.zeml.rotp_zgd.effects.MoldUtilEffect;
import com.zeml.rotp_zgd.effects.RigthArmLessEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = RotpGreenDayAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InitStatusEffect {

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, RotpGreenDayAddon.MOD_ID);

    public static final RegistryObject<Effect> MOLD_EFFECT = EFFECTS.register("mold",
            ()-> new MoldEffect(0x465D58));

    public static final RegistryObject<Effect> MOLD_UTIL_EFFECT = EFFECTS.register("mold_util",
            ()-> new MoldUtilEffect(0x465D58));


        public static final RegistryObject<Effect> RIGHT_ARMLESS = EFFECTS.register("right_armless",
            ()->new RigthArmLessEffect(0xFF0000));


    public static final RegistryObject<Effect> LEFT_ARMLESS = EFFECTS.register("left_armless",
            ()->new LeftArmLessEffect(0xFF0000));
    
    
    public static void afterEffectsRegister() {
        ModStatusEffects.setEffectAsTracked(
                RIGHT_ARMLESS.get(), 
                LEFT_ARMLESS.get());
    }

}
