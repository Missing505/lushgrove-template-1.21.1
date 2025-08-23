package net.missing.lushgrove.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.missing.lushgrove.LushGrove;

public record TeleportC2SPacket(Vec3d pos) implements CustomPayload {

    public static final Identifier HOLO_MODE_PAYLOAD_ID = Identifier.of(LushGrove.MOD_ID, "teleport_packet");
    public static final Id<TeleportC2SPacket> ID = new Id<>(HOLO_MODE_PAYLOAD_ID);
    public static final PacketCodec<RegistryByteBuf, TeleportC2SPacket> CODEC;

    public TeleportC2SPacket(Vec3d pos) {
        this.pos = pos;
    }

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void write(PacketByteBuf buf) {
        buf.writeVec3d(pos);
    }

    public static TeleportC2SPacket read(PacketByteBuf buf) {
        return new TeleportC2SPacket(buf.readVec3d());
    }

    static {
        CODEC = PacketCodec.of(TeleportC2SPacket::write, TeleportC2SPacket::read);
    }
}