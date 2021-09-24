/*MIT License

Copyright (c) 2021 Marko Dujović

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/

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

    @Comment("Enable or disable particles on mirror usage")
    public boolean enableParticles = true;
}
