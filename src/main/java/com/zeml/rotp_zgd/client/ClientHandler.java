package com.zeml.rotp_zgd.client;

import com.zeml.rotp_zgd.RotpGreenDayAddon;
import com.zeml.rotp_zgd.capability.entity.LivingData;
import com.zeml.rotp_zgd.capability.entity.LivingDataProvider;
import com.zeml.rotp_zgd.init.InitStatusEffect;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RotpGreenDayAddon.MOD_ID,value = Dist.CLIENT)
public class ClientHandler {
    private static boolean originalRightSleeveVisibility;
    private static boolean originalLeftSleeveVisibility;
    private static boolean hasRightStoredValues = false;
    private static boolean hasLeftStoredValues = false;


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    @OnlyIn(Dist.CLIENT)
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event){
        PlayerEntity player = event.getPlayer();
        PlayerModel<AbstractClientPlayerEntity> model = event.getRenderer().getModel();
        if(player.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get())){
            if(!hasRightStoredValues){
                originalRightSleeveVisibility =model.rightSleeve.visible;
                hasRightStoredValues = true;
            }
            model.rightArm.visible = false;
            model.rightSleeve.visible = false;
        }else {
            if(hasRightStoredValues){
                model.rightSleeve.visible = originalRightSleeveVisibility;
                model.rightArm.visible = true;
            }
        }
        if(player.hasEffect(InitStatusEffect.LEFT_ARMLESS.get())){
            if(!hasLeftStoredValues){
                originalLeftSleeveVisibility =model.leftSleeve.visible;
                hasLeftStoredValues = true;
            }
            model.leftArm.visible = false;
            model.leftSleeve.visible = false;
        }else {
            if(hasLeftStoredValues){
                model.leftSleeve.visible = originalLeftSleeveVisibility;
                model.leftArm.visible = true;
            }
        }

    }


}
