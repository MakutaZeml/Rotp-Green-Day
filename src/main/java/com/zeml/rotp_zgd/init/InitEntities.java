package com.zeml.rotp_zgd.init;

import com.zeml.rotp_zgd.RotpGreenDayAddon;

import com.zeml.rotp_zgd.entity.projectile.ShootingArmEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, RotpGreenDayAddon.MOD_ID);

    public static final RegistryObject<EntityType<ShootingArmEntity>> SHOOTING_ARM = ENTITIES.register("shooting_arm",
            () -> EntityType.Builder.<ShootingArmEntity>of(ShootingArmEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).setUpdateInterval(20).fireImmune()
                    .build(new ResourceLocation(RotpGreenDayAddon.MOD_ID, "shooting_arm").toString()));
    
}
