package cn.mcmod.simple_rapier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.mcmod.simple_rapier.item.ItemRegistry;
import cn.mcmod.simple_rapier.tic.TiCItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RapierMain.MODID)
public class RapierMain {
    public static final String MODID = "simple_rapier";
    private static final Logger LOGGER = LogManager.getLogger();
    public static final ItemGroup WEAPON_GROUP = new ItemGroup(MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.GOLDEN_RAPIER.get());
        }
    };

    public RapierMain() {
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        if(ModList.get().isLoaded("tconstruct"))
            TiCItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}
