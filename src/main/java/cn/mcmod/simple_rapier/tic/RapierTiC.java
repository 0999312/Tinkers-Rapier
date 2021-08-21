package cn.mcmod.simple_rapier.tic;

import java.util.List;

import cn.mcmod.simple_rapier.RapierMain;
import cn.mcmod.simple_rapier.RapierUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.ToolBaseStatDefinition;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.item.small.SwordTool;

public class RapierTiC extends SwordTool {
    
    static final ToolBaseStatDefinition RAPIER_BASE = new ToolBaseStatDefinition.Builder()
            .bonus(ToolStats.ATTACK_DAMAGE, 0.5f)
            .modifier(ToolStats.ATTACK_DAMAGE, 1f)
            .set(ToolStats.ATTACK_SPEED, 3.0f)
            .modifier(ToolStats.MINING_SPEED, 1f)
            .modifier(ToolStats.DURABILITY, 1f)
            .startingSlots(SlotType.UPGRADE, 2)
            .build();
    
    public static final ToolDefinition RAPIER = ToolDefinition
            .builder(RAPIER_BASE)
            .addPart(TinkerToolParts.smallBlade)
            .addPart(TinkerToolParts.toolBinding)
            .addPart(TinkerToolParts.largePlate)
            .addPart(TinkerToolParts.toolHandle)
            .addModifier(TinkerModifiers.silkyShears)
            .build();

    public RapierTiC() {
        super(new Item.Properties().tab(TinkerTools.TAB_TOOLS).tab(RapierMain.WEAPON_GROUP), RAPIER);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (!(entity instanceof LivingEntity))
            return super.onLeftClickEntity(stack, player, entity);
        LivingEntity target = (LivingEntity) entity;
        float baseDamage = ToolAttackUtil.getAttributeAttackDamage(ToolStack.from(stack), player, Hand.MAIN_HAND);
        RapierUtil.DoStingAttack(stack, (baseDamage * 0.75F), calculateExtraDamage(baseDamage, ToolStack.from(stack), player, target), player, target);
        return super.onLeftClickEntity(stack, player, entity);
    }

    public float calculateExtraDamage(float damage, IModifierToolStack tool,PlayerEntity player,LivingEntity target) {
        // boost damage from traits
        float baseDamage = damage;
        List<ModifierEntry> modifiers = tool.getModifierList();
        ToolAttackContext context = new ToolAttackContext(player, player, Hand.MAIN_HAND, target, target, false, 0, true);
        for (ModifierEntry entry : modifiers) {
          damage = entry.getModifier().getEntityDamage(tool, entry.getLevel(), context, baseDamage, damage);
        }
        
        return damage * 0.75F;
    }
    
    

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
        return RapierUtil.useRapier(level, player, hand);
    }
    
    
}
