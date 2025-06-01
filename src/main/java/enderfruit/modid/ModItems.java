package enderfruit.modid;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
	public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
		// Create the item key.
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Enderfruit.MOD_ID, name));

		// Create the item instance.
		Item item = itemFactory.apply(settings.registryKey(itemKey));

		// Register the item.
		Registry.register(Registries.ITEM, itemKey, item);
        
        

		return item;
	}
    public static final Item ENDER_FRUIT = register("ender_fruit",
			EnderFruitItem::new,
			new Item.Settings().maxCount(1)
					.food(new FoodComponent.Builder()
							.saturationModifier((float) 2.4)
							.nutrition(20)
							.alwaysEdible()
							.build()));
    public static void initialize() {
    }

}
















