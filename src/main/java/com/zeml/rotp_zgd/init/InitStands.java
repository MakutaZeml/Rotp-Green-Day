package com.zeml.rotp_zgd.init;

import com.zeml.rotp_zgd.RotpGreenDayAddon;
import com.zeml.rotp_zgd.action.stand.*;
import com.zeml.rotp_zgd.entity.stand.stands.GreenDayStandEntity;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction.Phase;
import com.github.standobyte.jojo.action.stand.StandEntityBlock;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.action.stand.StandEntityMeleeBarrage;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.StandInstance.StandPart;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class InitStands {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), RotpGreenDayAddon.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), RotpGreenDayAddon.MOD_ID);
    
 // ======================================== Green Day Stand ========================================
    
    public static final RegistryObject<StandEntityAction> GRREN_DAY_PUNCH = ACTIONS.register("gd_punch",
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(InitSounds.GREEN_DAY_PUNCH_LIGHT)
                    .standSound(Phase.WINDUP, InitSounds.GREEN_DAY_ORA)));
    
    public static final RegistryObject<StandEntityAction> GREEN_DAY_BARRAGE = ACTIONS.register("gd_barrage",
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(InitSounds.GREEN_DAY_BARRAGE)
                    .standSound(InitSounds.GREEN_DAY_ORA_ORA_ORA)));
    
    public static final RegistryObject<StandEntityHeavyAttack> GREEN_DAY_COMBO_PUNCH = ACTIONS.register("gd_combo_punch",
            () -> new GreenDaySpike(new StandEntityHeavyAttack.Builder()
                    .resolveLevelToUnlock(1)
                    .punchSound(InitSounds.GREEN_DAY_PUNCH_HEAVY)
                    .standSound(Phase.WINDUP, InitSounds.GREEN_DAY_ORA_LONG)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityHeavyAttack> GREEN_DAY_HEAVY_PUNCH = ACTIONS.register("gd_heavy_punch",
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .punchSound(InitSounds.GREEN_DAY_PUNCH_HEAVY)
                    .standSound(Phase.WINDUP, InitSounds.GREEN_DAY_ORA_LONG)
                    .partsRequired(StandPart.ARMS)
                    .setFinisherVariation(GREEN_DAY_COMBO_PUNCH)
                    .shiftVariationOf(GRREN_DAY_PUNCH).shiftVariationOf(GREEN_DAY_BARRAGE)));

    public static final RegistryObject<StandEntityAction> GREEN_DAY_BLOCK = ACTIONS.register("gd_block",
            StandEntityBlock::new);


    public static final RegistryObject<StandEntityAction> GREEN_DAY_MOLD = ACTIONS.register("gd_mold",
            () -> new GreenDayMoldAction(new StandEntityAction.Builder().standPerformDuration(0)
                    .holdToFire(40,false).resolveLevelToUnlock(2).standRecoveryTicks(2)
            ));

    public static final RegistryObject<StandEntityAction> MOLD_HEAL = ACTIONS.register("gd_heal",
            () -> new HealMold(new StandEntityAction.Builder().resolveLevelToUnlock(2).staminaCostTick(6)
                    .holdType()

            ));

    public static final RegistryObject<StandAction> SHOT_LEFT_ARM = ACTIONS.register("gd_shot_left_arm",
            () -> new GreenDayShotLeftArm(new StandAction.Builder().resolveLevelToUnlock(3).staminaCost(150)
                    .cooldown(60,0,.66666F)
            ));
    public static final RegistryObject<StandAction> SHOT_RIGHT_ARM = ACTIONS.register("gd_shot_right_arm",
            () -> new GreenDayShotRightArm(new StandAction.Builder().resolveLevelToUnlock(3).staminaCost(150)
                    .cooldown(60,0,.66666F)
            ));




    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<GreenDayStandEntity>> GREEN_DAY_STAND =
            new EntityStandRegistryObject<>("green_day",
                    STANDS, 
                    () -> new EntityStandType<StandStats>(
                            0xC2E2DA, ModStandsInit.PART_5_NAME,

                            new StandAction[] {
                                    GRREN_DAY_PUNCH.get(),
                                    GREEN_DAY_BARRAGE.get(),
                                    SHOT_LEFT_ARM.get()
                            },
                            new StandAction[] {
                                    GREEN_DAY_BLOCK.get(),
                                    GREEN_DAY_MOLD.get(),
                                    MOLD_HEAL.get(),

                            },


                            StandStats.class, new StandStats.Builder()
                            .tier(6)
                            .power(15.70)
                            .speed(10.0)
                            .range(2.0, 256.0)
                            .durability(15.0)
                            .precision(4.0)
                            .build("Green Day"),

                            new StandType.StandTypeOptionals()
                            .addSummonShout(InitSounds.USER_GRREN_DAY_STAND)
                            .addOst(InitSounds.GREEN_DAY_OST)),

                    InitEntities.ENTITIES, 
                    () -> new StandEntityType<GreenDayStandEntity>(GreenDayStandEntity::new, 0.65F, 1.95F)
                    .summonSound(InitSounds.GREEN_DAY_SUMMON)
                    .unsummonSound(InitSounds.GREEN_DAY_UNSUMMON))
            .withDefaultStandAttributes();
}
