package com.zeml.rotp_zgd.entity.stand.stands;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import com.github.standobyte.jojo.entity.stand.StandRelativeOffset;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.zeml.rotp_zgd.capability.entity.LivingData;
import com.zeml.rotp_zgd.capability.entity.LivingDataProvider;
import com.zeml.rotp_zgd.init.InitStands;
import com.zeml.rotp_zgd.init.InitStatusEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import java.util.List;

public class GreenDayStandEntity extends StandEntity {
    
    public GreenDayStandEntity(StandEntityType<GreenDayStandEntity> type, World world) {
        super(type, world);
    }


    @Override
    public void tick() {
        super.tick();
        if(!this.level.isClientSide){
            if(this.getUser() != null){
                LivingEntity user = this.getUser();
                IStandPower.getStandPowerOptional(user).ifPresent(power -> {
                    LazyOptional<LivingData> playerDataOptional = user.getCapability(LivingDataProvider.CAPABILITY);
                    playerDataOptional.ifPresent(playerData ->{
                        if(power.getType() != InitStands.GREEN_DAY_STAND.getStandType()){
                            if(!playerData.isMoldActivated()){
                                playerData.setMoldActivated(false);
                            }
                        }else {
                            if(playerData.isMoldActivated()){
                                List<LivingEntity> set = MCUtil.entitiesAround(LivingEntity.class,user,128,false,LivingEntity::isAlive);
                                if(!set.isEmpty()){
                                    set.forEach(entity -> {
                                        if(!(entity instanceof StandEntity)){
                                            entity.addEffect(new EffectInstance(InitStatusEffect.MOLD_UTIL_EFFECT.get(),40,0,false,false,false));
                                        }
                                    });
                                }
                                power.consumeStamina(1);
                                if(power.getStamina() == 0){
                                    playerData.setMoldActivated(false);
                                }
                            }
                        }
                    });
                });
            }

        }
    }

    private final StandRelativeOffset offsetDefault = StandRelativeOffset.withYOffset(0, .5, -.75);

    public StandRelativeOffset getDefaultOffsetFromUser() {return offsetDefault;}


    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        if(this.getUser() != null){
            this.getUser().getCapability(LivingDataProvider.CAPABILITY).ifPresent(livingData -> livingData.setMoldActivated(false));
        }
    }

    @Override
    public double getMaxRange() {
        return 5.3D;
    }
}
