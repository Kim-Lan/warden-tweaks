package com.kimlan.wardentweaks.mixin;

import static com.kimlan.wardentweaks.WardenTweaks.CONFIG;

import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Warden.class)
abstract class WardenMixin {
    @ModifyConstant(method = "createAttributes", constant = @Constant(doubleValue = 500.0))
    private static double configuredWardenMaxHealth(double value) {
        return CONFIG.wardenMaxHealth();
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 5))
    private int configuredWardenExperiencePoints(int value) {
        return CONFIG.wardenExperiencePoints();
    }

    @ModifyConstant(method = "createAttributes", constant = @Constant(doubleValue = 30.0))
    private static double configuredWardenMeleeAttackDamage(double value) {
        return CONFIG.wardenMeleeAttackDamage();
    }
}