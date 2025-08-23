package net.missing.lushgrove.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.missing.lushgrove.LushGrove;
import net.missing.lushgrove.block.ModBlocks;
import net.missing.lushgrove.item.custom.EnderPactItem;
import net.missing.lushgrove.item.custom.ModFoodComponents;
import net.missing.lushgrove.item.custom.OverworldPactItem;

public class ModItems {



    public static final Item VOIDPEPPER = registerItem("voidpepper", new Item(new Item.Settings().food(ModFoodComponents.VOIDPEPPER)));
    public static final Item GOLDENVOIDPEPPER = registerItem("golden_voidpepper", new Item(new Item.Settings().food(ModFoodComponents.GOLDENVOIDPEPPER)));

    public static final Item VOIDPEPPER_SEEDS = registerItem("voidpepper_seeds",
            new AliasedBlockItem(ModBlocks.VOIDPEPPER_CROP, new Item.Settings()));

    public static final Item ENDER_PACT = registerItem("ender_pact", new EnderPactItem(new Item.Settings()));

    public static final Item OVERWORLD_PACT = registerItem("overworld_pact", new OverworldPactItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
    return Registry.register(Registries.ITEM, Identifier.of(LushGrove.MOD_ID, name), item);
}

    public static void registerModItems() {
        LushGrove.LOGGER.info("Registering Mod Items for " + LushGrove.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {

        });
    }
}