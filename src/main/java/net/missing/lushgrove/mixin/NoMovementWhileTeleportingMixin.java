package net.missing.lushgrove.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;
import net.missing.lushgrove.LushGroveClient;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class NoMovementWhileTeleportingMixin {
	@Inject(at = @At("HEAD"), method = "tickMovement", cancellable = true)
	private void init(CallbackInfo info) {
		if (LushGroveClient.buildupTime >= 1) {
			info.cancel();
		}
	}
}