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
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = RotpGreenDayAddon.MOD_ID)
public class GameplayHandler {


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

    @SubscribeEvent
    public static void onRemovePotionEffect(PotionEvent.PotionExpiryEvent event){
        if(!event.getEntity().level.isClientSide){
            if(event.getPotionEffect().getEffect() == InitStatusEffect.MOLD_UTIL_EFFECT.get()){
                LivingEntity living = event.getEntityLiving();
                living.getCapability(LivingDataProvider.CAPABILITY).ifPresent(livingData -> {
                    livingData.setMaxBlock(0);
                });
            }
        }
    }


    //Green Day Armless stuff

    @SubscribeEvent
    public static void onBlockBreak(PlayerEvent.BreakSpeed event){
        LivingEntity entity = event.getEntityLiving();
        if((entity.getMainArm() == HandSide.RIGHT && entity.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get()))
                ||(entity.getMainArm() == HandSide.LEFT && entity.hasEffect(InitStatusEffect.LEFT_ARMLESS.get()))){
            if(!entity.level.isClientSide){
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get()) && entity.hasEffect(InitStatusEffect.LEFT_ARMLESS.get())){
            if(!entity.level.isClientSide) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event){
        if(event.getEntity() instanceof LivingEntity){
            LivingEntity entity = (LivingEntity) event.getEntity();
            if((entity.getMainArm() == HandSide.RIGHT && entity.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get()) && entity.getUsedItemHand() == Hand.MAIN_HAND)
                    || (entity.getMainArm() == HandSide.LEFT && entity.hasEffect(InitStatusEffect.LEFT_ARMLESS.get()) && entity.getUsedItemHand() == Hand.OFF_HAND)
                    ||(entity.getMainArm() == HandSide.LEFT && entity.hasEffect(InitStatusEffect.LEFT_ARMLESS.get()) && entity.getUsedItemHand() == Hand.MAIN_HAND)
                    ||(entity.getMainArm() == HandSide.LEFT && entity.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get()) && entity.getUsedItemHand() == Hand.OFF_HAND)
            ){
                if(!entity.level.isClientSide){
                    ItemEntity item = new ItemEntity(entity.level,entity.getX(),entity.getY(),entity.getZ(),new ItemStack(event.getPlacedBlock().getBlock().asItem()));
                    entity.level.addFreshEntity(item);
                    event.setCanceled(true);
                }

            }
        }
    }

    @SubscribeEvent
    public static void onEntityAttack(LivingAttackEvent event){
        if(event.getSource().getEntity() instanceof  LivingEntity){
            LivingEntity entity = (LivingEntity) event.getSource().getEntity();
            if((entity.getMainArm() == HandSide.RIGHT && entity.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get()))
                    ||(entity.getMainArm() == HandSide.LEFT && entity.hasEffect(InitStatusEffect.LEFT_ARMLESS.get()))){
                if(!entity.level.isClientSide) event.setCanceled(true);
            }
        }

    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        LivingEntity entity = event.getEntityLiving();
        Hand hand = event.getHand();
        if((hand == Hand.MAIN_HAND && entity.getMainArm() == HandSide.RIGHT &&entity.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get()))||
                (hand == Hand.MAIN_HAND && entity.getMainArm() == HandSide.LEFT &&entity.hasEffect(InitStatusEffect.LEFT_ARMLESS.get()))){
            if(!entity.level.isClientSide) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        LivingEntity entity = event.getEntityLiving();
        if(entity.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get()) && entity.hasEffect(InitStatusEffect.LEFT_ARMLESS.get())){
            event.setCanceled(true);
        }
    }
}
