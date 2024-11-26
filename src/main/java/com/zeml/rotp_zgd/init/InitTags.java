package com.zeml.rotp_zgd.init;

import com.zeml.rotp_zgd.RotpGreenDayAddon;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class InitTags {

    public static final Tags.IOptionalNamedTag<EntityType<?>> NO_MOLD = EntityTypeTags.createOptional(new ResourceLocation(RotpGreenDayAddon.MOD_ID,"unmold"));


    public static void iniTags(){}

}
