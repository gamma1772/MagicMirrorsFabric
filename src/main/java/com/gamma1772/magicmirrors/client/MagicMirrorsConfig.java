package com.gamma1772.magicmirrors.client;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

@Config(name = "magicmirrors")
public class MagicMirrorsConfig implements ConfigData {
    @ConfigEntry.Gui.Excluded
    public static MagicMirrorsConfig INSTANCE;

    public static void init()
    {
        AutoConfig.register(MagicMirrorsConfig.class, JanksonConfigSerializer::new);
        INSTANCE = AutoConfig.getConfigHolder(MagicMirrorsConfig.class).getConfig();
    }
}
