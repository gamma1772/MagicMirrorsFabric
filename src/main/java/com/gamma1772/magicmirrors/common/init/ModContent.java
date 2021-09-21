package com.gamma1772.magicmirrors.common.init;

import com.gamma1772.magicmirrors.common.content.item.MagicMirrorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModContent {
    private static final FabricItemSettings MIRROR_SETTINGS = new FabricItemSettings().maxCount(1).group(ItemGroup.TOOLS).rarity(Rarity.RARE);
    private static final FabricItemSettings DIMENSIONAL_MIRROR_SETTINGS = new FabricItemSettings().maxCount(1).group(ItemGroup.TOOLS).fireproof().rarity(Rarity.EPIC);
    private static final FabricItemSettings MIRROR_MATERIALS_SETTINGS = new FabricItemSettings().maxCount(16).group(ItemGroup.MATERIALS).rarity(Rarity.RARE);

    public static final Item MAGIC_MIRROR = new MagicMirrorItem(MIRROR_SETTINGS, false);
    public static final Item DIMENSIONAL_MIRROR = new MagicMirrorItem(DIMENSIONAL_MIRROR_SETTINGS, true);

    public static final Item ENCHANTED_MIRROR = new Item(MIRROR_MATERIALS_SETTINGS);
    public static final Item MIRROR_FRAME = new Item(MIRROR_MATERIALS_SETTINGS);
    public static final Item DIMENSION_CRYSTAL = new Item(MIRROR_MATERIALS_SETTINGS);

    public static SoundEvent MIRROR_WARP = new SoundEvent(new Identifier("magicmirrors:mirror_warp"));
    public static SoundEvent MIRROR_FAIL = new SoundEvent(new Identifier("magicmirrors:mirror_fail"));

    public static final ParticleType<DefaultParticleType> MIRROR_PARTICLE = FabricParticleTypes.simple();
}
