package com.zeml.rotp_zgd.capability.entity;

import com.zeml.rotp_zgd.init.InitStatusEffect;
import com.zeml.rotp_zgd.network.AddonPackets;
import com.zeml.rotp_zgd.network.server.SetMaxBlockPacket;
import com.zeml.rotp_zgd.network.server.SetMoldActivePacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class LivingData implements INBTSerializable<CompoundNBT> {
    private final LivingEntity entity;
    private int maxBlock=0;
    private int ticksInMold = 0;
    private int ticksWithoutMold = 0;
    private boolean moldActivated = false;


    public LivingData(LivingEntity entity) {
        this.entity = entity;
    }


    public void setMaxBlock(int maxBlock){
        this.maxBlock = maxBlock;
        if(entity instanceof ServerPlayerEntity){
            AddonPackets.sendToClient(new SetMaxBlockPacket(entity.getId(),maxBlock),(ServerPlayerEntity) entity);
        }
    }

    public int getMaxBlock(){
        return this.maxBlock;
    }

    public void setTicksInMold(int ticksInMold){
        this.ticksInMold = ticksInMold;
    }
    public int getTicksInMold(){
        return this.ticksInMold;
    }

    public void setTicksWithoutMold(int ticksWithoutMold) {
        this.ticksWithoutMold = ticksWithoutMold;
    }

    public int getTicksWithoutMold() {
        return this.ticksWithoutMold;
    }


    public void setMoldActivated(boolean moldActivated) {
        this.moldActivated = moldActivated;
        if(entity instanceof ServerPlayerEntity){
            AddonPackets.sendToClient(new SetMoldActivePacket(entity.getId(),moldActivated),(ServerPlayerEntity) entity);
        }
    }
    public boolean isMoldActivated() {
        return moldActivated;
    }

    public boolean isHasRightArm() {
        return !entity.hasEffect(InitStatusEffect.RIGHT_ARMLESS.get());
    }


    public boolean isHasLeftArm() {
        return !entity.hasEffect(InitStatusEffect.LEFT_ARMLESS.get());
    }

    public void syncWithAnyPlayer(ServerPlayerEntity player) {

        //AddonPackets.sendToClient(new TrPickaxesThrownPacket(entity.getId(), pickaxesThrown), player);
    }

    // If there is data that should only be known to the player, and not to other ones, sync it here instead.
    public void syncWithEntityOnly(ServerPlayerEntity player) {
        AddonPackets.sendToClient(new SetMaxBlockPacket(player.getId(),maxBlock),player);
        AddonPackets.sendToClient(new SetMoldActivePacket(player.getId(),moldActivated),player);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("MaxBlock",this.maxBlock);
        nbt.putInt("ticksInMold",this.ticksInMold);
        nbt.putInt("ticksWithoutMold",this.ticksWithoutMold);
        nbt.putBoolean("MoldActive", this.moldActivated);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.maxBlock = nbt.getInt("MaxBlock");
        this.ticksInMold = nbt.getInt("ticksInMold");
        this.ticksWithoutMold = nbt.getInt("ticksWithoutMold");
        this.moldActivated = nbt.getBoolean("MoldActive");
    }
}
