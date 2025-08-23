package net.missing.lushgrove;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.missing.lushgrove.block.ModBlocks;
import net.missing.lushgrove.packet.TeleportPacket;
import org.lwjgl.glfw.GLFW;

public class LushGroveClient implements ClientModInitializer {

    private static KeyBinding teleportkeyBinding;

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

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (teleportkeyBinding.wasPressed()) {
                ClientPlayNetworking.send(TeleportPacket.INSTANCE);
            }
        });
    }
}