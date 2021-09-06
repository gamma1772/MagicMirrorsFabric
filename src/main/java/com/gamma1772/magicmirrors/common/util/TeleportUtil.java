package com.gamma1772.magicmirrors.common.util;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public final class TeleportUtil {
    public static boolean changeDimension(PlayerEntity playerEntity, World destinationWorld, BlockPos spawnPoint) {
        ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
        ServerWorld destination = (ServerWorld) destinationWorld;

        Vec3d spawnVector = new Vec3d(spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ());

        TeleportTarget teleportTarget = new TeleportTarget(spawnVector, spawnVector, player.getYaw(), player.getPitch());

        if (player.getServerWorld().getRegistryKey() != destination.getRegistryKey()) {
            player.getServerWorld().getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, new ChunkPos(new BlockPos(spawnPoint)), 1, player.getId());

            FabricDimensions.teleport(player, destination, teleportTarget);

            return true;
        }
        else {
            //TODO
            return false;
        }
    }

    public static void teleportToPos(PlayerEntity playerEntity, BlockPos pos) {
        ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
        ChunkPos ticketPos = new ChunkPos(new BlockPos(pos));
        if (player.getVehicle() != null) {
            player.dismountVehicle();
        }
        player.getServerWorld().getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, ticketPos, 1, player.getId());

        player.requestTeleport(pos.getX(), pos.getY(), pos.getZ());
    }

    public static void teleportToPos(LivingEntity livingEntity, BlockPos pos) {
        ServerWorld serverWorld = (ServerWorld) livingEntity.getEntityWorld();
        ChunkPos ticketPos = new ChunkPos(new BlockPos(pos));

        serverWorld.getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, new ChunkPos(new BlockPos(pos)), 1, livingEntity.getId());

        livingEntity.requestTeleport(pos.getX(), pos.getY(), pos.getZ());
    }
}
