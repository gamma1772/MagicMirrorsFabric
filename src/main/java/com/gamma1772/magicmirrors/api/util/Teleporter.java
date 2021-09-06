package com.gamma1772.magicmirrors.api.util;

import com.gamma1772.magicmirrors.common.util.TeleportUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class Teleporter {
    /**
     * Teleports the player to spawn position, whether that's a bed position, or any other set spawn location
     * */
    public static void teleportToSpawnPoint(PlayerEntity playerEntity, World world, boolean canTraverseDimensions) {
        ServerWorld serverWorld = (ServerWorld) world;
        ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
        BlockPos spawnPoint = player.getSpawnPointPosition();
        RegistryKey<World> spawnDimension = player.getSpawnPointDimension();
        ServerWorld destination = ((ServerWorld) world).getServer().getWorld(spawnDimension);

        if (canTraverseDimensions) {
            TeleportUtil.changeDimension(playerEntity, destination, spawnPoint);
            TeleportUtil.teleportToPos(playerEntity, spawnPoint);

            //player.sendMessage(new TranslatableText("info.magicmirrors.dimension_traverse"), true);
        }
        else {
            if (player.getSpawnPointDimension().equals(serverWorld.getRegistryKey())) {
                TeleportUtil.teleportToPos(playerEntity, spawnPoint);
                //player.sendMessage(new TranslatableText("info.magicmirrors.local_teleport"), true);
            }
            else {
                //player.sendMessage(new TranslatableText("info.magicmirrors.dimension"), true);
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
