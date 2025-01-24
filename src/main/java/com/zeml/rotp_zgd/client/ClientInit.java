package com.zeml.rotp_zgd.client;

import com.github.standobyte.jojo.client.ClientSetup;
import com.github.standobyte.jojo.client.playeranim.PlayerAnimationHandler;
import com.github.standobyte.jojo.client.render.armor.model.GlovesModel;
import com.github.standobyte.jojo.client.render.entity.layerrenderer.*;
import com.github.standobyte.jojo.client.render.entity.layerrenderer.barrage.BarrageFistAfterimagesLayer;
import com.github.standobyte.jojo.client.ui.marker.MarkerRenderer;
import com.zeml.rotp_zgd.RotpGreenDayAddon;
import com.zeml.rotp_zgd.client.render.entity.layer.MoldLayer;
import com.zeml.rotp_zgd.client.render.entity.renderer.damaging.projectile.ShootingArmRenderer;
import com.zeml.rotp_zgd.client.render.entity.renderer.stand.GreenDayStandRenderer;
import com.zeml.rotp_zgd.client.ui.marker.HandMarker;
import com.zeml.rotp_zgd.init.AddonStands;

import com.zeml.rotp_zgd.init.InitEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Map;

@EventBusSubscriber(modid = RotpGreenDayAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {
    
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(AddonStands.GREEN_DAY_STANDO.getEntityType(), GreenDayStandRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SHOOTING_ARM.get(), ShootingArmRenderer::new);


        event.enqueueWork(() -> {
            Minecraft mc = event.getMinecraftSupplier().get();

            Map<String, PlayerRenderer> skinMap = mc.getEntityRenderDispatcher().getSkinMap();
            addLayers(skinMap.get("default"), false);
            addLayers(skinMap.get("slim"), true);
            mc.getEntityRenderDispatcher().renderers.values().forEach(ClientInit::addLayersToEntities);

            MarkerRenderer.Handler.addRenderer(new HandMarker(mc));
        });

    }


    private static void addLayers(PlayerRenderer renderer, boolean slim) {
        addBipedLayers(renderer);
    }


    private static <T extends LivingEntity, M extends BipedModel<T>> void addLayersToEntities(EntityRenderer<?> renderer) {
        if (renderer instanceof LivingRenderer<?, ?>) {
            LivingRenderer<T, M> livingRenderer = (LivingRenderer<T, M>) renderer;
            if (((LivingRenderer<?, ?>) renderer).getModel() instanceof BipedModel<?>) {
                addBipedLayers(livingRenderer);
            }
            else {
                livingRenderer.addLayer(new MoldLayer<T, M>(livingRenderer, MoldLayer.NON_BIPED_PATH));
            }
        }
    }


    private static <T extends LivingEntity, M extends BipedModel<T>> void addBipedLayers(LivingRenderer<T, M> renderer) {
        renderer.addLayer(new MoldLayer<>(renderer, MoldLayer.BIPED_PATH));
    }

}
