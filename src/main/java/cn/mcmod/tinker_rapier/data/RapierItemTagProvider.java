package cn.mcmod.tinker_rapier.data;

import org.jetbrains.annotations.Nullable;

import cn.mcmod.tinker_rapier.RapierMain;
import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import slimeknights.tconstruct.common.TinkerTags;

public class RapierItemTagProvider extends ItemTagsProvider{

    public RapierItemTagProvider(DataGenerator gen, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen, new ForgeBlockTagsProvider(gen, existingFileHelper), RapierMain.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TinkerTags.Items.TOOL_PARTS).add(TiCItemRegistry.SLENDER_BLADE.get());
        
        tag(TinkerTags.Items.MULTIPART_TOOL).add(TiCItemRegistry.ESTOC.get(), TiCItemRegistry.RAPIER.get());
        tag(TinkerTags.Items.DURABILITY).add(TiCItemRegistry.ESTOC.get(), TiCItemRegistry.RAPIER.get());
        tag(TinkerTags.Items.HARVEST).add(TiCItemRegistry.ESTOC.get(), TiCItemRegistry.RAPIER.get());
        tag(TinkerTags.Items.MELEE_PRIMARY).add(TiCItemRegistry.ESTOC.get(), TiCItemRegistry.RAPIER.get());
        tag(TinkerTags.Items.ONE_HANDED).add(TiCItemRegistry.ESTOC.get(), TiCItemRegistry.RAPIER.get());
    }
}
