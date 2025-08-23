package net.missing.lushgrove.mixin;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.missing.lushgrove.LushGroveClient;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraDetacherMixin {
	@Shadow protected abstract void moveBy(float f, float g, float h);

	@Shadow public abstract Quaternionf getRotation();

	@Shadow public abstract Vec3d getPos();

	@Shadow protected abstract void setPos(Vec3d pos);

	@Inject(at = @At("TAIL"), method = "update")
	private void init(CallbackInfo info) {
		if (LushGroveClient.buildupTime >= 1) {
			Vec3d pos = getPos().add(MinecraftClient.getInstance().getCameraEntity().getRotationVec(0f).normalize().multiply(easeOutCirc(LushGroveClient.blocksMoved/5)*5));
			LushGroveClient.teleportPos = pos;
			setPos(pos);
		}
	}
	float easeOutCirc(float x) {
		return (float)Math.sqrt(1f - Math.pow(x - 1f, 2f));
	}
}