package net.missing.lushgrove.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.missing.lushgrove.LushGrove;
import net.missing.lushgrove.effect.ModEffects;

public class OverworldPactItem extends Item {

    public OverworldPactItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            if (user.hasAttached(LushGrove.enderPacted)) {
                user.removeAttached(LushGrove.enderPacted);

                user.removeStatusEffect(ModEffects.ENDER_PACT);

                user.sendMessage(Text.literal("You are no longer bound by the Ender Pact."), true);
            } else {
                user.sendMessage(Text.literal("You are not under the Ender Pact."), true);
            }

            user.getStackInHand(hand).decrement(1);
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
