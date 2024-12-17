package com.kimlan.wardentweaks.mixin;

import net.minecraft.block.entity.SculkShriekerWarningManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SculkShriekerWarningManager.class)
public abstract class SculkShriekerWarningManagerMixin {
	@Shadow private int ticksSinceLastWarning;

	@Shadow protected abstract void decreaseWarningLevel();

	@Inject(at = @At("HEAD"), method = "tick")
	private void tickInject(CallbackInfo ci) {
		if (this.ticksSinceLastWarning >= 6000) {
			this.decreaseWarningLevel();
			this.ticksSinceLastWarning = 0;
		}
	}
}