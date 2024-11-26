package com.zeml.rotp_zgd.network.server;

import com.github.standobyte.jojo.client.ClientUtil;
import com.zeml.rotp_zgd.capability.entity.LivingData;
import com.zeml.rotp_zgd.capability.entity.LivingDataProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ArmRightPacket {
    private final int entityID;
    private final boolean rightHand;

    public ArmRightPacket(int entityID, boolean rightHand){
        this.entityID = entityID;
        this.rightHand = rightHand;
    }


    public static void encode(ArmRightPacket msg, PacketBuffer buf) {
        buf.writeInt(msg.entityID);
        buf.writeBoolean(msg.rightHand);
    }

    public static ArmRightPacket decode(PacketBuffer buf) {
        return new ArmRightPacket(buf.readInt(), buf.readBoolean());
    }


    public static void handle(ArmRightPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Entity entity = ClientUtil.getEntityById(msg.entityID);
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                LazyOptional<LivingData> playerDataOptional = living.getCapability(LivingDataProvider.CAPABILITY);
                playerDataOptional.ifPresent(playerData ->{
                    playerData.setHasRightArm( msg.rightHand);
                });
            }
        });
        ctx.get().setPacketHandled(true);

    }
}
