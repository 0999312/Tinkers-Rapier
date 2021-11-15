package cn.mcmod.tinker_rapier.data;

import java.util.function.Consumer;

import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import slimeknights.tconstruct.common.data.BaseRecipeProvider;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;

public class RapierRecipeProvider extends BaseRecipeProvider implements IToolRecipeHelper {

    public RapierRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "tools/building/";
        toolBuilding(consumer, TiCItemRegistry.RAPIER, folder);
    }

    @Override
    public String getName() {
        return "Tinker's Rapier Tool Recipe";
    }

}
