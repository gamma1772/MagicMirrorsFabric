package com.gamma1772.magicmirrors.client;

import com.gamma1772.magicmirrors.client.particle.TeleportParticle;
import com.gamma1772.magicmirrors.common.init.ModContent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class MagicMirrorsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ParticleFactoryRegistry.getInstance().register(ModContent.MIRROR_PARTICLE, TeleportParticle.Factory::new);
    }
}
