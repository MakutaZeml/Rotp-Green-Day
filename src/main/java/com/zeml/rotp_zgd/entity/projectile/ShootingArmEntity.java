package com.zeml.rotp_zgd.entity.projectile;

import com.github.standobyte.jojo.entity.damaging.projectile.ModdedProjectileEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.zeml.rotp_zgd.capability.entity.LivingData;
import com.zeml.rotp_zgd.capability.entity.LivingDataProvider;
import com.zeml.rotp_zgd.init.InitEntities;
import com.zeml.rotp_zgd.init.InitSounds;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import java.util.List;

public class ShootingArmEntity extends ModdedProjectileEntity {
    private boolean isRight = true;
    private boolean isSlim = false;
    private int tickCount = 0;

    public ShootingArmEntity(LivingEntity shooter, World world) {
        super(InitEntities.SHOOTING_ARM.get(), shooter, world);
        this.setNoGravity(false);
    }



    public ShootingArmEntity(EntityType<? extends ShootingArmEntity> type, World world) {
        super(type, world);
        this.setNoGravity(false);
    }

    @Override
    public int ticksLifespan() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected float getBaseDamage() {
        return 5;
    }

    @Override
    public void tick() {
        super.tick();
        tickCount++;

        if(!this.isOnGround()){
            this.setDeltaMovement(this.getDeltaMovement().add(0,-.08F,0));
        }
        System.out.println(this.getDeltaMovement());

        this.setNoGravity(false);

        if(!this.getPassengers().isEmpty()){
            List<Entity> passengers = this.getPassengers();{
                passengers.forEach(passenger ->{
                    if(this.tickCount%25==0){
                        passenger.hurt(DamageSource.IN_WALL,2);
                    }
                });
            }
        }
    }

    @Override
    public void playerTouch(PlayerEntity player) {
        if(!player.level.isClientSide){
            if( this.getOwner() != null && player == this.getOwner() && (this.isOnGround()|| this.onGround)){
                LazyOptional<LivingData> livingDataOptional = player.getCapability(LivingDataProvider.CAPABILITY);
                livingDataOptional.ifPresent(data ->{
                    if(this.isRight){
                        data.setHasRightArm(true);
                    }else {
                        data.setHasLeftArm(true);
                    }
                });
                this.remove();
            }
        }else {
            if( this.getOwner() != null && player == this.getOwner() && (this.isOnGround()|| this.onGround)){
                player.playSound(SoundEvents.ITEM_PICKUP,1F,1F);
            }
        }
    }


    @Override
    protected void onHitBlock(BlockRayTraceResult blockRayTraceResult){
        Vector3d pos = position();
        Vector3d movementVec = getDeltaMovement();
        Direction hitFace = blockRayTraceResult.getDirection();
        Vector3d blockVec = Vector3d.atCenterOf(blockRayTraceResult.getBlockPos()).add(Vector3d.atLowerCornerOf(hitFace.getNormal()).scale(0.5));
        double k;
        switch (hitFace.getAxis()) {
            case X:
                k = (blockVec.x - pos.x) / movementVec.x;
                break;
            case Y:
                k = (blockVec.y - pos.y) / movementVec.y;
                break;
            case Z:
                k = (blockVec.z - pos.z) / movementVec.z;
                break;
            default:
                return;
        }

        setPos(
                getX() + movementVec.x * k,
                getY() + movementVec.y * k,
                getZ() + movementVec.z * k);
        setDeltaMovement(Vector3d.ZERO);
        this.setNoGravity(false);
        this.setOnGround(true);
    }

    @Override
    public double getPassengersRidingOffset() {
        return -1;
    }

    public void setRight(boolean right) {
        this.isRight = right;
    }

    public boolean isRight() {
        return this.isRight;
    }

    public void setSlim(boolean slim) {
        this.isSlim = slim;
    }

    public boolean isSlim() {
        return this.isSlim;
    }

    @Override
    protected float getMaxHardnessBreakable() {
        return 0;
    }

    @Override
    public boolean standDamage() {
        return false;
    }


    @Override
    protected void onHitEntity(EntityRayTraceResult entityRayTraceResult) {
        if (!level.isClientSide() && isAlive()) {

            Entity target = entityRayTraceResult.getEntity();
            LivingEntity owner = getOwner();
            boolean entityHurt = hurtTarget(target, owner);
            if(this.getPassengers().isEmpty()){
                target.startRiding(this);
            }
            int prevTargetFireTimer = target.getRemainingFireTicks();
            if (isOnFire()) {
                target.setSecondsOnFire(5);
            }
            if (entityHurt) {

                if (owner instanceof StandEntity && target instanceof LivingEntity) {
                    LivingEntity standUser = ((StandEntity) owner).getUser();
                    if (standUser != null) {
                        LivingEntity livingTarget = (LivingEntity) target;
                        if (standUser instanceof PlayerEntity) {
                            livingTarget.setLastHurtByPlayer((PlayerEntity) standUser);
                        }
                        livingTarget.setLastHurtByMob(standUser);
                    }
                }
            }
            else {
                target.setRemainingFireTicks(prevTargetFireTimer);
            }
            afterEntityHit(entityRayTraceResult, entityHurt);
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("isRight", this.isRight);
        nbt.putBoolean("isSlim",this.isSlim);
        nbt.putInt("life",this.tickCount);
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.isRight = nbt.getBoolean("isRight");
        this.isSlim = nbt.getBoolean("isSlim");
        this.tickCount = nbt.getInt("life");
    }


}
