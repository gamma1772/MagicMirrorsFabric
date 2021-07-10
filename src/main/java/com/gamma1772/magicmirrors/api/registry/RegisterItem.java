package com.gamma1772.magicmirrors.api.registry;

import com.gamma1772.magicmirrors.MagicMirrors;
import com.gamma1772.magicmirrors.content.item.MagicMirrorItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterItem {

    public static void registerMagicMirror(String id, Item item) {
        Identifier identifier = new Identifier(MagicMirrors.MODID, id);
        Registry.register(Registry.ITEM, identifier, item);
    }
}
