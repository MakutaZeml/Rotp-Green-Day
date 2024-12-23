package com.zeml.rotp_zgd.init;

import com.zeml.rotp_zgd.entity.stand.stands.GreenDayStandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject.EntityStandSupplier;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;

public class AddonStands {

    public static final EntityStandSupplier<EntityStandType<StandStats>, StandEntityType<GreenDayStandEntity>>
            GREEN_DAY_STANDO = new EntityStandSupplier<>(InitStands.GREEN_DAY_STAND);
}
