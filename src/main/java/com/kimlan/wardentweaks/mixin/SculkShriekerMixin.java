package com.kimlan.wardentweaks.mixin;

import static com.kimlan.wardentweaks.WardenTweaks.CONFIG;

import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.entity.SculkShriekerBlockEntity;
import net.minecraft.world.entity.monster.warden.WardenSpawnTracker;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(SculkShriekerBlock.class)
abstract class SculkShriekerBlockMixin {
	@ModifyConstant(method = "<init>", constant = @Constant(intValue = 0, ordinal = 2))
	private int configuredCanSummon(int value) {
		return CONFIG.placedShriekersCanSpawnWarden() ? 1 : 0;
	}
}

@Mixin(SculkShriekerBlockEntity.class)
abstract class SculkShriekerBlockEntityMixin {
	@Shadow
	private int warningLevel;

	@ModifyConstant(method = "trySummonWarden", constant = @Constant(intValue = 4))
	private int configuredSpawnWardenWarningLevel(int value) {
		return CONFIG.spawnWardenWarningLevel();
	}

	@ModifyVariable(method = "playWardenReplySound", at = @At("STORE"), ordinal = 0)
	private SoundEvent modifiedWardenReplySound(SoundEvent soundEvent) {
		int warningLevelDifference = CONFIG.spawnWardenWarningLevel() - this.warningLevel;
        if (warningLevelDifference <= 0) {
			return SoundEvents.WARDEN_LISTENING_ANGRY;
		}
		return switch (warningLevelDifference) {
			case 1 -> SoundEvents.WARDEN_NEARBY_CLOSEST;
			case 2 -> SoundEvents.WARDEN_NEARBY_CLOSER;
			default -> SoundEvents.WARDEN_NEARBY_CLOSE;
		};
	}
}

@Mixin(WardenSpawnTracker.class)
abstract class WardenSpawnTrackerMixin {
	@ModifyConstant(method = "tick()V", constant = @Constant(intValue = 12000))
	private int configuredWarnDecreaseCooldown(int value) {
		return CONFIG.warnDecreaseCooldown();
	}

	@ModifyConstant(method = "setWarningLevel(I)V", constant = @Constant(intValue = 4))
	private int configuredMaxWarningLevel(int value) {
		return CONFIG.maxWarningLevel();
	}
}