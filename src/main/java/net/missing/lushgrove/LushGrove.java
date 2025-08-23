package net.missing.lushgrove;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.missing.lushgrove.block.ModBlocks;
import net.missing.lushgrove.effect.ModEffects;
import net.missing.lushgrove.item.ModItems;
import net.missing.lushgrove.packet.ModPackets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LushGrove implements ModInitializer {

	public static final String MOD_ID = "lushgrove";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final AttachmentType<Boolean> enderPacted = AttachmentRegistry.create(Identifier.of(LushGrove.MOD_ID, "ender_pact"),
			AttachmentRegistry.Builder::copyOnDeath);



	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerEffects();
		ModPackets.init();
		ServerTickEvents.START_WORLD_TICK.register(serverWorld ->
		{
			serverWorld.getPlayers().forEach(serverPlayerEntity ->
			{
				if (serverPlayerEntity.hasAttached(enderPacted)){
					serverPlayerEntity.addStatusEffect(new StatusEffectInstance(ModEffects.ENDER_PACT, -1, 0));
				}
			});
		});
	}
}