package net.missing.lushgrove.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;
import net.missing.lushgrove.block.ModBlocks;
import net.missing.lushgrove.block.custom.VoidpepperCropBlock;
import net.missing.lushgrove.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.ANEMONE_HARMONY);
        addPottedPlantDrops(ModBlocks.ANEMONE_HARMONY_POT);

        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.VOIDPEPPER_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(VoidpepperCropBlock.AGE, VoidpepperCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.VOIDPEPPER_CROP, this.cropDrops(ModBlocks.VOIDPEPPER_CROP, ModItems.VOIDPEPPER, ModItems.VOIDPEPPER_SEEDS, builder2));
    }
}
