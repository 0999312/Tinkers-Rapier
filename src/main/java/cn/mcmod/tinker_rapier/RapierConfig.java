package cn.mcmod.tinker_rapier;

import net.minecraftforge.common.ForgeConfigSpec;

public class RapierConfig {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.BooleanValue CLASSIC_ATK;
    public static ForgeConfigSpec.DoubleValue RAPIER_DURABILITY;
    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("General settings").push("general");
        CLASSIC_ATK = COMMON_BUILDER.comment("Is the rapier attack like classic TiC (Armor-piercing)")
                .define("classic_attack", false);
        RAPIER_DURABILITY = COMMON_BUILDER.comment("the durability multiplier of Rapier")
                .defineInRange("rapier_durability", 0.75D, 0.1D, 2D);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
