package cn.mcmod.tinker_rapier;

import net.minecraftforge.common.ForgeConfigSpec;

public class RapierConfig {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.DoubleValue RAPIER_ATTACK_BOUNS;
    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("General settings").push("general");
        RAPIER_ATTACK_BOUNS = COMMON_BUILDER.comment("the sting attack multiplier of Rapiers")
                .defineInRange("rapier_attack_bouns", 1D, 0.01D, 10D);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
