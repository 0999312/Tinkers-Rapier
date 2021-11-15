package cn.mcmod.tinker_rapier.data;

import cn.mcmod.tinker_rapier.RapierConfig;
import cn.mcmod.tinker_rapier.item.RapierTiC;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class RapierDefinitionDataProvider extends AbstractToolDefinitionDataProvider {

    public RapierDefinitionDataProvider(DataGenerator generator, String modId) {
        super(generator, modId);
    }

    @Override
    public String getName() {
        return "Tinker's Rapier Tool Definition";
    }

    @Override
    protected void addToolDefinitions() {
        define(RapierTiC.RAPIER)
            .part(TinkerToolParts.smallBlade)
            .part(TinkerToolParts.largePlate)
            .part(TinkerToolParts.toughHandle)
            .part(TinkerToolParts.toolHandle)
            .stat(ToolStats.ATTACK_DAMAGE, 1.25f)
            .stat(ToolStats.ATTACK_SPEED, 3.0f)
            .stat(ToolStats.MINING_SPEED, 0.75f)
            .stat(ToolStats.DURABILITY, RapierConfig.RAPIER_DURABILITY.get().floatValue())
            .largeToolStartingSlots()
            .trait(TinkerModifiers.silkyShears);
    }

}
