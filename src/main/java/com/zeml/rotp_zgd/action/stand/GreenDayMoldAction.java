package com.zeml.rotp_zgd.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.zeml.rotp_zgd.capability.entity.LivingData;
import com.zeml.rotp_zgd.capability.entity.LivingDataProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class GreenDayMoldAction extends StandEntityAction {

    public GreenDayMoldAction(StandEntityAction.Builder builder){
        super(builder);
    }


    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        LivingEntity user = userPower.getUser();
        if(!world.isClientSide){
            LazyOptional<LivingData> playerDataOptional = user.getCapability(LivingDataProvider.CAPABILITY);
            playerDataOptional.ifPresent(playerData ->{
                playerData.setMoldActivated(!playerData.isMoldActivated());
            });
        }
    }

    @Override
    public boolean greenSelection(IStandPower power, ActionConditionResult conditionCheck) {
        LazyOptional<LivingData> playerDataOptional = power.getUser().getCapability(LivingDataProvider.CAPABILITY);
        return playerDataOptional.map(LivingData::isMoldActivated).isPresent()?playerDataOptional.map(LivingData::isMoldActivated).get():false;

    }


    @Override
    public void standTickRecovery(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        super.standTickRecovery(world, standEntity, userPower, task);
    }

    @Override
    public boolean stopOnHeavyAttack(StandEntityHeavyAttack.HeavyPunchInstance punch) {
        return super.stopOnHeavyAttack(punch);
    }
}
