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
        Registry.register(Registry.ITEM, new Identifier(MagicMirrors.MODID, "magic_mirror"), ModContent.MAGIC_MIRROR);
        Registry.register(Registry.ITEM, new Identifier(MagicMirrors.MODID, "dimensional_mirror"), ModContent.DIMENSIONAL_MIRROR);

        Registry.register(Registry.ITEM, new Identifier(MagicMirrors.MODID, "enchanted_mirror"), ModContent.ENCHANTED_MIRROR);
        Registry.register(Registry.ITEM, new Identifier(MagicMirrors.MODID, "mirror_frame"), ModContent.MIRROR_FRAME);
        Registry.register(Registry.ITEM, new Identifier(MagicMirrors.MODID, "dimension_crystal"), ModContent.DIMENSION_CRYSTAL);
    }

    private static void registerSounds() {
        Registry.register(Registry.SOUND_EVENT, new Identifier(MagicMirrors.MODID, "mirror_warp"), ModContent.MIRROR_WARP);
        Registry.register(Registry.SOUND_EVENT, new Identifier(MagicMirrors.MODID, "mirror_fail"), ModContent.MIRROR_FAIL);
    }

    private static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MagicMirrors.MODID, "mirror_particle"), ModContent.MIRROR_PARTICLE);
    }
}
