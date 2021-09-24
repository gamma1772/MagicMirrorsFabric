/*MIT License

Copyright (c) 2021 Marko Dujović

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/

package com.gamma1772.magicmirrors.common.util;

import com.gamma1772.magicmirrors.client.MagicMirrorsConfig;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.impl.dimension.FabricDimensionInternals;
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

    public static boolean changeDimensionsAndTeleport(PlayerEntity playerEntity, World destinationWorld, BlockPos spawnPoint) {
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
            return false;
        }
    }

    public static boolean changeDimensions(PlayerEntity playerEntity, World destinationWorld, BlockPos spawnPoint) {
        ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
        ServerWorld destination = (ServerWorld) destinationWorld;

        Vec3d spawnVector = new Vec3d(spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ());

        TeleportTarget teleportTarget = new TeleportTarget(spawnVector, spawnVector, player.getYaw(), player.getPitch());

        if (player.getServerWorld().getRegistryKey() != destination.getRegistryKey()) {
            player.getServerWorld().getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, new ChunkPos(new BlockPos(spawnPoint)), 1, player.getId());

            FabricDimensionInternals.changeDimension(player, destination, teleportTarget);
            return true;
        }
        else {
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

        player.requestTeleport(pos.getX() + 0.5F, pos.getY() + MagicMirrorsConfig.INSTANCE.heightAdjustment, pos.getZ() + 0.5F); //0.5F is used to center the player on a block, instead of teleporting to the edge of the block
    }

    public static void teleportToPos(LivingEntity livingEntity, BlockPos pos) {
        ServerWorld serverWorld = (ServerWorld) livingEntity.getEntityWorld();
        ChunkPos ticketPos = new ChunkPos(new BlockPos(pos));

        serverWorld.getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, new ChunkPos(new BlockPos(pos)), 1, livingEntity.getId());

        livingEntity.requestTeleport(pos.getX(), pos.getY(), pos.getZ());
    }
}
