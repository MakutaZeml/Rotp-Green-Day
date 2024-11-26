package com.zeml.rotp_zgd.power.impl.type;

import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.Nullable;

public class GreenDayStandType  <T extends StandStats> extends EntityStandType<T> {

    @Deprecated
    public GreenDayStandType(int color, ITextComponent partName, StandAction[] attacks, StandAction[] abilities, Class<T> statsClass, T defaultStats, @Nullable StandType.StandTypeOptionals additions) {
        super(color, partName, attacks, abilities, statsClass, defaultStats, additions);
    }

    protected GreenDayStandType(EntityStandType.AbstractBuilder<?, T> builder) {
        super(builder);
    }

    @Override
    public void tickUser(LivingEntity user, IStandPower power) {
        super.tickUser(user, power);
    }


    @Override
    public void unsummon(LivingEntity user, IStandPower standPower) {
        super.unsummon(user, standPower);
    }

    public static class Builder<T extends StandStats> extends EntityStandType.AbstractBuilder<Builder<T>, T> {

        @Override
        protected Builder<T> getThis() {
            return this;
        }

        @Override
        public GreenDayStandType<T> build() {
            return new GreenDayStandType<>(this);
        }

    }


}
