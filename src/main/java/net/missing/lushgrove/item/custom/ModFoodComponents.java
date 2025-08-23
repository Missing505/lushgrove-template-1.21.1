package net.missing.lushgrove.item.custom;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {

    public static final FoodComponent VOIDPEPPER = new FoodComponent.Builder().nutrition(5).saturationModifier(4)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 100), 1).build();

    public static final FoodComponent GOLDENVOIDPEPPER = new FoodComponent.Builder().nutrition(5).saturationModifier(4)
            .nutrition(5)
            .saturationModifier(1.5F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 500, 0), 1.0F)
            .alwaysEdible()
            .build();
}
