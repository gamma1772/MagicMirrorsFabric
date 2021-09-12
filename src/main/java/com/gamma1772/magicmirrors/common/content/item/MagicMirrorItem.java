package com.gamma1772.magicmirrors.common.content.item;

import com.gamma1772.magicmirrors.api.util.teleport.Teleporter;
import com.gamma1772.magicmirrors.common.init.ModContent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

import java.util.Random;

public class MagicMirrorItem extends Item {
    private final boolean canTraverseDimensions;
    private final int cooldown;
    public MagicMirrorItem(Settings settings, boolean canTraverseDimensions) {
        super(settings);
        this.canTraverseDimensions = canTraverseDimensions;
        this.cooldown = 200;
    }

    public MagicMirrorItem(Settings settings, boolean canTraverseDimensions, int cooldown) {
        super(settings);
        this.canTraverseDimensions = canTraverseDimensions;
        this.cooldown = cooldown;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, stack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        Random rand = user.world.random;
        for (int i = 0; i < 25; i++) {
            user.world.addParticle(ModContent.MIRROR_PARTICLE,
                    user.getX() + (rand.nextBoolean() ? -0.5 : 0.5) * Math.pow(rand.nextFloat(), 2) * 2,
                    user.getY() + rand.nextFloat() * 3 - 2,
                    user.getZ() + (rand.nextBoolean() ? -0.5 : 0.5) * Math.pow(rand.nextFloat(), 2) * 2,
                    -2, -0.100D, -2);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient) { return super.finishUsing(stack, world, user); }

        ServerPlayerEntity player = (ServerPlayerEntity) user; //casting serverPlayer to entity
        BlockPos spawnPos = player.getSpawnPointPosition(); //Gets user's respawn position
        RegistryKey<World> spawnWorldKey = player.getSpawnPointDimension(); //Gets the respawn dimension
        BlockPos currentPos = player.getBlockPos();
        ((ServerPlayerEntity) user).getItemCooldownManager().set(this, cooldown);

        if (spawnPos != null) {
            Teleporter.teleportToSpawnPoint(player, world, canTraverseDimensions);
            if (!world.isClient) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), ModContent.MIRROR_WARP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                world.playSound(null, spawnPos, ModContent.MIRROR_WARP, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
        }
        else {
            player.sendMessage(new TranslatableText("info.magicmirrors.spawn_not_found"), true);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), ModContent.MIRROR_FAIL, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
        return stack;
    }

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
