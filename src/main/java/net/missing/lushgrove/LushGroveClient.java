package net.missing.lushgrove;

import foundry.veil.api.event.VeilPostProcessingEvent;
import foundry.veil.api.event.VeilRenderLevelStageEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.missing.lushgrove.block.ModBlocks;
import net.missing.lushgrove.effect.ModEffects;
import net.missing.lushgrove.packet.TeleportC2SPacket;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class LushGroveClient implements ClientModInitializer {

    private static KeyBinding teleportkeyBinding;
    public static float blocksMoved = 0;
    public static float buildupTime = 0;
    public static Vec3d teleportPos;

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.ANEMONE_HARMONY);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.ANEMONE_HARMONY_POT);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.VOIDPEPPER_CROP);

        teleportkeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.lushgrove.teleport", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_R, // The keycode of the key
                "category.lushgrove.lushgrove" // The translation key of the keybinding's category.
        ));

        WorldRenderEvents.BEFORE_ENTITIES.register((t)->{
            if (buildupTime >= 1) {
                LushGroveClient.blocksMoved += MinecraftClient.getInstance().getRenderTickCounter().getLastFrameDuration();
                if (LushGroveClient.blocksMoved > 5) {
                    LushGroveClient.blocksMoved = 5;
                }
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                if (client.player.hasStatusEffect(ModEffects.ENDER_PACT)) {
                    if (teleportkeyBinding.isPressed()) {
                        if (buildupTime <= 1) {
                            buildupTime += 0.3f;
                        }
                    }
                    if (!teleportkeyBinding.isPressed()) {
                        if (buildupTime >= 1) {
                            blocksMoved = 0;
                            client.execute(() -> {
                                ClientPlayNetworking.send(new TeleportC2SPacket(teleportPos));
                            });
                        }
                        buildupTime = 0;
                    }
                }
            }
        });
    }
}