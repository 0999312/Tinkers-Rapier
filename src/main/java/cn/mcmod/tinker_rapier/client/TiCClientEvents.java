package cn.mcmod.tinker_rapier.client;

import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.client.color.item.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.client.model.tools.ToolModel;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TiCClientEvents {
    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
            final ItemColors colors = event.getItemColors();
            ToolModel.registerItemColors(colors, TiCItemRegistry.RAPIER);
    }
}
