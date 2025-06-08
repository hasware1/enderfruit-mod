package enderfruit.modid;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class EnderFruitItem extends Item {
    public EnderFruitItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.SLOW_FALLING,
                    80,
                    0
            ));
        }

        HitResult result = user.raycast(30.0, 1.0f, true);
        Vec3d vec3d = result.getPos();
        user.requestTeleport(vec3d.x, vec3d.y, vec3d.z);

        world.playSound(null, vec3d.x, vec3d.y, vec3d.z, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f);


        // Spawn portal particles after teleport
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            for (int i = 0; i < 32; i++) {
                double offsetX = world.random.nextDouble() - 1.0;
                double offsetY = world.random.nextDouble() - 1.0;
                double offsetZ = world.random.nextDouble() - 1.0;
                serverWorld.spawnParticles(
                        ParticleTypes.PORTAL,
                        vec3d.x, vec3d.y + 1.0, vec3d.z,
                        20,
                        offsetX, offsetY, offsetZ,
                        0.5
                );
            }
        }

        return super.finishUsing(stack, world, user);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("tooltip.enderfruit.ender_fruit.tooltip").formatted(Formatting.GOLD));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}
