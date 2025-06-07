package enderfruit.modid;


import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class SoundPlayer extends Item {
    public SoundPlayer(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        System.out.println("finishUsing called!");
        if (!world.isClient()) {
            world.playSound(
                    user,                           // null = broadcast to nearby players
                    user.getBlockPos(),             // play at the user's location
                    SoundEvents.ENTITY_PLAYER_TELEPORT, // your chosen sound
                    SoundCategory.MASTER,          // player sound category
                    1.0f,                           // volume
                    1.0f                            // pitch
            );
        }

        return super.finishUsing(stack, world, user); // maintain normal eating behavior
    }

}
