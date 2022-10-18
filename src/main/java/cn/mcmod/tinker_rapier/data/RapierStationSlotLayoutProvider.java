package cn.mcmod.tinker_rapier.data;

import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class RapierStationSlotLayoutProvider extends AbstractStationSlotLayoutProvider {

    public RapierStationSlotLayoutProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Tinker's Rapier Tinker Station Slot Layouts";
    }

    @Override
    protected void addLayouts() {
        defineModifiable(TiCItemRegistry.RAPIER.get())
        .sortIndex(SORT_WEAPON)
        .addInputItem(TiCItemRegistry.SLENDER_BLADE.get(), 10, 22)
        .addInputItem(TinkerToolParts.largePlate, 33, 44)
        .addInputItem(TinkerToolParts.toughHandle, 33, 64)
        .addInputItem(TinkerToolParts.toolHandle, 53, 64)
        .build();
        
        defineModifiable(TiCItemRegistry.ESTOC.get())
        .sortIndex(SORT_WEAPON)
        .addInputItem(TiCItemRegistry.SLENDER_BLADE.get(), 15, 26)
        .addInputItem(TinkerToolParts.toughHandle, 33, 44)
        .addInputItem(TinkerToolParts.toolHandle, 51, 62)
        .build();
    }

}
