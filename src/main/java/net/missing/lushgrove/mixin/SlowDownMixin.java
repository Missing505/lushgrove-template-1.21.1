package net.missing.lushgrove.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.missing.lushgrove.LushGroveClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class SlowDownMixin {
	@Shadow private Vec3d velocity;

	@Inject(at = @At("TAIL"), method = "setVelocity(Lnet/minecraft/util/math/Vec3d;)V", cancellable = true)
	private void init(Vec3d velocity, CallbackInfo ci) {
		if (LushGroveClient.buildupTime > 0 && ((Object)this) instanceof ClientPlayerEntity) {
			this.velocity = velocity.multiply(Math.abs(1-LushGroveClient.buildupTime));
		}
	}
}