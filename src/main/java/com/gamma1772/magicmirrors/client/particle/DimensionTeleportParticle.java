package com.gamma1772.magicmirrors.client.particle;

import com.gamma1772.magicmirrors.client.MagicMirrorsConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class DimensionTeleportParticle extends TeleportParticle {

    protected DimensionTeleportParticle(ClientWorld clientWorld, double posX, double posY, double posZ, double speedX, double speedY, double speedZ) {
        super(clientWorld, posX, posY, posZ, speedX, speedY, speedZ);

        this.colorR = MagicMirrorsConfig.INSTANCE.colorRedDimensional;
        this.colorG = MagicMirrorsConfig.INSTANCE.colorGreenDimensional;
        this.colorB = MagicMirrorsConfig.INSTANCE.colorBlueDimensional;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            DimensionTeleportParticle particle = new DimensionTeleportParticle(clientWorld, d, e, f, g, h, i);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}
