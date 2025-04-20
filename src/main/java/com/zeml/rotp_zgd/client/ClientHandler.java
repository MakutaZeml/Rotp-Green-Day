package com.zeml.rotp_zgd.client;

import com.zeml.rotp_zgd.RotpGreenDayAddon;
import com.zeml.rotp_zgd.capability.entity.LivingData;
import com.zeml.rotp_zgd.capability.entity.LivingDataProvider;
import com.zeml.rotp_zgd.init.InitStatusEffect;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RotpGreenDayAddon.MOD_ID,value = Dist.CLIENT)
public class ClientHandler {



    @SubscribeEvent(priority = EventPriority.HIGHEST)
    @OnlyIn(Dist.CLIENT)
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event){
        PlayerEntity player = event.getPlayer();
        AbstractClientPlayerEntity abstractClientPlayer = (AbstractClientPlayerEntity) player;
        PlayerModel<AbstractClientPlayerEntity> model = event.getRenderer().getModel();
        if(player.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get())){
            model.rightArm.visible = false;
            model.rightSleeve.visible = false;
        }else {
            boolean rvisible = true;
            boolean rSvisible = abstractClientPlayer.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE);
            for(EffectInstance effects: player.getActiveEffects()){
                if(effects.getEffect().getRegistryName().getPath().equals("off_handless") && player.getMainArm() == HandSide.LEFT){
                    rvisible = false;
                    rSvisible = false;
                }
                model.rightSleeve.visible = rSvisible;
                model.rightArm.visible = rvisible;

            }
        }
        if(player.hasEffect(InitStatusEffect.LEFT_ARMLESS.get())){
            model.leftArm.visible = false;
            model.leftSleeve.visible = false;
        }else {
            boolean Lvisible = true;
            boolean LSvisible = abstractClientPlayer.isModelPartShown(PlayerModelPart.LEFT_SLEEVE);
            for(EffectInstance effects: player.getActiveEffects()){
                if(effects.getEffect().getRegistryName().getPath().equals("off_handless") && player.getMainArm() == HandSide.RIGHT){
                    Lvisible = false;
                    LSvisible = false;
                }
            }
            model.leftSleeve.visible = LSvisible;
            model.leftArm.visible = Lvisible;
        }
    }





}
