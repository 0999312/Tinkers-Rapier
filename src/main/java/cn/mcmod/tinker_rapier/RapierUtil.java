package cn.mcmod.tinker_rapier;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class RapierUtil {
    public static ActionResult<ItemStack> useRapier(World level, PlayerEntity player, Hand hand) {
        ItemStack itemStackIn = player.getItemInHand(hand);
        if (!player.isOnGround()) {
            player.causeFoodExhaustion(0.1f);
            float f = 0.25F;
            float motionX = MathHelper.sin(player.yRot / 180.0F * (float) Math.PI)
                    * MathHelper.cos(player.xRot / 180.0F * (float) Math.PI) * f;
            float motionZ = -MathHelper.cos(player.yRot / 180.0F * (float) Math.PI)
                    * MathHelper.cos(player.xRot / 180.0F * (float) Math.PI) * f;
            player.setDeltaMovement(motionX, 0.2F, motionZ);

        } else {
            float f = 0.5F;
            float motionX = MathHelper.sin(player.yRot / 180.0F * (float) Math.PI)
                    * MathHelper.cos(player.xRot / 180.0F * (float) Math.PI) * f;
            float motionZ = -MathHelper.cos(player.yRot / 180.0F * (float) Math.PI)
                    * MathHelper.cos(player.xRot / 180.0F * (float) Math.PI) * f;
            player.setDeltaMovement(motionX, 0.1F, motionZ);
        }
        player.getCooldowns().addCooldown(itemStackIn.getItem(), 5);
        ActionResultType result = ActionResultType.SUCCESS;
        if (hand == Hand.MAIN_HAND) {
            ItemStack offhand = player.getOffhandItem();
            if (!offhand.isEmpty() && (offhand.getItem().isShield(offhand, player))) {
                result = ActionResultType.PASS;
            }
        }
        return new ActionResult<>(result, itemStackIn);
    }
    
    public static void DoStingAttack(ItemStack stack, float baseDamage, float exDamage, LivingEntity attacker,
            LivingEntity target) {
        if (!target.hasItemInSlot(EquipmentSlotType.CHEST)) {
            boolean isPlayer = attacker instanceof PlayerEntity;
            float f = baseDamage, f1 = exDamage;
            if(isPlayer) {
                float f2 = ((PlayerEntity) attacker).getAttackStrengthScale(0.5F);
                f = f * (0.2F + f2 * f2 * 0.8F);
                f1 = f1 * f2;
            }
            f += f1;
            boolean flag5 = isPlayer
                    ? target.hurt(DamageSource.playerAttack((PlayerEntity) attacker), f)
                    : target.hurt(DamageSource.mobAttack(attacker), f);
            if (flag5) {
                // don't prevent main damage from applying
                target.invulnerableTime = 0;
                target.level.playSound(null, target, SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH, target.getSoundSource(), Math.max(1.0F, f), (target.getRandom().nextFloat() - target.getRandom().nextFloat()) * 0.5F + 1.0F);
            }
        }

    }
}
