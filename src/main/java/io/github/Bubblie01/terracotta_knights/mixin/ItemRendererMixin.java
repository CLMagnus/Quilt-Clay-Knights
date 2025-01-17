package io.github.Bubblie01.terracotta_knights.mixin;

import io.github.Bubblie01.terracotta_knights.Main;
import io.github.Bubblie01.terracotta_knights.TerracottaRegistry;
import io.github.Bubblie01.terracotta_knights.items.TinySwordItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

	@Shadow
	@Final
	private ItemModels models;

	MinecraftClient client = MinecraftClient.getInstance();

	@ModifyVariable(method = "renderItem", at = @At("HEAD"))
	private BakedModel terracotta$_tridentItemModel(BakedModel model, ItemStack stack, ModelTransformationMode renderMode) {
		boolean bl = renderMode == ModelTransformationMode.GUI;
		if(stack.getItem() == TerracottaRegistry.TINY_PITCHFORK_ITEM && !bl) {
			return this.models.getModelManager().getModel(new ModelIdentifier(Main.MOD_ID, "tiny_diamond_pitchfork_item_model", "inventory"));
		}
		return model;
	}



	@Inject(method = "Lnet/minecraft/client/render/item/ItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V", at = @At("HEAD"))
	private void terracotta$_scaleTinyItem(LivingEntity entity, ItemStack item, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, World world, int light, int overlay, int seed, CallbackInfo ci) {
		if((item.getItem() instanceof TinySwordItem || item.getItem() == TerracottaRegistry.TINY_BOW_ITEM || item.getItem() == TerracottaRegistry.TINY_ARROW_ITEM) && (entity instanceof PlayerEntity)) {
			matrices.scale(0.5f,0.5f,0.5f);

		}
	}





}
