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

public class SetMoldActivePacket {
    private final int entityID;
    private final boolean isActive;

    public SetMoldActivePacket(int entityID, boolean isActive){
        this.entityID = entityID;
        this.isActive = isActive;
    }


    public static void encode(SetMoldActivePacket msg, PacketBuffer buf) {
        buf.writeInt(msg.entityID);
        buf.writeBoolean(msg.isActive);
    }

    public static SetMoldActivePacket decode(PacketBuffer buf) {
        return new SetMoldActivePacket(buf.readInt(), buf.readBoolean());
    }


    public static void handle(SetMoldActivePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Entity entity = ClientUtil.getEntityById(msg.entityID);
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                LazyOptional<LivingData> playerDataOptional = living.getCapability(LivingDataProvider.CAPABILITY);
                playerDataOptional.ifPresent(playerData ->{
                    playerData.setMoldActivated(msg.isActive);
                });
            }
        });
        ctx.get().setPacketHandled(true);

    }
}
