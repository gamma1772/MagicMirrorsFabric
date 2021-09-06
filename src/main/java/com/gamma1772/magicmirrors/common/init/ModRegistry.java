package com.gamma1772.magicmirrors.common.init;

import com.gamma1772.magicmirrors.api.registry.RegisterItem;

public class ModRegistry {
    public static void setup() {
        registerItems();
    }

    public static void registerItems() {
        //Registry.register(Registry.ITEM, new Identifier(MagicMirrors.MODID, "magic_mirror"), ModContent.MAGIC_MIRROR);
        RegisterItem.registerMagicMirror("magic_mirror", ModContent.MAGIC_MIRROR);
        RegisterItem.registerMagicMirror("dimensional_mirror", ModContent.DIMENSIONAL_MIRROR);
    }
}
