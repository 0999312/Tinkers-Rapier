package cn.mcmod.tinker_rapier;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RapierUtil {
    public static InteractionResultHolder<ItemStack> useRapier(Level level, Player player, InteractionHand hand) {
        ItemStack itemStackIn = player.getItemInHand(hand);
        if (!player.isOnGround()) {
            player.causeFoodExhaustion(0.1f);
            float f = 0.25F;
            float motionX = Mth.sin(player.getYRot() / 180.0F * (float) Math.PI)
                    * Mth.cos(player.getXRot() / 180.0F * (float) Math.PI) * f;
            float motionZ = -Mth.cos(player.getYRot() / 180.0F * (float) Math.PI)
                    * Mth.cos(player.getXRot() / 180.0F * (float) Math.PI) * f;
            player.setDeltaMovement(motionX, 0.2F, motionZ);

        } else {
            float f = 0.5F;
            float motionX = Mth.sin(player.getYRot() / 180.0F * (float) Math.PI)
                    * Mth.cos(player.getXRot() / 180.0F * (float) Math.PI) * f;
            float motionZ = -Mth.cos(player.getYRot() / 180.0F * (float) Math.PI)
                    * Mth.cos(player.getXRot() / 180.0F * (float) Math.PI) * f;
            player.setDeltaMovement(motionX, 0.1F, motionZ);
        }
        player.getCooldowns().addCooldown(itemStackIn.getItem(), 5);
        InteractionResult result = InteractionResult.SUCCESS;
        if (hand == InteractionHand.MAIN_HAND) {
            ItemStack offhand = player.getOffhandItem();
            if (!offhand.isEmpty()) {
                result = InteractionResult.PASS;
            }
        }
        return new InteractionResultHolder<>(result, itemStackIn);
    }

    public static void DoStingAttack(ItemStack stack, float baseDamage, float exDamage, LivingEntity attacker,
            LivingEntity target, boolean isPassArmor) {
        boolean isPlayer = attacker instanceof Player;
        float f = baseDamage, f1 = exDamage;
        if (isPlayer) {
            float f2 = ((Player) attacker).getAttackStrengthScale(0.5F);
            f = f * (0.2F + f2 * f2 * 0.8F);
            f1 = f1 * f2;
        }
        f += f1;
        f *= RapierConfig.RAPIER_ATTACK_BOUNS.get();
        if (isPassArmor) {
            f *= 0.5F;
            boolean flag5 = isPlayer
                    ? target.hurt(DamageSource.playerAttack((Player) attacker).bypassArmor().bypassMagic(), f)
                    : target.hurt(DamageSource.mobAttack(attacker).bypassArmor().bypassMagic(), f);
            if (flag5) {
                // don't prevent main damage from applying
                target.invulnerableTime = 0;
                target.hurtTime = 0;
                target.level.playSound(null, target, SoundEvents.ANVIL_HIT, target.getSoundSource(), Math.max(1.0F, f),
                        (target.getRandom().nextFloat() - target.getRandom().nextFloat()) * 0.5F + 1.0F);
            }
        } else if (!target.hasItemInSlot(EquipmentSlot.CHEST)) {
            f *= 0.8F;
            boolean flag5 = isPlayer ? target.hurt(DamageSource.playerAttack((Player) attacker), f)
                    : target.hurt(DamageSource.mobAttack(attacker), f);
            if (flag5) {
                // don't prevent main damage from applying
                target.invulnerableTime = 0;
                target.hurtTime = 0;
                target.level.playSound(null, target, SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH, target.getSoundSource(),
                        Math.max(1.0F, f),
                        (target.getRandom().nextFloat() - target.getRandom().nextFloat()) * 0.5F + 1.0F);
            }
        }
    }

}
