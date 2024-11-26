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

public class ArmLeftPacket {
    private final int entityID;
    private final boolean leftHand;

    public ArmLeftPacket(int entityID, boolean leftHand){
        this.entityID = entityID;
        this.leftHand = leftHand;
    }


    public static void encode(ArmLeftPacket msg, PacketBuffer buf) {
        buf.writeInt(msg.entityID);
        buf.writeBoolean(msg.leftHand);
    }

    public static ArmLeftPacket decode(PacketBuffer buf) {
        return new ArmLeftPacket(buf.readInt(), buf.readBoolean());
    }


    public static void handle(ArmLeftPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Entity entity = ClientUtil.getEntityById(msg.entityID);
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                LazyOptional<LivingData> playerDataOptional = living.getCapability(LivingDataProvider.CAPABILITY);
                playerDataOptional.ifPresent(playerData ->{
                    playerData.setHasLeftArm(msg.leftHand);
                });
            }
        });
        ctx.get().setPacketHandled(true);

    }
}
