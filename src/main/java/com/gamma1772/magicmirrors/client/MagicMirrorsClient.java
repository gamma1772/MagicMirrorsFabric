package com.gamma1772.magicmirrors.client;

import com.gamma1772.magicmirrors.client.registry.ClientModRegistry;
import net.fabricmc.api.ClientModInitializer;

public class MagicMirrorsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientModRegistry.clientSetup();
    }
}
