package com.gamma1772.magicmirrors.common.init;

import com.gamma1772.magicmirrors.MagicMirrors;
import com.gamma1772.magicmirrors.client.particle.TeleportParticle;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRegistry {
    public static void setup() {
        registerSounds();
        registerParticles();
        registerItems();
    }

    private static void registerItems() {
        //Registry.register(Registry.ITEM, new Identifier(MagicMirrors.MODID, "magic_mirror"), ModContent.MAGIC_MIRROR);
        Registry.register(Registry.ITEM, new Identifier(MagicMirrors.MODID, "magic_mirror"), ModContent.MAGIC_MIRROR);
        Registry.register(Registry.ITEM, new Identifier(MagicMirrors.MODID, "dimensional_mirror"), ModContent.DIMENSIONAL_MIRROR);
    }

    private static void registerSounds() {
        Registry.register(Registry.SOUND_EVENT, new Identifier(MagicMirrors.MODID, "mirror_warp"), ModContent.MIRROR_WARP);
        Registry.register(Registry.SOUND_EVENT, new Identifier(MagicMirrors.MODID, "mirror_fail"), ModContent.MIRROR_FAIL);
    }

    private static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MagicMirrors.MODID, "mirror_particle"), ModContent.MIRROR_PARTICLE);
    }
}
