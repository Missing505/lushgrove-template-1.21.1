package net.missing.lushgrove.item.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.missing.lushgrove.LushGrove;
import net.missing.lushgrove.effect.ModEffects;

public class EnderPactItem extends Item {

    public EnderPactItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        user.setAttached(LushGrove.enderPacted, true);
        if (!user.getAbilities().creativeMode) {
            stack.decrement(1);
        }
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(ModEffects.ENDER_PACT, -1, 0));
        }

        return TypedActionResult.success(stack, world.isClient());
    }
}
