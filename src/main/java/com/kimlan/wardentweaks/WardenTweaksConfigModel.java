package com.kimlan.wardentweaks;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.RangeConstraint;

@Config(name = "warden-tweaks", wrapperName = "WardenTweaksConfig")
public class WardenTweaksConfigModel {
    @RangeConstraint(min = 1, max = Integer.MAX_VALUE)
    public int warnDecreaseCooldown = 12000;

    @RangeConstraint(min = 1, max = Integer.MAX_VALUE)
    public int spawnWardenWarningLevel = 4;

    @RangeConstraint(min = 1, max = Integer.MAX_VALUE)
    public int maxWarningLevel = 4;

    public boolean allShriekersCanSpawn = false;

    @RangeConstraint(min = 1.0, max = Double.MAX_VALUE)
    public double wardenMaxHealth = 500.0;

    @RangeConstraint(min = 0, max = Integer.MAX_VALUE)
    public int wardenExperiencePoints = 5;

    @RangeConstraint(min = 0.0, max = Double.MAX_VALUE)
    public double wardenMeleeAttackDamage = 30.0;
}
