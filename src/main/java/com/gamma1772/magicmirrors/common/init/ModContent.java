package com.gamma1772.magicmirrors.common.init;

import com.gamma1772.magicmirrors.common.content.item.MagicMirrorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Rarity;

public class ModContent {
    private static final FabricItemSettings MIRROR_SETTINGS = new FabricItemSettings().maxCount(1).group(ItemGroup.TOOLS).rarity(Rarity.RARE);
    private static final FabricItemSettings DIMENSIONAL_MIRROR_SETTINGS = new FabricItemSettings().maxCount(1).group(ItemGroup.TOOLS).fireproof().rarity(Rarity.EPIC);

    public static final Item MAGIC_MIRROR = new MagicMirrorItem(MIRROR_SETTINGS, false);
    public static final Item DIMENSIONAL_MIRROR = new MagicMirrorItem(DIMENSIONAL_MIRROR_SETTINGS, true);
}
