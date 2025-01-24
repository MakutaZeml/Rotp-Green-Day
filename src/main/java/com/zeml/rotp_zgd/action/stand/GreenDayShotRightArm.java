package com.zeml.rotp_zgd.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.zeml.rotp_zgd.capability.entity.LivingData;
import com.zeml.rotp_zgd.capability.entity.LivingDataProvider;
import com.zeml.rotp_zgd.entity.projectile.ShootingArmEntity;
import com.zeml.rotp_zgd.init.InitStatusEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class GreenDayShotRightArm extends StandAction {
    public GreenDayShotRightArm(StandAction.Builder builder){
        super(builder);
    }


    @Override
    public ActionConditionResult checkConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        boolean hasRight = user.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get());
        return !hasRight? ActionConditionResult.POSITIVE:ActionConditionResult.NEGATIVE;
    }

    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if(!world.isClientSide){
            LazyOptional<LivingData> dataLazyOptional = user.getCapability(LivingDataProvider.CAPABILITY);
            dataLazyOptional.ifPresent(livingData -> {
                if(livingData.isHasRightArm()){
                    ShootingArmEntity arm = new ShootingArmEntity(user,world);
                    arm.setRight(true);
                    arm.shootFromRotation(user,1.5F,.3F);
                    arm.setNoGravity(false);
                    world.addFreshEntity(arm);
                    arm.setNoGravity(false);
                }
            });
        }
    }
}
