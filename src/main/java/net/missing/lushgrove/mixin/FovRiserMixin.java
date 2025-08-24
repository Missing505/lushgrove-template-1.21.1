package net.missing.lushgrove.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.missing.lushgrove.LushGroveClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class FovRiserMixin {
	@Inject(at = @At("RETURN"), method = "getFovMultiplier", cancellable = true)
	private void init(CallbackInfoReturnable<Float> cir) {
		cir.setReturnValue(cir.getReturnValue()+(LushGroveClient.buildupTime*80));
	}
}