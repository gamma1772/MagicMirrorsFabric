package com.gamma1772.magicmirrors.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class TeleportParticle extends SpriteBillboardParticle {
    private static final Random RAND = new Random();
    protected final SpriteProvider provider;

    private TeleportParticle(SpriteProvider provider, ClientWorld clientWorld, double x, double y, double z, double speedX, double speedY, double speedZ) {
        super(clientWorld, x, y, z, 0.5D - RAND.nextDouble(), speedY, 0.5D - RAND.nextDouble());
        this.provider = provider;
        this.velocityY *= 0.15D;
        if(speedX == 0.0D && speedZ == 0.0D) {
            this.velocityX *= 0.5D;
            this.velocityZ *= 0.5D;
        }
        this.scale *= 0.3F;
        this.maxAge = (int)(7.5D / (Math.random() * 0.2D + 0.1D)); //was 7.5 0.4 and 0.2
        this.collidesWithWorld = false;
        this.setSpriteForAge(provider);
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if(this.age++ >= this.maxAge){
            this.markDead();
        }else{
            this.setSpriteForAge(this.provider);
            this.velocityY += 0.04D;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            if(this.y == this.prevPosY){
                this.velocityX *= 0.5D;
                this.velocityZ *= 0.5D;
            }
            this.velocityX *= 0.5D;
            this.velocityY *= 0.5D;
            this.velocityZ *= 0.5D;
            if (this.onGround) {
                this.velocityX *= 0.5D;
                this.velocityZ *= 0.5D;
            }
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getBrightness(float tint) {
        float f = ((float) this.age + tint) / (float) this.maxAge;
        f = MathHelper.clamp(f, 0f, 1f);
        int i = super.getBrightness(tint);
        int j = i & 255;
        int k = i >> 16 & 255;
        j = j + (int) (f * 15f * 16f);
        if (j > 240) {
            j = 240;
        }
        return j | k << 16;    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double z, double y, double speedX, double speedY, double speedZ) {
            TeleportParticle particle = new TeleportParticle(this.spriteProvider ,clientWorld, x, y, z, speedX, speedY, speedZ);

            return particle;
        }
    }
}