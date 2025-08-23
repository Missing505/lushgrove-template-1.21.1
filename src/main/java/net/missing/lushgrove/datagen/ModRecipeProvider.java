package net.missing.lushgrove.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.missing.lushgrove.LushGrove;
import net.missing.lushgrove.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.VOIDPEPPER_SEEDS, 1)
                .input(ModItems.VOIDPEPPER)
                .criterion(hasItem(ModItems.VOIDPEPPER), conditionsFromItem(ModItems.VOIDPEPPER_SEEDS))
                .offerTo(exporter, Identifier.of(LushGrove.MOD_ID, "voidpepper_seeds_from_voidpepper"));
    }
}
