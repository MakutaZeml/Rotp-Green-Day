package com.zeml.rotp_zgd.client.render.entity.renderer.damaging.projectile;

import com.github.standobyte.jojo.client.render.entity.renderer.SimpleEntityRenderer;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.ZoomPunchEntity;
import com.zeml.rotp_zgd.client.render.entity.model.damaging.projectile.ShootingHandModel;
import com.zeml.rotp_zgd.entity.projectile.ShootingArmEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ShootingArmRenderer extends SimpleEntityRenderer<ShootingArmEntity, ShootingHandModel> {
    public ShootingArmRenderer(EntityRendererManager renderManager) {
        super(renderManager, new ShootingHandModel(), DefaultPlayerSkin.getDefaultSkin());
    }


    @Override
    public ResourceLocation getTextureLocation(ShootingArmEntity entity) {
        Entity owner = entity.getOwner();
        return owner != null ? entityRenderDispatcher.getRenderer(owner).getTextureLocation(owner) :
                Minecraft.getInstance().player.getSkinTextureLocation();
    }
}
