package net.missing.lushgrove.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.missing.lushgrove.LushGrove;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> ENDER_PACT = registerStatusEffect("ender_pact",
            new EnderPactEffect(StatusEffectCategory.NEUTRAL, 0x5f00a1));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(LushGrove.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        LushGrove.LOGGER.info("Registering Mod Effects for " + LushGrove.MOD_ID);
    }
}
