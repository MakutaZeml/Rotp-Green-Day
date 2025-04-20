package com.zeml.rotp_zgd.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.non_stand.HamonHealing;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.MCUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class HealMold extends StandEntityAction {
    public HealMold(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        if(user.hasEffect(ModStatusEffects.BLEEDING.get()) || user.getMaxHealth() != user.getHealth()){
            return super.checkSpecificConditions(user, power, target);
        }
        return ActionConditionResult.NEGATIVE;
    }

    @Override
    public void standTickPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if(!world.isClientSide){
            LivingEntity user = userPower.getUser();
            if(task.getTick()%60==59){
                if(user.hasEffect(ModStatusEffects.BLEEDING.get())){
                    Effect bleed = user.getEffect(ModStatusEffects.BLEEDING.get()).getEffect();
                    int duration = user.getEffect(ModStatusEffects.BLEEDING.get()).getDuration()/2;
                    MCUtil.reduceEffect(user,bleed,duration,1);
                    MCUtil.runCommand(user,"playsound rotp_zgd:gd_mold player @a ~ ~ ~");
                }
                user.heal(user.getMaxHealth()*.25F);
            }
        }
    }



}



