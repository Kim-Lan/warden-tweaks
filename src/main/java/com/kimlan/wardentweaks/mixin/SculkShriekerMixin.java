package com.kimlan.wardentweaks.mixin;

import static com.kimlan.wardentweaks.WardenTweaks.CONFIG;

import net.minecraft.block.SculkShriekerBlock;
import net.minecraft.block.entity.SculkShriekerBlockEntity;
import net.minecraft.block.entity.SculkShriekerWarningManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(SculkShriekerWarningManager.class)
abstract class SculkShriekerWarningManagerMixin {
	@ModifyConstant(method = "tick()V", constant = @Constant(intValue = 12000))
	private int injectedWarnDecreaseCooldown(int value) {
		return CONFIG.warnDecreaseCooldown();
	}

	@ModifyConstant(method = "setWarningLevel(I)V", constant = @Constant(intValue = 4))
	private int injectedMaxWarningLevel(int value) {
		return CONFIG.maxWarningLevel();
	}
}

@Mixin(SculkShriekerBlockEntity.class)
abstract class SculkShriekerBlockEntityMixin {
	@Shadow
	private int warningLevel;

	@ModifyConstant(method = "trySpawnWarden", constant = @Constant(intValue = 4))
	private int injectedSpawnWardenWarningLevel(int value) {
		return CONFIG.spawnWardenWarningLevel();
	}

	@ModifyVariable(method = "playWarningSound", at = @At("STORE"), ordinal = 0)
	private SoundEvent injectedWarningSound(SoundEvent soundEvent) {
		int warningLevelDifference = CONFIG.spawnWardenWarningLevel() - this.warningLevel;
        return switch (warningLevelDifference) {
            case 1 -> SoundEvents.ENTITY_WARDEN_NEARBY_CLOSEST;
            case 2 -> SoundEvents.ENTITY_WARDEN_NEARBY_CLOSER;
            default -> SoundEvents.ENTITY_WARDEN_NEARBY_CLOSE;
        };
	}
}

@Mixin(SculkShriekerBlock.class)
abstract class SculkShriekerBlockMixin {
	@ModifyConstant(method = "<init>", constant = @Constant(intValue = 0, ordinal = 2))
	private int injectedCanSummon(int value) {
		return CONFIG.allShriekersCanSpawn() ? 1 : 0;
	}
}