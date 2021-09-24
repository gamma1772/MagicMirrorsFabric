package com.gamma1772.magicmirrors.client;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "magicmirrors")
public class MagicMirrorsConfig implements ConfigData {

    @ConfigEntry.Gui.Excluded
    public static MagicMirrorsConfig INSTANCE;

    public static void init()
    {
        AutoConfig.register(MagicMirrorsConfig.class, JanksonConfigSerializer::new);
        INSTANCE = AutoConfig.getConfigHolder(MagicMirrorsConfig.class).getConfig();
    }

    @Comment("Red color of teleport particle (0 - 255)")
    public int colorRed = 0;

    @Comment("Green color of teleport particle (0 - 255)")
    public int colorGreen = 222;

    @Comment("Blue color of teleport particle (0 - 255)")
    public int colorBlue = 255;


}
