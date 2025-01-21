package com.zeml.rotp_zgd.action.stand;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.zeml.rotp_zgd.capability.entity.LivingData;
import com.zeml.rotp_zgd.capability.entity.LivingDataProvider;
import com.zeml.rotp_zgd.entity.projectile.ShootingArmEntity;
import com.zeml.rotp_zgd.init.InitStands;
import com.zeml.rotp_zgd.init.InitStatusEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

public class GreenDayShotLeftArm extends StandAction {

    public GreenDayShotLeftArm(StandAction.Builder builder){
        super(builder);
    }


    @Override
    protected Action<IStandPower> replaceAction(IStandPower power, ActionTarget target) {
        if(power.getUser().isShiftKeyDown() || power.getUser().hasEffect(InitStatusEffect.LEFT_ARMLESS.get())){
            return InitStands.SHOT_RIGHT_ARM.get();
        }
        return super.replaceAction(power, target);
    }

    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if(!world.isClientSide){
            LazyOptional<LivingData> dataLazyOptional = user.getCapability(LivingDataProvider.CAPABILITY);
            dataLazyOptional.ifPresent(livingData -> {
                if(livingData.isHasLeftArm()){
                    ShootingArmEntity arm = new ShootingArmEntity(user,world);
                    arm.setRight(false);
                    arm.setNoGravity(false);
                    arm.shootFromRotation(user,1.5F,.3F);
                    world.addFreshEntity(arm);
                    arm.setNoGravity(false);
                }
            });
        }
    }


    @Override
    public StandAction[] getExtraUnlockable() {
        return new StandAction[] {InitStands.SHOT_RIGHT_ARM.get()};
    }
}
