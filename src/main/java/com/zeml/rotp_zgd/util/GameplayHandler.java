package com.zeml.rotp_zgd.util;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.init.power.non_stand.ModPowers;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.zeml.rotp_zgd.RotpGreenDayAddon;
import com.zeml.rotp_zgd.capability.entity.LivingData;
import com.zeml.rotp_zgd.capability.entity.LivingDataProvider;
import com.zeml.rotp_zgd.entity.projectile.ShootingArmEntity;
import com.zeml.rotp_zgd.init.InitSounds;
import com.zeml.rotp_zgd.init.InitStands;
import com.zeml.rotp_zgd.init.InitStatusEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = RotpGreenDayAddon.MOD_ID)
public class GameplayHandler {


    @SubscribeEvent(priority =  EventPriority.LOW)
    public static void ArmlessRender(LivingEvent.LivingUpdateEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(!entity.level.isClientSide){
            LazyOptional<LivingData> livingData = entity.getCapability(LivingDataProvider.CAPABILITY);
            livingData.ifPresent(data ->{
                if(!data.isHasLeftArm()){
                    if(!entity.hasEffect(InitStatusEffect.LEFT_ARMLESS.get())){
                        entity.addEffect(new EffectInstance(InitStatusEffect.LEFT_ARMLESS.get(),Integer.MAX_VALUE,0,false,false,false));
                    }
                }else {
                    entity.removeEffect(InitStatusEffect.LEFT_ARMLESS.get());
                }
                if(!data.isHasRightArm()){
                    if(!entity.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get())){
                        entity.addEffect(new EffectInstance(InitStatusEffect.RIGHT_ARMLESS.get(),Integer.MAX_VALUE,0,false,false,false));
                    }
                }else {
                    entity.removeEffect(InitStatusEffect.RIGHT_ARMLESS.get());
                }


            });
        }
    }


    /*
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onDeleteEvent(LivingEvent.LivingUpdateEvent event){
        LivingEntity user = event.getEntityLiving();
        if(!user.level.isClientSide){
            IStandPower.getStandPowerOptional(user).ifPresent(power -> {
                LazyOptional<LivingData> playerDataOptional = user.getCapability(LivingDataProvider.CAPABILITY);
                playerDataOptional.ifPresent(playerData ->{
                    StandType<?> GD = InitStands.GREEN_DAY_STAND.getStandType();
                    if(power.getType() != GD){
                        if(!playerData.isMoldActivated()){
                            playerData.setMoldActivated(false);
                        }
                    }else {
                        if(!(power.getStandManifestation() instanceof StandEntity)){
                            playerData.setMoldActivated(false);
                        }else {
                            if(playerData.isMoldActivated()){
                                List<LivingEntity> set = MCUtil.entitiesAround(LivingEntity.class,user,256,false,LivingEntity::isAlive);
                                if(!set.isEmpty()){
                                    set.forEach(entity -> {
                                        if(!entity.hasEffect(InitStatusEffect.MOLD_UTIL_EFFECT.get())){
                                            if(!(entity instanceof StandEntity)){
                                                entity.addEffect(new EffectInstance(InitStatusEffect.MOLD_UTIL_EFFECT.get(),Integer.MAX_VALUE,0,false,false,false));
                                            }
                                        }
                                    });
                                }
                                power.consumeStamina(1);
                                if(power.getStamina() == 0){
                                    playerData.setMoldActivated(false);
                                }
                            }
                        }
                    }
                });
            });
        }
    }
     */


    @SubscribeEvent
    public static void onProjectileHit(ProjectileImpactEvent event){
        Entity entity = event.getEntity();
        if(!entity.level.isClientSide){
            if(entity instanceof ShootingArmEntity){
                if(event.getRayTraceResult() instanceof EntityRayTraceResult){
                    EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult) event.getRayTraceResult();
                    Entity target = entityRayTraceResult.getEntity();
                    if(target instanceof LivingEntity){
                        MCUtil.runCommand( (LivingEntity) target,"playsound rotp_zgd:gd_kick neutral @a ~ ~ ~ .5 1");
                    }
                }

            }
        }
    }

    @SubscribeEvent
    public static void onGreenDayDeath(LivingDeathEvent event){
        LivingEntity living = event.getEntityLiving();
        if(!living.level.isClientSide){
            IStandPower.getStandPowerOptional(living).ifPresent(power -> {
                StandType<?> GREEN_DAY = InitStands.GREEN_DAY_STAND.getStandType();
                if(power.getType() == GREEN_DAY){
                    Iterable<Entity> list =  MCUtil.getAllEntities(living.level);
                    list.forEach(entity -> {
                        if(entity instanceof LivingEntity){
                            LivingEntity livingEntity = (LivingEntity) entity;
                            if(livingEntity.hasEffect(InitStatusEffect.MOLD_UTIL_EFFECT.get())){
                                livingEntity.removeEffect(InitStatusEffect.MOLD_UTIL_EFFECT.get());
                                LazyOptional<LivingData> playerDataOptional = living.getCapability(LivingDataProvider.CAPABILITY);
                                playerDataOptional.ifPresent(playerData ->{
                                    playerData.setTicksInMold(0);
                                });
                            }
                        }
                    });
                }

            });
        }
    }

}
