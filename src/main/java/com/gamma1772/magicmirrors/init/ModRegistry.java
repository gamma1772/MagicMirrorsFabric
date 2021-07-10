package com.gamma1772.magicmirrors.init;

import com.gamma1772.magicmirrors.MagicMirrors;
import com.gamma1772.magicmirrors.api.registry.RegisterItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
