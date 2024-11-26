package com.zeml.rotp_zgd.client.render.entity.model.stand;

import com.zeml.rotp_zgd.entity.stand.stands.GreenDayStandEntity;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.client.render.entity.model.stand.HumanoidStandModel;
import com.github.standobyte.jojo.client.render.entity.pose.ModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.RotationAngle;
import com.github.standobyte.jojo.client.render.entity.pose.anim.PosedActionAnimation;
import com.github.standobyte.jojo.entity.stand.StandPose;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports

public class GreenDayStandModel extends HumanoidStandModel<GreenDayStandEntity> {
	private final ModelRenderer pipes;
	private final ModelRenderer pipe_r1;
	private final ModelRenderer pipe_r2;
	private final ModelRenderer pipe_r3;
	private final ModelRenderer pipe_r4;
	private final ModelRenderer pipe_r5;
	private final ModelRenderer pipe_r6;
	private final ModelRenderer pipe_r7;
	private final ModelRenderer pipe_r8;
	private final ModelRenderer pipe_r9;
	private final ModelRenderer pipe_r10;
	private final ModelRenderer pipe_r11;
	private final ModelRenderer pipe_r12;
	private final ModelRenderer pipe_r13;
	private final ModelRenderer pipe_r14;
	private final ModelRenderer pipes2;
	private final ModelRenderer pipe_r15;
	private final ModelRenderer pipe_r16;
	private final ModelRenderer pipe_r17;
	private final ModelRenderer pipe_r18;
	private final ModelRenderer pipe_r19;
	private final ModelRenderer torso_r1;
	private final ModelRenderer LShoulderpad;
	private final ModelRenderer LShoulderPipe;
	private final ModelRenderer pipe_r20;
	private final ModelRenderer pipe_r21;
	private final ModelRenderer RShoulderpad;
	private final ModelRenderer RShoulderPipe;
	private final ModelRenderer pipe_r22;
	private final ModelRenderer pipe_r23;



    public GreenDayStandModel() {
		super();
		addHumanoidBaseBoxes(null);
		texWidth = 128;
		texHeight = 128;

		head.texOffs(34, 10).addBox(-2.0F, -10.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		head.texOffs(35, 4).addBox(-1.75F, -10.25F, -1.75F, 3.5F, 0.5F, 3.5F, 0.0F, false);

		pipes = new ModelRenderer(this);
		pipes.setPos(-1.75F, -9.75F, 0.5F);
		head.addChild(pipes);


		pipe_r1 = new ModelRenderer(this);
		pipe_r1.setPos(1.75F, -0.25F, -2.75F);
		pipes.addChild(pipe_r1);
		setRotationAngle(pipe_r1, 1.5708F, 0.6545F, 1.5708F);
		pipe_r1.texOffs(41, 29).addBox(-0.25F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, true);

		pipe_r2 = new ModelRenderer(this);
		pipe_r2.setPos(1.75F, -0.25F, 1.75F);
		pipes.addChild(pipe_r2);
		setRotationAngle(pipe_r2, -1.5708F, -0.6545F, 1.5708F);
		pipe_r2.texOffs(44, 26).addBox(-0.25F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, true);

		pipe_r3 = new ModelRenderer(this);
		pipe_r3.setPos(4.0F, -0.25F, -0.5F);
		pipes.addChild(pipe_r3);
		setRotationAngle(pipe_r3, 0.0F, 0.0F, 0.9163F);
		pipe_r3.texOffs(44, 29).addBox(-0.25F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, true);

		pipe_r4 = new ModelRenderer(this);
		pipe_r4.setPos(5.9263F, 1.5324F, 1.0F);
		pipes.addChild(pipe_r4);
		setRotationAngle(pipe_r4, 0.0F, 0.0F, 0.7418F);
		pipe_r4.texOffs(41, 23).addBox(-0.25F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, true);

		pipe_r5 = new ModelRenderer(this);
		pipe_r5.setPos(6.0F, 1.6F, -2.0F);
		pipes.addChild(pipe_r5);
		setRotationAngle(pipe_r5, 0.0F, 0.0F, 0.7418F);
		pipe_r5.texOffs(41, 26).addBox(-0.35F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, true);

		pipe_r6 = new ModelRenderer(this);
		pipe_r6.setPos(-2.4263F, 1.5324F, 1.0F);
		pipes.addChild(pipe_r6);
		setRotationAngle(pipe_r6, 0.0F, 0.0F, -0.7418F);
		pipe_r6.texOffs(35, 29).addBox(-0.25F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, false);

		pipe_r7 = new ModelRenderer(this);
		pipe_r7.setPos(-2.5F, 1.6F, -2.0F);
		pipes.addChild(pipe_r7);
		setRotationAngle(pipe_r7, 0.0F, 0.0F, -0.7418F);
		pipe_r7.texOffs(32, 27).addBox(-0.15F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, false);

		pipe_r8 = new ModelRenderer(this);
		pipe_r8.setPos(1.75F, 1.6062F, -4.6087F);
		pipes.addChild(pipe_r8);
		setRotationAngle(pipe_r8, 1.5708F, 0.829F, 1.5708F);
		pipe_r8.texOffs(35, 23).addBox(-0.25F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, true);

		pipe_r9 = new ModelRenderer(this);
		pipe_r9.setPos(4.75F, 1.6062F, -4.6087F);
		pipes.addChild(pipe_r9);
		setRotationAngle(pipe_r9, 1.5708F, 0.829F, 1.5708F);
		pipe_r9.texOffs(35, 26).addBox(-0.25F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, true);

		pipe_r10 = new ModelRenderer(this);
		pipe_r10.setPos(-1.25F, 1.6F, 3.75F);
		pipes.addChild(pipe_r10);
		setRotationAngle(pipe_r10, -1.5708F, -0.829F, 1.5708F);
		pipe_r10.texOffs(38, 23).addBox(-0.35F, -0.4F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, true);

		pipe_r11 = new ModelRenderer(this);
		pipe_r11.setPos(1.75F, 1.6062F, 3.6087F);
		pipes.addChild(pipe_r11);
		setRotationAngle(pipe_r11, -1.5708F, -0.829F, 1.5708F);
		pipe_r11.texOffs(38, 26).addBox(-0.25F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, true);

		pipe_r12 = new ModelRenderer(this);
		pipe_r12.setPos(4.75F, 1.6062F, 3.6087F);
		pipes.addChild(pipe_r12);
		setRotationAngle(pipe_r12, -1.5708F, -0.829F, 1.5708F);
		pipe_r12.texOffs(38, 29).addBox(-0.25F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, true);

		pipe_r13 = new ModelRenderer(this);
		pipe_r13.setPos(-1.25F, 1.6F, -4.75F);
		pipes.addChild(pipe_r13);
		setRotationAngle(pipe_r13, 1.5708F, 0.829F, 1.5708F);
		pipe_r13.texOffs(32, 24).addBox(-0.35F, -0.4F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, true);

		pipe_r14 = new ModelRenderer(this);
		pipe_r14.setPos(-0.5F, -0.25F, -0.5F);
		pipes.addChild(pipe_r14);
		setRotationAngle(pipe_r14, 0.0F, 0.0F, -0.9163F);
		pipe_r14.texOffs(44, 23).addBox(-0.25F, -0.5F, -0.25F, 0.5F, 1.0F, 0.5F, 0.0F, false);

		pipes2 = new ModelRenderer(this);
		pipes2.setPos(-1.75F, -9.75F, 0.5F);
		head.addChild(pipes2);

		torso.texOffs(7, 45).addBox(-4.0F, 0.5F, 1.4F, 8.0F, 4.0F, 1.0F, 0.0F, false);

		pipe_r15 = new ModelRenderer(this);
		pipe_r15.setPos(-2.5059F, 0.1761F, 2.1F);
		torso.addChild(pipe_r15);
		setRotationAngle(pipe_r15, -0.1304F, -0.0114F, -0.0865F);
		pipe_r15.texOffs(21, 52).addBox(-0.25F, -1.0F, -0.25F, 0.5F, 2.0F, 0.5F, 0.0F, false);

		pipe_r16 = new ModelRenderer(this);
		pipe_r16.setPos(-1.2559F, 0.1761F, 2.1F);
		torso.addChild(pipe_r16);
		setRotationAngle(pipe_r16, -0.1308F, -0.0057F, -0.0433F);
		pipe_r16.texOffs(18, 52).addBox(-0.25F, -1.0F, -0.25F, 0.5F, 2.0F, 0.5F, 0.0F, false);

		pipe_r17 = new ModelRenderer(this);
		pipe_r17.setPos(2.5059F, 0.1761F, 2.1F);
		torso.addChild(pipe_r17);
		setRotationAngle(pipe_r17, -0.1304F, 0.0114F, 0.0865F);
		pipe_r17.texOffs(9, 52).addBox(-0.25F, -1.0F, -0.25F, 0.5F, 2.0F, 0.5F, 0.0F, true);

		pipe_r18 = new ModelRenderer(this);
		pipe_r18.setPos(1.2559F, 0.1761F, 2.1F);
		torso.addChild(pipe_r18);
		setRotationAngle(pipe_r18, -0.1308F, 0.0057F, 0.0433F);
		pipe_r18.texOffs(12, 52).addBox(-0.25F, -1.0F, -0.25F, 0.5F, 2.0F, 0.5F, 0.0F, true);

		pipe_r19 = new ModelRenderer(this);
		pipe_r19.setPos(0.0059F, 0.1761F, 2.1F);
		torso.addChild(pipe_r19);
		setRotationAngle(pipe_r19, -0.1309F, 0.0F, 0.0F);
		pipe_r19.texOffs(15, 52).addBox(-0.25F, -1.0F, -0.25F, 0.5F, 2.0F, 0.5F, 0.0F, true);

		torso_r1 = new ModelRenderer(this);
		torso_r1.setPos(0.0F, 2.75F, -1.85F);
		torso.addChild(torso_r1);
		setRotationAngle(torso_r1, -0.0436F, 0.0F, 0.0F);
		torso_r1.texOffs(7, 19).addBox(-4.0F, -2.0F, -0.5F, 8.0F, 4.0F, 1.0F, 0.0F, false);


		leftArmJoint.texOffs(31, 118).addBox(-1.0F, -1.0F, 2.1F, 2.0F, 2.0F, 0.0F, -0.1F, true);


		LShoulderpad = new ModelRenderer(this);
		LShoulderpad.setPos(0.625F, 0.625F, 0.0F);
		leftArm.addChild(LShoulderpad);
		LShoulderpad.texOffs(31, 87).addBox(-2.625F, -2.875F, -2.25F, 4.5F, 4.0F, 4.5F, 0.0F, true);
		LShoulderpad.texOffs(30, 79).addBox(-2.625F, 0.625F, -2.5F, 5.0F, 0.5F, 5.0F, 0.0F, true);

		LShoulderPipe = new ModelRenderer(this);
		LShoulderPipe.setPos(0.5513F, -3.4426F, -1.5F);
		LShoulderpad.addChild(LShoulderPipe);
		setRotationAngle(LShoulderPipe, 0.0F, 0.0F, -0.5236F);


		pipe_r20 = new ModelRenderer(this);
		pipe_r20.setPos(-1.4203F, -0.2564F, 0.0F);
		LShoulderPipe.addChild(pipe_r20);
		setRotationAngle(pipe_r20, 0.0F, 0.0F, 0.6109F);
		pipe_r20.texOffs(38, 75).addBox(-0.25F, -1.0F, 2.75F, 0.5F, 2.0F, 0.5F, 0.0F, true);
		pipe_r20.texOffs(44, 75).addBox(-0.25F, -1.0F, -0.25F, 0.5F, 2.0F, 0.5F, 0.0F, true);

		pipe_r21 = new ModelRenderer(this);
		pipe_r21.setPos(-0.3378F, 0.3686F, 0.0F);
		LShoulderPipe.addChild(pipe_r21);
		setRotationAngle(pipe_r21, 0.0F, 0.0F, 0.7418F);
		pipe_r21.texOffs(35, 75).addBox(-0.25F, -1.0F, 2.75F, 0.5F, 2.0F, 0.5F, 0.0F, true);
		pipe_r21.texOffs(41, 75).addBox(-0.25F, -1.0F, -0.25F, 0.5F, 2.0F, 0.5F, 0.0F, true);

		RShoulderpad = new ModelRenderer(this);
		RShoulderpad.setPos(-0.625F, 0.625F, 0.0F);
		rightArm.addChild(RShoulderpad);
		RShoulderpad.texOffs(31, 87).addBox(-1.875F, -2.875F, -2.25F, 4.5F, 4.0F, 4.5F, 0.0F, false);
		RShoulderpad.texOffs(30, 79).addBox(-2.375F, 0.625F, -2.5F, 5.0F, 0.5F, 5.0F, 0.0F, false);

		RShoulderPipe = new ModelRenderer(this);
		RShoulderPipe.setPos(-0.5513F, -3.4426F, -1.5F);
		RShoulderpad.addChild(RShoulderPipe);
		setRotationAngle(RShoulderPipe, 0.0F, 0.0F, 0.5236F);


		pipe_r22 = new ModelRenderer(this);
		pipe_r22.setPos(1.4203F, -0.2564F, 0.0F);
		RShoulderPipe.addChild(pipe_r22);
		setRotationAngle(pipe_r22, 0.0F, 0.0F, -0.6109F);
		pipe_r22.texOffs(38, 75).addBox(-0.25F, -1.0F, 2.75F, 0.5F, 2.0F, 0.5F, 0.0F, false);
		pipe_r22.texOffs(44, 75).addBox(-0.25F, -1.0F, -0.25F, 0.5F, 2.0F, 0.5F, 0.0F, false);

		pipe_r23 = new ModelRenderer(this);
		pipe_r23.setPos(0.3378F, 0.3686F, 0.0F);
		RShoulderPipe.addChild(pipe_r23);
		setRotationAngle(pipe_r23, 0.0F, 0.0F, -0.7418F);
		pipe_r23.texOffs(35, 75).addBox(-0.25F, -1.0F, 2.75F, 0.5F, 2.0F, 0.5F, 0.0F, false);
		pipe_r23.texOffs(41, 75).addBox(-0.25F, -1.0F, -0.25F, 0.5F, 2.0F, 0.5F, 0.0F, false);

		rightArmJoint.texOffs(31, 118).addBox(-1.0F, -1.0F, 2.1F, 2.0F, 2.0F, 0.0F, -0.1F, false);

		leftLegJoint.texOffs(68, 100).addBox(-1.0F, -0.6F, -2.5F, 2.0F, 2.0F, 1.0F, -0.1F, true);

		rightLegJoint.texOffs(68, 100).addBox(-1.0F, -0.6F, -2.5F, 2.0F, 2.0F, 1.0F, -0.1F, false);

    }

	@Override
	protected RotationAngle[][] initSummonPoseRotations() {
		return new RotationAngle[][] {
				new RotationAngle[]{
						RotationAngle.fromDegrees(head,0f, -0.1f, 0.25f),
						RotationAngle.fromDegrees(body,-10f, 0f, 0f),
						RotationAngle.fromDegrees(leftArm,0.75f, 1.25f, -0.5f),
						RotationAngle.fromDegrees(leftForeArm,-49.57f, -7.64f, -33.97f),
						RotationAngle.fromDegrees(rightArm,-0.75f, 1.25f, -0.5f),
						RotationAngle.fromDegrees(rightForeArm,-49.57f, 7.64f, 33.97f),
						RotationAngle.fromDegrees(leftLeg,5f, 0f, -2.5f),
						RotationAngle.fromDegrees(leftLowerLeg,5f, -0.22f, 2.49f),
						RotationAngle.fromDegrees(rightLeg,-7.5f, 0f, 2.5f),
						RotationAngle.fromDegrees(rightLowerLeg,14.99f, 0.65f, -2.41f)
				},
				new RotationAngle[]{
						RotationAngle.fromDegrees(head,2.5f, 0f, 0f),
						RotationAngle.fromDegrees(body,7.5f, 0f, 0f),
						RotationAngle.fromDegrees(leftArm,0.5f, 1f, 0f),
						RotationAngle.fromDegrees(leftForeArm,-73.38f, 13.8f, -19.4f),
						RotationAngle.fromDegrees(rightArm,-0.5f, 1f, 0f),
						RotationAngle.fromDegrees(rightForeArm,-73.38f, -13.8f, 19.4f),
						RotationAngle.fromDegrees(leftArm,0f, -0.75f, -0.25f),
						RotationAngle.fromDegrees(leftLowerLeg,55f, 0f, 0f),
						RotationAngle.fromDegrees(rightLeg,0f, -0.5f, -0.75f),
						RotationAngle.fromDegrees(rightLowerLeg,77.5f, 0f, 0f)
				}

		};
	}

	@Override
	protected void initActionPoses() {
        actionAnim.put(StandPose.RANGED_ATTACK, new PosedActionAnimation.Builder<GreenDayStandEntity>()
                .addPose(StandEntityAction.Phase.BUTTON_HOLD, new ModelPose<>(new RotationAngle[] {
                        new RotationAngle(body, 0.0F, -0.48F, 0.0F),
                        new RotationAngle(leftArm, 0.0F, 0.0F, 0.0F),
                        new RotationAngle(leftForeArm, 0.0F, 0.0F, 0.0F),
                        new RotationAngle(rightArm, -1.0908F, 0.0F, 1.5708F), 
                        new RotationAngle(rightForeArm, 0.0F, 0.0F, 0.0F)
                }))
                .build(idlePose));

		super.initActionPoses();
	}

	@Override
	protected ModelPose<GreenDayStandEntity> initIdlePose() {
		return new ModelPose<>(new RotationAngle[] {
				new RotationAngle(head,0,-0.00174532925199F,0.00436332312999F),
                new RotationAngle(body,-0.0872664625997F/2,0,0),
                new RotationAngle(leftArm,0.01308996939F,0.0218166156499F,-0.00872664625997F),
                new RotationAngle(leftForeArm,-0.865159710214F,-0.133343154852F,-0.592888346902F),
                new RotationAngle(rightArm,-0.01308996939F,0.0218166156499F,-0.00872664625997F),
                new RotationAngle(rightForeArm,-0.865159710214F,0.133343154852F,0.592888346902F),
                new RotationAngle(leftLeg,0.0436332312999F,0,-0.0436332312999F),
                new RotationAngle(leftLowerLeg,0.0872664625997F,-0.00383972435439F,0.0434586983747F),
                new RotationAngle(rightLeg,0F,.0F,0.0436332312999F),
                new RotationAngle(rightLowerLeg,0.261624854874F,0.011344640138F,-0.0420624349731F)
		});
	}

	@Override
	protected ModelPose<GreenDayStandEntity> initIdlePose2Loop() {
		return new ModelPose<>(new RotationAngle[] {

		});
	}
}