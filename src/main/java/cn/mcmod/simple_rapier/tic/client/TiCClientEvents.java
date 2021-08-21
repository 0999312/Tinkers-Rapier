package cn.mcmod.simple_rapier.tic.client;

import cn.mcmod.simple_rapier.tic.TiCItemRegistry;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.tools.ToolClientEvents;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TiCClientEvents {
    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
        if(ModList.get().isLoaded("tconstruct")) {
            final ItemColors colors = event.getItemColors();
            ToolClientEvents.registerToolItemColors(colors, TiCItemRegistry.RAPIER);
        }
    }
}
