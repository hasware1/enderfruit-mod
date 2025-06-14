package enderfruit.modid;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemGroups;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Enderfruit implements ModInitializer {
	public static final String MOD_ID = "enderfruit";
	

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		ModItems.initialize();
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
				.register((itemGroup) -> itemGroup.add(ModItems.ENDER_FRUIT));
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


		LOGGER.info("Hello Fabric world!");
	}
	public static final RegistryEntry<StatusEffect> ENDEREFFECT =
			Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MOD_ID, "endereffect"), new EnderEffect());
}