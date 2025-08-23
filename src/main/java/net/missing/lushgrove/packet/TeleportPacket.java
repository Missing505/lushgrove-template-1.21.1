package net.missing.lushgrove.packet;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.missing.lushgrove.LushGrove;
import net.missing.lushgrove.effect.ModEffects;

public class TeleportPacket implements ModPackets.ReceiverPacket<ServerPlayNetworking.Context> {
    @Override
    public void receive(ServerPlayNetworking.Context context) {
        if (context.player().hasStatusEffect(ModEffects.ENDER_PACT)){
            Vec3d teleport = context.player().getPos().add(context.player().getRotationVector().normalize().multiply(5));
            context.player().teleport(teleport.x, teleport.y, teleport.z, false);
        }
    }

    public static final TeleportPacket INSTANCE = new TeleportPacket();

    public static final CustomPayload.Id<TeleportPacket> ID = new CustomPayload.Id<>(Identifier.of(LushGrove.MOD_ID, "teleport"));
    public static final PacketCodec<ByteBuf, TeleportPacket> PACKET_CODEC = PacketCodec.unit(INSTANCE);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
