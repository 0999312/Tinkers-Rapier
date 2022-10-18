package cn.mcmod.tinker_rapier.item;

import cn.mcmod.tinker_rapier.RapierMain;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class TiCItemRegistry {
    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(RapierMain.MODID);

    public static final ItemObject<ToolPartItem> SLENDER_BLADE = ITEMS.register("slender_blade",
            () -> new ToolPartItem(RapierMain.defaultItemProperties(), HeadMaterialStats.ID));

    public static final CastItemObject SLENDER_BLADE_CAST = ITEMS.registerCast("slender_blade",
            new Item.Properties().tab(TinkerSmeltery.TAB_SMELTERY));

    public static final ItemObject<RapierTiC> RAPIER = ITEMS.register("rapier_tic", RapierTiC::new);
    public static final ItemObject<EstocTiC> ESTOC = ITEMS.register("estoc_tic", EstocTiC::new);

}
