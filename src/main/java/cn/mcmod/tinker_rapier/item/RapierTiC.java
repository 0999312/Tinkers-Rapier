package cn.mcmod.tinker_rapier.item;

import java.util.List;

import cn.mcmod.tinker_rapier.RapierMain;
import cn.mcmod.tinker_rapier.RapierUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.item.ModifiableSwordItem;

public class RapierTiC extends ModifiableSwordItem {

    public static final ToolDefinition RAPIER = ToolDefinition
            .builder(TiCItemRegistry.RAPIER)
            .meleeHarvest()
            .build();

    public RapierTiC() {
        super(RapierMain.defaultItemProperties(), RAPIER);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        super.use(level, player, hand);
        return RapierUtil.useRapier(level, player, hand);
    }
    
    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (!(entity instanceof LivingEntity))
            return super.onLeftClickEntity(stack, player, entity);
        LivingEntity target = (LivingEntity) entity;
        float baseDamage = ToolAttackUtil.getAttributeAttackDamage(ToolStack.from(stack), player, EquipmentSlot.MAINHAND);
        RapierUtil.DoStingAttack(stack, (baseDamage * 0.75F), calculateExtraDamage(baseDamage, ToolStack.from(stack), player, target), player, target, false);
        return super.onLeftClickEntity(stack, player, entity);
    }

    public float calculateExtraDamage(float damage, IToolStackView tool,Player player,LivingEntity target) {
        // boost damage from traits
        float baseDamage = damage;
        List<ModifierEntry> modifiers = tool.getModifierList();
        ToolAttackContext context = new ToolAttackContext(player, player, InteractionHand.MAIN_HAND, target, target, false, 0, true);
        for (ModifierEntry entry : modifiers) {
          damage = entry.getModifier().getEntityDamage(tool, entry.getLevel(), context, baseDamage, damage);
        }
        return damage * 0.75F;
    }

}
