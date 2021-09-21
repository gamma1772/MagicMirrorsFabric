package com.gamma1772.magicmirrors.api.util.teleport;

import com.gamma1772.magicmirrors.common.util.TeleportUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class Teleporter {
    /**
     * Teleports the player to spawn position, whether that's a bed position, or any other set spawn location
     * @return status code
     * 0 - player has changed dimensions and teleported to a spawn point;
     * 1 - player hasn't changed dimensions, but has teleported;
     * 2 - player can't change dimensions, but has teleported;
     * 3 - player unable to change dimensions and can't teleport;
     * */
    public static int teleportToSpawnPoint(PlayerEntity playerEntity, World world, boolean canTraverseDimensions) {
        ServerWorld serverWorld = (ServerWorld) world;
        ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
        BlockPos spawnPoint = player.getSpawnPointPosition();
        RegistryKey<World> spawnDimension = player.getSpawnPointDimension();
        ServerWorld destination = ((ServerWorld) world).getServer().getWorld(spawnDimension);

        if (canTraverseDimensions) {
            if(TeleportUtil.changeDimension(playerEntity, destination, spawnPoint)) {
                TeleportUtil.teleportToPos(playerEntity, spawnPoint);
                return 0;
            } else {
                TeleportUtil.teleportToPos(playerEntity, spawnPoint);
                return 1;
            }
        }
        else {
            if (player.getSpawnPointDimension().equals(serverWorld.getRegistryKey())) {
                TeleportUtil.teleportToPos(playerEntity, spawnPoint);
                return 2;
            } else {
                return 3;
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
