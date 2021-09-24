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

package com.gamma1772.magicmirrors.client;

import com.gamma1772.magicmirrors.client.particle.DimensionTeleportParticle;
import com.gamma1772.magicmirrors.client.particle.TeleportParticle;
import com.gamma1772.magicmirrors.common.init.ModContent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class MagicMirrorsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        MagicMirrorsConfig.init();

        ParticleFactoryRegistry.getInstance().register(ModContent.MIRROR_PARTICLE, TeleportParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModContent.DIMENSIONAL_MIRROR_PARTICLE, DimensionTeleportParticle.Factory::new);
    }
}
