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

public class MoldUtilEffect extends MoldEffect implements IApplicableEffect {


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
            if(amplifier > 1){
                living.addEffect(new EffectInstance(Effects.WEAKNESS,20,amplifier-1,false,false,false));
            }
            if(amplifier > 2){
                living.addEffect(new EffectInstance(Effects.WEAKNESS,20,amplifier-2,false,false,false));
            }
            if(amplifier>4){
                living.addEffect(new EffectInstance(Effects.CONFUSION ,20,amplifier-4,false,false,false));

            }
            List<GreenDayStandEntity> list = MCUtil.entitiesAround(GreenDayStandEntity.class,living,256,false, greenDayStandEntity -> greenDayStandEntity.isAlive() && greenDayStandEntity.getUser() != living);
            if(!list.isEmpty()){
                list.forEach(greenDayStandEntity -> {
                    LivingEntity owner = greenDayStandEntity.getUser();
                    if(owner != null && owner.isAlive()){
                        LazyOptional<LivingData> livingDataLazyOptional = owner.getCapability(LivingDataProvider.CAPABILITY);
                        if(!livingDataLazyOptional.map(LivingData::isMoldActivated).orElse(false)){
                            living.removeEffect(this);
                            living.getCapability(LivingDataProvider.CAPABILITY).ifPresent(livingData -> livingData.setTicksInMold(0));
                        }
                    }else {
                        living.removeEffect(this);
                        living.getCapability(LivingDataProvider.CAPABILITY).ifPresent(livingData -> livingData.setTicksInMold(0));
                    }
                });
            }else {
                living.removeEffect(this);
                living.getCapability(LivingDataProvider.CAPABILITY).ifPresent(livingData -> livingData.setTicksInMold(0));
            }
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


    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean isApplicable(LivingEntity entity) {
        return !isUndead(entity);
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
