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

package com.gamma1772.magicmirrors.common.content.item;

import com.gamma1772.magicmirrors.api.util.teleport.Teleporter;
import com.gamma1772.magicmirrors.client.MagicMirrorsConfig;
import com.gamma1772.magicmirrors.common.init.ModContent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class MagicMirrorItem extends Item {
    private final boolean canTraverseDimensions;
    //private final int cooldown;
    //private final int particleCount = MagicMirrorsConfig.INSTANCE.particleCount;

    public MagicMirrorItem(Settings settings, boolean canTraverseDimensions) {
        super(settings);
        this.canTraverseDimensions = canTraverseDimensions;
        //this.cooldown = 200;
    }

    public MagicMirrorItem(Settings settings, boolean canTraverseDimensions, int cooldown) {
        super(settings);
        this.canTraverseDimensions = canTraverseDimensions;
        //this.cooldown = cooldown;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, stack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (MagicMirrorsConfig.INSTANCE.enableParticles) {
            Random rand = user.world.random;

            if (canTraverseDimensions) {
                for (int i = 0; i < MagicMirrorsConfig.INSTANCE.particleCount; i++) {
                    user.world.addParticle((ParticleEffect) ModContent.DIMENSIONAL_MIRROR_PARTICLE,
                            user.prevX + (rand.nextBoolean() ? -0.25 : 0.25) * Math.pow(rand.nextFloat(), 2) * 2,
                            user.prevY + rand.nextFloat() * 3 - 2,
                            user.prevZ + (rand.nextBoolean() ? -0.25 : 0.25) * Math.pow(rand.nextFloat(), 2) * 2,
                            -2, 0.2D, -2);
                }
            } else {
                for (int i = 0; i < MagicMirrorsConfig.INSTANCE.particleCount; i++) {
                    user.world.addParticle((ParticleEffect) ModContent.MIRROR_PARTICLE,
                            user.prevX + (rand.nextBoolean() ? -0.25 : 0.25) * Math.pow(rand.nextFloat(), 2) * 2,
                            user.prevY + rand.nextFloat() * 3 - 2,
                            user.prevZ + (rand.nextBoolean() ? -0.25 : 0.25) * Math.pow(rand.nextFloat(), 2) * 2,
                            -2, 0.2D, -2);
                }
            }
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient) { return super.finishUsing(stack, world, user); }

        ServerPlayerEntity player = (ServerPlayerEntity) user; //casting serverPlayer to entity
        BlockPos spawnPos = player.getSpawnPointPosition(); //Gets user's respawn position
        ((ServerPlayerEntity) user).getItemCooldownManager().set(this, MagicMirrorsConfig.INSTANCE.mirrorCooldown * 20);

        if (spawnPos != null) {
            switch (Teleporter.teleportToSpawnPoint(player, world, canTraverseDimensions)) {
                case 0, 1, 2 -> {
                    player.sendMessage(new TranslatableText("info.magicmirrors.teleport"), true);
                    if (!world.isClient) {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), ModContent.MIRROR_WARP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        world.playSound(null, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), ModContent.MIRROR_WARP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    }
                }
                case 3 -> {
                    player.sendMessage(new TranslatableText("info.magicmirrors.power"), true);
                    if (!world.isClient)
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), ModContent.MIRROR_FAIL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }
                default -> player.sendMessage(new TranslatableText("info.magicmirrors.undefined"), true);
            }
        }
        else {
            player.sendMessage(new TranslatableText("info.magicmirrors.spawn_not_found"), true);
            if (!world.isClient)
                world.playSound(null, player.getX(), player.getY(), player.getZ(), ModContent.MIRROR_FAIL, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
        return stack;
    }

    /**Usage animation*/
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    /**
     * Duration of use action
     * */
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 30;
    }


    @Override
    public Rarity getRarity(ItemStack stack) {
        if (canTraverseDimensions) {
            return Rarity.EPIC;
        }
        else {
            return Rarity.RARE;
        }
    }

    /**
     * Whether the item has enchantment glint or not
     * */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    /**
     * Can the item be used to teleport from ex: Nether if the spawn point is in another dimension
     * */
    public boolean getCanTraverseDimensions() {
        return canTraverseDimensions;
    }
}
