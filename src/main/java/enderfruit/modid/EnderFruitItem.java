package enderfruit.modid;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.Consumer;

public class EnderFruitItem extends Item {
    public EnderFruitItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.SLOW_FALLING, // The effect type
                    80, // 10 seconds (200 ticks)
                    0   // Amplifier level 0 (Levitation I)
            ));
        }
        // upto 20 blocks
        HitResult result = user.raycast(30.0, 1.0f, true);
        Vec3d vec3d = result.getPos();
        user.requestTeleport(
                vec3d.x,
                vec3d.y,
                vec3d.z
        );

        return super.finishUsing(stack, world, user);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("tooltip.enderfruit.ender_fruit.tooltip").formatted(Formatting.GOLD));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}