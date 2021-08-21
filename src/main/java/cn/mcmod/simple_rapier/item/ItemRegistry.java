package cn.mcmod.simple_rapier.item;

import cn.mcmod.simple_rapier.RapierMain;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RapierMain.MODID);

    public static final RegistryObject<Item> WOODEN_RAPIER = ITEMS.register("wooden_rapier",
            () -> new RapierItem(ItemTier.WOOD, new Item.Properties().tab(RapierMain.WEAPON_GROUP).stacksTo(1)));
    public static final RegistryObject<Item> STONE_RAPIER = ITEMS.register("stone_rapier",
            () -> new RapierItem(ItemTier.STONE, new Item.Properties().tab(RapierMain.WEAPON_GROUP).stacksTo(1)));
    public static final RegistryObject<Item> IRON_RAPIER = ITEMS.register("iron_rapier",
            () -> new RapierItem(ItemTier.IRON, new Item.Properties().tab(RapierMain.WEAPON_GROUP).stacksTo(1)));
    public static final RegistryObject<Item> GOLDEN_RAPIER = ITEMS.register("golden_rapier",
            () -> new RapierItem(ItemTier.GOLD, new Item.Properties().tab(RapierMain.WEAPON_GROUP).stacksTo(1)));
    public static final RegistryObject<Item> DIAMOND_RAPIER = ITEMS.register("diamond_rapier",
            () -> new RapierItem(ItemTier.DIAMOND, new Item.Properties().tab(RapierMain.WEAPON_GROUP).stacksTo(1)));
    public static final RegistryObject<Item> NETHERITE_RAPIER = ITEMS.register("netherite_rapier",
            () -> new RapierItem(ItemTier.NETHERITE, new Item.Properties().tab(RapierMain.WEAPON_GROUP).stacksTo(1)));
}
