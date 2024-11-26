package com.zeml.rotp_zgd.client.ui.marker;

import com.github.standobyte.jojo.client.ui.marker.MarkerRenderer;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.zeml.rotp_zgd.RotpGreenDayAddon;
import com.zeml.rotp_zgd.entity.projectile.ShootingArmEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class HandMarker extends MarkerRenderer {

    public HandMarker(Minecraft mc){
        super(new ResourceLocation(RotpGreenDayAddon.MOD_ID,"textures/action/gd_shot_left_arm.png"),mc);
    }


    @Override
    protected boolean shouldRender() {
        return !MCUtil.entitiesAround(ShootingArmEntity.class,mc.player,256/.6,false,shootingArmEntity -> shootingArmEntity.getOwner() == mc.player && shootingArmEntity.isAlive()).isEmpty();
    }

    @Override
    protected void updatePositions(List<MarkerInstance> list, float partialTick) {
        MCUtil.entitiesAround(ShootingArmEntity.class,mc.player,256/.6,false,shootingArmEntity -> shootingArmEntity.getOwner() == mc.player && shootingArmEntity.isAlive()).forEach(shootingArmEntity -> {
            list.add(new MarkerInstance(shootingArmEntity.getPosition(partialTick).add(0,shootingArmEntity.getBbHeight()*1.1,0), true));
        });
    }
}
