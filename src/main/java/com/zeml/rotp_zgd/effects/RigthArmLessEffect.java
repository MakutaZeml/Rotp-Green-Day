package com.zeml.rotp_zgd.effects;

import com.github.standobyte.jojo.potion.UncurableEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;

public class RigthArmLessEffect extends Effect {
    public RigthArmLessEffect(int color) {
        super(EffectType.HARMFUL, color);
    }


    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if(!entity.level.isClientSide){
            ItemStack itemStack = entity.getMainArm() == HandSide.RIGHT ? entity.getMainHandItem():entity.getOffhandItem();
            if(!itemStack.isEmpty()){
                ItemEntity itemEntity = new ItemEntity(entity.level,entity.getX(),entity.getY(),entity.getZ(),itemStack);
                itemEntity.setPickUpDelay(60);
                entity.setItemInHand(entity.getMainArm() == HandSide.RIGHT? Hand.MAIN_HAND:Hand.OFF_HAND,ItemStack.EMPTY);
                entity.level.addFreshEntity(itemEntity);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
        return true;
    }

}
