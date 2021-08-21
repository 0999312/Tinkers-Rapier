package cn.mcmod.simple_rapier.data;

import java.util.function.Consumer;

import cn.mcmod.simple_rapier.item.ItemRegistry;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeRecipeProvider;

public class SwordRecipes extends ForgeRecipeProvider {

    public SwordRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        registerRapier(consumer, ItemRegistry.WOODEN_RAPIER.get(), Ingredient.of(ItemTags.PLANKS),
                Ingredient.of(Items.WOODEN_SWORD));
        registerRapier(consumer, ItemRegistry.STONE_RAPIER.get(), Ingredient.of(Tags.Items.COBBLESTONE),
                Ingredient.of(Items.STONE_SWORD));
        registerRapier(consumer, ItemRegistry.IRON_RAPIER.get(), Ingredient.of(Tags.Items.INGOTS_IRON),
                Ingredient.of(Items.IRON_SWORD));
        registerRapier(consumer, ItemRegistry.GOLDEN_RAPIER.get(), Ingredient.of(Tags.Items.INGOTS_GOLD),
                Ingredient.of(Items.GOLDEN_SWORD));
        registerRapier(consumer, ItemRegistry.DIAMOND_RAPIER.get(), Ingredient.of(Tags.Items.GEMS_DIAMOND),
                Ingredient.of(Items.DIAMOND_SWORD));
        netheriteSmithing(consumer, ItemRegistry.DIAMOND_RAPIER.get(), ItemRegistry.NETHERITE_RAPIER.get());
    }

    private void registerRapier(Consumer<IFinishedRecipe> consumer, IItemProvider rapier, Ingredient material,
            Ingredient sword) {
        ShapedRecipeBuilder.shaped(rapier).pattern("  S").pattern("AA ").pattern("PL ")
                .define('P', Ingredient.of(Tags.Items.RODS_WOODEN)).define('L', Ingredient.of(Tags.Items.LEATHER))
                .define('A', material).define('S', sword)
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(sword.getItems()[0].getItem()))
                .save(consumer);
    }

    private void netheriteSmithing(Consumer<IFinishedRecipe> consumer, IItemProvider input, IItemProvider output) {
        SmithingRecipeBuilder.smithing(Ingredient.of(input), Ingredient.of(Items.NETHERITE_INGOT), output.asItem())
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(consumer, output.asItem().getRegistryName() + "_smithing");
    }
}
