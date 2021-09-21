package com.gamma1772.magicmirrors.client.registry;

import com.gamma1772.magicmirrors.MagicMirrors;
import com.gamma1772.magicmirrors.client.particle.TeleportParticle;
import com.gamma1772.magicmirrors.common.init.ModContent;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

public class ClientModRegistry {
    public static void clientSetup() {
        registerParticles();
    }

    public static void registerParticles() {



    }
}
