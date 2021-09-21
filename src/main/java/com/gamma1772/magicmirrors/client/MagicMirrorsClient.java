package com.gamma1772.magicmirrors.client;

import com.gamma1772.magicmirrors.MagicMirrors;
import com.gamma1772.magicmirrors.client.particle.TeleportParticle;
import com.gamma1772.magicmirrors.client.registry.ClientModRegistry;
import com.gamma1772.magicmirrors.common.init.ModContent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

public class MagicMirrorsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        /*ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((((atlasTexture, registry) ->
                registry.register(new Identifier(MagicMirrors.MODID, "particle/mirror_particle")))));*/

        ParticleFactoryRegistry.getInstance().register(ModContent.MIRROR_PARTICLE, TeleportParticle.Factory::new);
    }
}
