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

package com.gamma1772.magicmirrors.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class TeleportParticle extends SpriteBillboardParticle {
    private final double startX;
    private final double startY;
    private final double startZ;

    protected TeleportParticle(ClientWorld clientWorld, double posX, double posY, double posZ, double speedX, double speedY, double speedZ) {
        super(clientWorld, posX, posY, posZ, speedX, speedY, speedZ);
        this.velocityX = speedX;
        this.velocityY = speedY;
        this.velocityZ = speedZ;
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.startX = this.x;
        this.startY = this.y;
        this.startZ = this.z;
        this.scale = 0.1F * (this.random.nextFloat() * 0.2F + 0.5F);
        //float j = this.random.nextFloat() * 0.6F + 0.4F;
        /*Color values are from 0 to 1. To convert from integer 0-255 values to float 0-1, division with 255 is required.*/
        this.colorRed = 0 / 255F;
        this.colorGreen = 222 / 255F;
        this.colorBlue = 255 / 255F;
        this.maxAge = (int)(Math.random() * 10.0D) + 40;
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    public float getSize(float tickDelta) {
        float f = ((float)this.age + tickDelta) / (float)this.maxAge;
        f = 1.0F - f;
        f *= f;
        f = 1.0F - f;
        return this.scale * f;
    }

    public int getBrightness(float tint) {
        int i = super.getBrightness(tint);
        float f = (float)this.age / (float)this.maxAge;
        f *= f;
        f *= f;
        int j = i & 255;
        int k = i >> 16 & 255;
        k += (int)(f * 15.0F * 16.0F);
        if (k > 240) {
            k = 240;
        }

        return j | k << 16;
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge) {
            this.markDead();
        } else {
            float f = (float)this.age / (float)this.maxAge;
            this.velocityY += 0.07F;

            if (this.y == this.prevPosY) {
                this.velocityX *= 0.5F;
                this.velocityZ *= 0.5F;
            }

            float g = f;
            f = -f + f * f * 2.0F;
            f = 1.0F - f;

            this.velocityX *= 0.5D * f;
            this.velocityY *= 0.75D * f;
            this.velocityZ *= 0.5D * f;

            this.x = this.startX + this.velocityX * (double)f;
            this.y = this.startY + this.velocityY * (double)f + (double)(1.0F - g);
            this.z = this.startZ + this.velocityZ * (double)f;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            TeleportParticle particle = new TeleportParticle(clientWorld, d, e, f, g, h, i);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}