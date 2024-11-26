package com.zeml.rotp_zgd.effects;

import com.github.standobyte.jojo.capability.world.TimeStopHandler;
import com.github.standobyte.jojo.potion.IApplicableEffect;
import com.github.standobyte.jojo.potion.StatusEffect;
import com.github.standobyte.jojo.potion.UncurableEffect;
import com.zeml.rotp_zgd.capability.entity.LivingData;
import com.zeml.rotp_zgd.capability.entity.LivingDataProvider;
import com.zeml.rotp_zgd.init.InitStatusEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public class MoldEffect extends Effect {
    public MoldEffect( int liquidColor) {
        super(EffectType.HARMFUL, liquidColor);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if(!living.level.isClientSide){
            LazyOptional<LivingData> playerDataOptional = living.getCapability(LivingDataProvider.CAPABILITY);
            playerDataOptional.ifPresent(playerData ->{

                if(!TimeStopHandler.isTimeStopped(living.level,living.blockPosition())){
                    playerData.setTicksInMold(playerData.getTicksInMold()+1);
                    if(amplifier != 0){
                        int tickToDie = 200*(10-amplifier)/3;
                        if(tickToDie <= playerData.getTicksInMold()){
                            living.kill();
                        }

                    }
                }
            });
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }


    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }


}
