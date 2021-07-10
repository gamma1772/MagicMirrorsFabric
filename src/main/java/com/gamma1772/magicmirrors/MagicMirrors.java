package com.gamma1772.magicmirrors;

import com.gamma1772.magicmirrors.init.ModRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

public class MagicMirrors implements ModInitializer {

    public static final String MODID = "magicmirrors";
    @Override
    public void onInitialize() {
        ModRegistry.setup();
    }
}
