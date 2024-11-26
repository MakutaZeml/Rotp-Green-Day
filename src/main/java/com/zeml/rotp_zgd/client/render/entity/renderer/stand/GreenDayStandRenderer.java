package com.zeml.rotp_zgd.client.render.entity.renderer.stand;

import com.zeml.rotp_zgd.RotpGreenDayAddon;
import com.zeml.rotp_zgd.client.render.entity.model.stand.GreenDayStandModel;
import com.zeml.rotp_zgd.entity.stand.stands.GreenDayStandEntity;
import com.github.standobyte.jojo.client.render.entity.renderer.stand.StandEntityRenderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class GreenDayStandRenderer extends StandEntityRenderer<GreenDayStandEntity, GreenDayStandModel> {
    
    public GreenDayStandRenderer(EntityRendererManager renderManager) {
        super(renderManager, new GreenDayStandModel(), new ResourceLocation(RotpGreenDayAddon.MOD_ID, "textures/entity/stand/green_day.png"), 0);
    }
}
