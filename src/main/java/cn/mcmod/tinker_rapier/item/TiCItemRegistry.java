package cn.mcmod.tinker_rapier.item;

import cn.mcmod.tinker_rapier.RapierMain;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TiCItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RapierMain.MODID);

    public static final RegistryObject<RapierTiC> RAPIER = ITEMS.register("rapier_tic", RapierTiC::new);
}
