package io.github.Bubblie01.terracotta_knights;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TerracottaRegistry {

	//items
	public static final TerracottaKnightItem TERRACOTTA_KNIGHT_ITEM = new TerracottaKnightItem(new Item.Settings().group(ItemGroup.MISC));
	public static final ToolItem TINY_WOODEN_SWORD_ITEM = new TinySwordItem(ToolMaterials.WOOD,1, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
	public static final ToolItem TINY_STONE_SWORD_ITEM = new TinySwordItem(ToolMaterials.STONE,1, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
	public static final ToolItem TINY_IRON_SWORD_ITEM = new TinySwordItem(ToolMaterials.IRON,1, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
	public static final ToolItem TINY_GOLD_SWORD_ITEM = new TinySwordItem(ToolMaterials.GOLD,1, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
	public static final ToolItem TINY_DIAMOND_SWORD_ITEM = new TinySwordItem(ToolMaterials.DIAMOND,1, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
	public static final ToolItem TINY_NETHERITE_SWORD_ITEM = new TinySwordItem(ToolMaterials.NETHERITE,1, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
	public static final TinyBowItem TINY_BOW_ITEM = new TinyBowItem(new Item.Settings().group(ItemGroup.COMBAT));
	public static final TinyArrowItem TINY_ARROW_ITEM = new TinyArrowItem(new Item.Settings().group(ItemGroup.COMBAT));
	public static final SignalDeviceItem SIGNAL_DEVICE_ITEM = new SignalDeviceItem(new Item.Settings().group(ItemGroup.MISC));
	public static final TinyPitchforkItem TINY_PITCHFORK_ITEM = new TinyPitchforkItem(new Item.Settings().group(ItemGroup.COMBAT));

	public static final Item TINY_IRON_HELMET = new TinyArmorItem(TinyArmorMaterial.TINY_IRON, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item TINY_IRON_CHESTPLATE = new TinyArmorItem(TinyArmorMaterial.TINY_IRON, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item TINY_IRON_LEGGINGS = new TinyArmorItem(TinyArmorMaterial.TINY_IRON, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item TINY_IRON_BOOTS = new TinyArmorItem(TinyArmorMaterial.TINY_IRON, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

	public static final Item TINY_GOLD_HELMET = new TinyArmorItem(TinyArmorMaterial.TINY_GOLD, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item TINY_GOLD_CHESTPLATE = new TinyArmorItem(TinyArmorMaterial.TINY_GOLD, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item TINY_GOLD_LEGGINGS = new TinyArmorItem(TinyArmorMaterial.TINY_GOLD, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item TINY_GOLD_BOOTS = new TinyArmorItem(TinyArmorMaterial.TINY_GOLD, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

	public static final Item TINY_DIAMOND_HELMET = new TinyArmorItem(TinyArmorMaterial.TINY_DIAMOND, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item TINY_DIAMOND_CHESTPLATE = new TinyArmorItem(TinyArmorMaterial.TINY_DIAMOND, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item TINY_DIAMOND_LEGGINGS = new TinyArmorItem(TinyArmorMaterial.TINY_DIAMOND, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item TINY_DIAMOND_BOOTS = new TinyArmorItem(TinyArmorMaterial.TINY_DIAMOND, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

	//recipies
	public static SpecialRecipeSerializer<TerracottaKnightRecipe> TERRACOTTA_KNIGHT_RECIPE;

	//sounds
	public static final Identifier SIGNAL_SOUND_ID = new Identifier(Main.MOD_ID, "signal_sound");
	public static final SoundEvent SIGNAL_SOUND_EVENT = new SoundEvent(SIGNAL_SOUND_ID);
	public static final Identifier SOUL_WHISPER_SOUND_ID = new Identifier(Main.MOD_ID, "soul_whisper");
	public static final SoundEvent SOUL_WHISPER_SOUND_EVENT = new SoundEvent(SOUL_WHISPER_SOUND_ID);

	//model layer
	public static final EntityModelLayer PITCHFORK = new EntityModelLayer(new Identifier(Main.MOD_ID, "pitchfork"), "pitchfork");

	public static void registerItems() {
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "terracotta_knight"), TERRACOTTA_KNIGHT_ITEM);

		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_wooden_sword_item"), TINY_WOODEN_SWORD_ITEM);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_stone_sword_item"), TINY_STONE_SWORD_ITEM);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_iron_sword_item"), TINY_IRON_SWORD_ITEM);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_gold_sword_item"), TINY_GOLD_SWORD_ITEM);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_sword_item"), TINY_DIAMOND_SWORD_ITEM);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_netherite_sword_item"), TINY_NETHERITE_SWORD_ITEM);

		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_bow"), TINY_BOW_ITEM);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "signal_device_item"), SIGNAL_DEVICE_ITEM);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_arrow"), TINY_ARROW_ITEM);

		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_pitchfork_item"), TINY_PITCHFORK_ITEM);

		//Iron Armor
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_iron_helmet"), TINY_IRON_HELMET);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_iron_chestplate"), TINY_IRON_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_iron_leggings"), TINY_IRON_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_iron_boots"), TINY_IRON_BOOTS);
		//Gold Armor
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_gold_helmet"), TINY_GOLD_HELMET);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_gold_chestplate"), TINY_GOLD_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_gold_leggings"), TINY_GOLD_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_gold_boots"), TINY_GOLD_BOOTS);
		//Diamond Armor
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_helmet"), TINY_DIAMOND_HELMET);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_chestplate"), TINY_DIAMOND_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_leggings"), TINY_DIAMOND_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_boots"), TINY_DIAMOND_BOOTS);

	}

	public static void registerRecipies() {
		TERRACOTTA_KNIGHT_RECIPE = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Main.MOD_ID, "crafting_special_terracotta_knight_recipe") , new SpecialRecipeSerializer<>(TerracottaKnightRecipe::new));
	}

	public static void registerColors() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DyeableItem)stack.getItem()).getColor(stack), TERRACOTTA_KNIGHT_ITEM);
	}

	public static void registerSounds() {
		Registry.register(Registry.SOUND_EVENT, SIGNAL_SOUND_ID, SIGNAL_SOUND_EVENT);
		Registry.register(Registry.SOUND_EVENT, SOUL_WHISPER_SOUND_ID, SOUL_WHISPER_SOUND_EVENT);
	}
}


