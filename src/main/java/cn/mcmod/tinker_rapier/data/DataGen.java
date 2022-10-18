package cn.mcmod.tinker_rapier.data;

import cn.mcmod.tinker_rapier.RapierMain;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        
        if(event.includeServer()) {
            generator.addProvider(new RapierRecipeProvider(generator));
            generator.addProvider(new RapierDefinitionDataProvider(generator, RapierMain.MODID));
            generator.addProvider(new RapierStationSlotLayoutProvider(generator));
            generator.addProvider(new RapierItemTagProvider(generator, existingFileHelper));
        }
        if (event.includeClient()) {
            TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
            RapierTextureProvider rapierSprites = new RapierTextureProvider(RapierMain.MODID);
            generator.addProvider(new MaterialPartTextureGenerator(generator, existingFileHelper, rapierSprites, materialSprites));
        }
    }
}
