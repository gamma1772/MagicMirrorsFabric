package com.gamma1772.magicmirrors.api.util;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class Teleporter {
    /**
     * Teleports the player to spawn position, whether that's a bed position, or any other set spawn location
     * */
    public static void teleportToSpawnPoint(PlayerEntity playerEntity, World world, boolean canTraverseDimensions) {
        ServerWorld serverWorld = (ServerWorld) world;
        ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
        BlockPos spawnPoint = player.getSpawnPointPosition();
        ChunkPos pos = new ChunkPos(new BlockPos(spawnPoint));
        Vec3d spawnVec = new Vec3d(spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ());
        RegistryKey<World> spawnDimension = player.getSpawnPointDimension();
        ServerWorld destination = ((ServerWorld) world).getServer().getWorld(spawnDimension);
        serverWorld.getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, pos, 1, player.getId());
        TeleportTarget target = new TeleportTarget(spawnVec, spawnVec, player.getYaw(), player.getPitch());

        if (canTraverseDimensions) {
            FabricDimensions.teleport(player, destination, target);
            player.requestTeleport(spawnPoint.getX() + 0.5F, spawnPoint.getY() + 0.6F, spawnPoint.getZ() + 0.5F);
            player.sendMessage(new TranslatableText("info.magicmirrors.dimension_traverse"), true);
        }
        else {
            if (player.getSpawnPointDimension().equals(serverWorld.getRegistryKey())) {
                player.requestTeleport(spawnPoint.getX() + 0.5F, spawnPoint.getY() + 0.6F, spawnPoint.getZ() + 0.5F);
                player.sendMessage(new TranslatableText("info.magicmirrors.local_teleport"), true);
            }
            else {
                player.sendMessage(new TranslatableText("info.magicmirrors.dimension"), true);
            }
        }

    }

    /**
     * Teleports an entity to a spawn point.
     * */
    public static void teleportEntityToSpawnPoint(LivingEntity entity, World world, BlockPos spawn) {

        ServerWorld serverWorld = (ServerWorld) world;
        ChunkPos pos = new ChunkPos(new BlockPos(spawn));

        entity.stopRiding();
        entity.setPosition(spawn.getX() + 0.5F, spawn.getY() + 0.5F, spawn.getZ() + 0.5F);
        serverWorld.getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, pos, 1, entity.getId());
    }
}
