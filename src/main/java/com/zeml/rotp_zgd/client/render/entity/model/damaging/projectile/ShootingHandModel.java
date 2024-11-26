package com.zeml.rotp_zgd.client.render.entity.model.damaging.projectile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zeml.rotp_zgd.entity.projectile.ShootingArmEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ShootingHandModel extends EntityModel<ShootingArmEntity> {

    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer RightArmSlim;
    private final ModelRenderer LeftArmSlim;

    public ShootingHandModel() {
        texWidth = 64;
        texHeight = 64;

        RightArm = new ModelRenderer(this);
        RightArm.setPos(0.0F, 0.0F, 0.0F);
        setRotationAngle(RightArm, -1.5708F, 0.0F, 0.0F);
        RightArm.texOffs(40, 16).addBox(-2.0F, -10.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        RightArm.texOffs(40, 32).addBox(-2.0F, -10.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setPos(0.0F, 0.0F, 0.0F);
        setRotationAngle(LeftArm, -1.5708F, 0.0F, 0.0F);
        LeftArm.texOffs(32, 48).addBox(-2.0F, -10.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        LeftArm.texOffs(48, 48).addBox(-2.0F, -10.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        RightArmSlim = new ModelRenderer(this);
        RightArmSlim.setPos(0.0F, 0.0F, 0.0F);
        setRotationAngle(RightArmSlim, -1.5708F, 0.0F, 0.0F);
        RightArmSlim.texOffs(40, 16).addBox(-1.5F, -10.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
        RightArmSlim.texOffs(40, 32).addBox(-1.5F, -10.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.25F, false);

        LeftArmSlim = new ModelRenderer(this);
        LeftArmSlim.setPos(0.0F, 0.0F, 0.0F);
        setRotationAngle(LeftArmSlim, -1.5708F, 0.0F, 0.0F);
        LeftArmSlim.texOffs(32, 48).addBox(-1.5F, -10.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
        LeftArmSlim.texOffs(48, 48).addBox(-1.5F, -10.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.25F, false);
    }


    @Override
    public void setupAnim(ShootingArmEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float yRotationOffset, float xRotation){
        boolean slim =false;
        if(entity.getOwner() instanceof ClientPlayerEntity){
            slim = ((ClientPlayerEntity) entity.getOwner()).getModelName().equals("slim");
        }
        this.RightArm.visible = entity.isRight() && !slim;
        this.RightArmSlim.visible = entity.isRight() && slim;
        this.LeftArm.visible = !entity.isRight() && !slim;
        this.LeftArmSlim.visible = !entity.isRight() && slim;

        this.RightArm.xRot = this.LeftArm.xRot = this.RightArmSlim.xRot = this.LeftArmSlim.xRot = (xRotation-90) * ((float)Math.PI / 180F);
        this.RightArm.yRot = this.LeftArm.yRot = this.RightArmSlim.yRot = this.LeftArmSlim.yRot = yRotationOffset * ((float)Math.PI / 180F);

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        RightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightArmSlim.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftArmSlim.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
