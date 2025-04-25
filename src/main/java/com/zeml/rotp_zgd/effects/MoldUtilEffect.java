package com.zeml.rotp_zgd.effects;

import com.github.standobyte.jojo.init.power.non_stand.ModPowers;
import com.github.standobyte.jojo.potion.IApplicableEffect;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.impl.nonstand.type.NonStandPowerType;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.zeml.rotp_zgd.capability.entity.LivingData;
import com.zeml.rotp_zgd.capability.entity.LivingDataProvider;
import com.zeml.rotp_zgd.entity.stand.stands.GreenDayStandEntity;
import com.zeml.rotp_zgd.init.InitStatusEffect;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MoldUtilEffect extends MoldEffect {


    public MoldUtilEffect(int liquidColor) {
        super(liquidColor);
    }


    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if(!living.level.isClientSide){
            LazyOptional<LivingData> playerDataOptional = living.getCapability(LivingDataProvider.CAPABILITY);
            playerDataOptional.ifPresent(playerData ->{
                if(living.blockPosition().getY()>playerData.getMaxBlock()){
                    playerData.setMaxBlock(living.blockPosition().getY());
                }
                if(living.blockPosition().getY()<playerData.getMaxBlock()){
                    if(living.blockPosition().getY()<playerData.getMaxBlock()){
                        living.addEffect(new EffectInstance(InitStatusEffect.MOLD_EFFECT.get(),20,playerData.getMaxBlock()-living.blockPosition().getY(),false,false,false));
                    }
                }
                if(!living.hasEffect(InitStatusEffect.MOLD_EFFECT.get())){
                    playerData.setTicksWithoutMold(playerData.getTicksWithoutMold()+1);
                }
                if(playerData.getTicksWithoutMold() > 100){
                    playerData.setTicksInMold(0);
                }
            });
        }
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity p_180793_1_, @Nullable Entity p_180793_2_, LivingEntity living, int p_180793_4_, double p_180793_5_) {
        if(!living.level.isClientSide){
            LazyOptional<LivingData> playerDataOptional = living.getCapability(LivingDataProvider.CAPABILITY);
            playerDataOptional.ifPresent(playerData ->{
                playerData.setMaxBlock(living.blockPosition().getY());
            });
        }
    }


    public static boolean isUndead(LivingEntity entity) {
        if (entity.getMobType() == CreatureAttribute.UNDEAD) {
            return true;
        }else  {
            return isLivingJojoZombie((entity));
        }
    }

    public static boolean isLivingJojoZombie(LivingEntity player) {
        return INonStandPower.getNonStandPowerOptional(player).map(power -> {
            NonStandPowerType<?> powerType = power.getType();
            return powerType == ModPowers.ZOMBIE.get();
        }).orElse(false);
    }
}
