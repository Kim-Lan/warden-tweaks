package com.kimlan.wardentweaks.mixin;

import static com.kimlan.wardentweaks.WardenTweaks.CONFIG;

import net.minecraft.entity.mob.WardenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(WardenEntity.class)
abstract class WardenEntityMixin {
    @ModifyConstant(method = "addAttributes", constant = @Constant(doubleValue = 500.0))
    private static double injectedWardenMaxHealth(double value) {
        return CONFIG.wardenMaxHealth();
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 5))
    private int injectedWardenExperiencePoints(int value) {
        return CONFIG.wardenExperiencePoints();
    }

    @ModifyConstant(method = "addAttributes", constant = @Constant(doubleValue = 30.0))
    private static double injectedWardenMeleeAttackDamage(double value) {
        return CONFIG.wardenMeleeAttackDamage();
    }
}