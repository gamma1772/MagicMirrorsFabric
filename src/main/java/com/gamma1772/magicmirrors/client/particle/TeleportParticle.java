package com.gamma1772.magicmirrors.client.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.world.ClientWorld;

public class TeleportParticle extends Particle {

    protected final ParticleTextureSheet textureSheet = new ParticleTextureSheet() {
        @Override
        public void begin(BufferBuilder bufferBuilder, TextureManager textureManager) {
            RenderSystem.enableBlend();
            RenderSystem.disableTexture();
        }

        @Override
        public void draw(Tessellator tessellator) {
            tessellator.draw();
            RenderSystem.disableBlend();
            RenderSystem.enableTexture();
        }
    };

    public TeleportParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if(this.age++ >= this.maxAge){
            this.markDead();
        }else{
            //this.selectSpriteWithAge(this.textureSheet);
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
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {

    }

    @Override
    public ParticleTextureSheet getType() {
        return textureSheet;
    }
}
