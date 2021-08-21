package cn.mcmod.simple_rapier.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import cn.mcmod.simple_rapier.RapierUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RapierItem extends TieredItem implements IVanishable {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public RapierItem(IItemTier tier, Properties prop) {
        super(tier, prop);
        this.attackDamage = 0.5F + tier.getAttackDamageBonus();
        Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -1F,
                AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.category == EnchantmentType.WEAPON && enchantment != Enchantments.SWEEPING_EDGE)
            return true;
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    public float getDamage() {
        return this.attackDamage;
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
        return RapierUtil.useRapier(level, player, hand);
    }

    @Override
    public boolean canAttackBlock(BlockState blockstate, World level, BlockPos blockpos, PlayerEntity player) {
        return !player.isCreative();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState blockstate) {
        if (blockstate.is(Blocks.COBWEB)) {
            return 15.0F;
        }
        Material material = blockstate.getMaterial();
        return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.CORAL
                && !blockstate.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (!(entity instanceof LivingEntity))
            return super.onLeftClickEntity(stack, player, entity);

        LivingEntity target = (LivingEntity) entity;
        RapierUtil.DoStingAttack(stack, player, target);

        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, entity -> {
            entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack stack, World level, BlockState blockstate, BlockPos blockpos,
            LivingEntity entityLiving) {
        if (blockstate.getDestroySpeed(level, blockpos) != 0.0F) {
            stack.hurtAndBreak(2, entityLiving, entity -> {
                entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }
        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState blockstate) {
        return blockstate.is(Blocks.COBWEB);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        return slot == EquipmentSlotType.MAINHAND ? this.defaultModifiers : super.getAttributeModifiers(slot, stack);
    }
}
