package net.missing.lushgrove;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.missing.lushgrove.datagen.ModBlockTagProvider;
import net.missing.lushgrove.datagen.ModLootTableProvider;
import net.missing.lushgrove.datagen.ModModelProvider;
import net.missing.lushgrove.datagen.ModRecipeProvider;

public class LushGroveDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
