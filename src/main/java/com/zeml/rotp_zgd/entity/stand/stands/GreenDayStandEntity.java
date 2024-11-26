package com.zeml.rotp_zgd.entity.stand.stands;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import com.github.standobyte.jojo.entity.stand.StandRelativeOffset;
import net.minecraft.world.World;

public class GreenDayStandEntity extends StandEntity {
    
    public GreenDayStandEntity(StandEntityType<GreenDayStandEntity> type, World world) {
        super(type, world);
    }



    private final StandRelativeOffset offsetDefault = StandRelativeOffset.withYOffset(0, .5, -.75);

    public StandRelativeOffset getDefaultOffsetFromUser() {return offsetDefault;}



    @Override
    public double getMaxRange() {
        return 5.3D;
    }
}
