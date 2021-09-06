package com.gamma1772.magicmirrors;

import com.gamma1772.magicmirrors.common.init.ModRegistry;
import net.fabricmc.api.ModInitializer;

public class MagicMirrors implements ModInitializer {

    public static final String MODID = "magicmirrors";
    @Override
    public void onInitialize() {
        ModRegistry.setup();
    }
}
