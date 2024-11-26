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

public class SetMaxBlockPacket {
    private final int entityID;
    private final long maxBlock;

    public SetMaxBlockPacket(int entityID, long maxBlock){
        this.entityID = entityID;
        this.maxBlock = maxBlock;
    }


    public static void encode(SetMaxBlockPacket msg, PacketBuffer buf) {
        buf.writeInt(msg.entityID);
        buf.writeLong(msg.maxBlock);
    }

    public static SetMaxBlockPacket decode(PacketBuffer buf) {
        return new SetMaxBlockPacket(buf.readInt(), buf.readLong());
    }


    public static void handle(SetMaxBlockPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Entity entity = ClientUtil.getEntityById(msg.entityID);
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                LazyOptional<LivingData> playerDataOptional = living.getCapability(LivingDataProvider.CAPABILITY);
                playerDataOptional.ifPresent(playerData ->{
                    playerData.setMaxBlock((int) msg.maxBlock);
                });
            }
        });
        ctx.get().setPacketHandled(true);

    }
}
