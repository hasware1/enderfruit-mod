package enderfruit.modid;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class EnderFruitItem extends Item {
    public EnderFruitItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.ABSORPTION, // The effect type
                    600, // 10 seconds (200 ticks)
                    4    // Amplifier level 0 (Levitation I)
            ));
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.RESISTANCE, // The effect type
                    450, // 10 seconds (200 ticks)
                    4  // Amplifier level 0 (Levitation I)
            ));
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.FIRE_RESISTANCE, // The effect type
                    800, // 10 seconds (200 ticks)
                    1    // Amplifier level 0 (Levitation I)
            ));

        }
        if (world.isClient && user instanceof ServerPlayerEntity player) {
            Vec3d pos  = player.getPos();
            Vec3d vel = player.getVelocity().normalize();
            double y = pos.y;
            double x =  (10 * vel.x) + pos.x;
            double z = (10 * vel.z) + pos.z;
            player.teleport(x,y,z,true);


        }
        Vec3d vec3d = user.getPos();
        if (user.teleport(0,0,0,true)){
            world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
        }
        return super.finishUsing(stack, world, user);
    }
}
