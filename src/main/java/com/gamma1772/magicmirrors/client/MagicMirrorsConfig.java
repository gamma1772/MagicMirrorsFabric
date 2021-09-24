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

    //General config

    public int mirrorCooldown = 200;

    @Comment("Enabling options from debug menu. Without this enabled, debug options will be active")
    public boolean enableDebugging = false;

    //Particle config
    @ConfigEntry.Category("particles")
    @Comment("Red color of teleport particle (0 - 255)")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int colorRed = 0;

    @ConfigEntry.Category("particles")
    @Comment("Green color of teleport particle (0 - 255)")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int colorGreen = 222;

    @ConfigEntry.Category("particles")
    @Comment("Blue color of teleport particle (0 - 255)")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int colorBlue = 255;

    @ConfigEntry.Category("particles")
    @ConfigEntry.Gui.PrefixText
    @Comment("Red color of teleport particle for dimensional teleport (0 - 255)")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int colorRedDimensional = 0;

    @ConfigEntry.Category("particles")
    @Comment("Green color of teleport particle for dimensional teleport (0 - 255)")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int colorGreenDimensional = 222;

    @ConfigEntry.Category("particles")
    @Comment("Blue color of teleport particle for dimensional teleport (0 - 255)")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int colorBlueDimensional = 255;

    @ConfigEntry.Category("particles")
    @Comment("Enable or disable particles on mirror usage. Particles are still registered and can be used with /particle command.")
    public boolean enableParticles = true;

    @ConfigEntry.Category("particles")
    @Comment("How much particles should spawn during mirror usage tick. A large value can cause low FPS and possibly TPS")
    public int particleCount = 30;


    //Teleport config
    @ConfigEntry.Category("teleportation")
    @Comment("How high above the block should the player teleport? (DANGEROUS)/nRecommended values between 0.0 and 1.5, values higher than 3 can cause fall damage.")
    public double heightAdjustment = 0.6;

    @ConfigEntry.Category("teleportation")
    @Comment("Enable or disable dimensional teleports regardless of the used item")
    public boolean allowDimensonalTravel = true;

    //Debug config
}
