package cn.mcmod.tinker_rapier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RapierMain.MODID)
public class RapierMain {
    public static final String MODID = "tinker_rapier";
    private static final Logger LOGGER = LogManager.getLogger();
    public static final ItemGroup WEAPON_GROUP = new ItemGroup(MODID) {
        @Override
        public ItemStack makeIcon() {
            return TiCItemRegistry.RAPIER.get().getRenderTool();
        }
    };

    public RapierMain() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RapierConfig.COMMON_CONFIG);
//        ModLoadingContext.get().
        TiCItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}
