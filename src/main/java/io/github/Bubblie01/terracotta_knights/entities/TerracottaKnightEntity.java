package io.github.Bubblie01.terracotta_knights.entities;

import io.github.Bubblie01.terracotta_knights.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class TerracottaKnightEntity extends PathAwareEntity {
	//public static final EntityType<TerracottaKnightEntity> TERRACOTTA_KNIGHT = Registry.register(Registry.ENTITY_TYPE, new Identifier(Main.MOD_ID, "terracotta_knight_entity"),FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, TerracottaKnightEntity::new).dimensions(EntityDimensions.changing(0.5F, 1.2F)).build());
	public static final EntityType<TerracottaKnightEntity> TERRACOTTA_KNIGHT = Registry.register(Registries.ENTITY_TYPE, new Identifier(Main.MOD_ID, "terracotta_knight_entity"), QuiltEntityTypeBuilder.create(SpawnGroup.MONSTER, TerracottaKnightEntity::new).setDimensions(EntityDimensions.changing(0.5f,1.2f)).build());
	public static final TrackedData<Integer> COLOR = DataTracker.registerData(TerracottaKnightEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<BlockPos> POS = DataTracker.registerData(TerracottaKnightEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
	public static final SimpleInventory terracottaKnightInventory = new SimpleInventory(5);
	public DyeColor dyeColor;
	protected TerracottaKnightEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public static void registerClayKnightEntityAttributes() {
		FabricDefaultAttributeRegistry.register(TERRACOTTA_KNIGHT, TerracottaKnightEntity.createAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0f).add(EntityAttributes.GENERIC_ATTACK_SPEED, 4.0f).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,  0.5f).add(EntityAttributes.GENERIC_MAX_HEALTH, 12f));
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new TerracottaKnightAttackGoal(this, 10.0f, 2.0f));
		this.goalSelector.add(1, new WanderAroundFarGoal(this,0.5f));
		this.goalSelector.add(0, new LookAroundGoal(this));
		super.initGoals();
	}

	@Override
	public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
		return true;
	}

	@Override
	protected void loot(ItemEntity item) {
		if(item.getStack().getItem() instanceof TinySwordItem || item.getStack().getItem() == TerracottaRegistry.TINY_BOW_ITEM) {
			item.discard();
			this.sendPickup(item,item.getStack().getCount());
			this.equipStack(EquipmentSlot.MAINHAND,item.getStack());
		}
		super.loot(item);
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BLOCK_STONE_PLACE;
	}

	@Override
	public boolean canPickupItem(ItemStack stack) {
		if(stack.getItem() instanceof TinySwordItem || stack.getItem() == TerracottaRegistry.TINY_BOW_ITEM) {
			return true;
		}
		return false;
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void tickMovement() {
		this.tickHandSwing();
		super.tickMovement();
	}

	@Override
	public boolean canPickUpLoot() {
		return true;
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(COLOR, 0);
	}

	public int getColor() {
		return this.dataTracker.get(COLOR);
	}

	public void setColor(int color) {
		this.dataTracker.set(COLOR, color);
	}

	@Override
	public NbtCompound writeNbt(NbtCompound nbt) {
		nbt.putInt("Color",this.dataTracker.get(COLOR));
		return super.writeNbt(nbt);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		if(nbt.getInt("Color") != 0) {
			setColor(nbt.getInt("Color"));
		}
		super.readNbt(nbt);
	}

	@Override
	protected ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack item = player.getStackInHand(hand);
		if(item.getItem() instanceof DyeItem) {
			dyeColor = ((DyeItem) item.getItem()).getColor();
			this.dataTracker.set(COLOR, (dyeColor.getMapColor().color));
		}

		if(item.getItem() instanceof TinySwordItem) {
			this.equipStack(EquipmentSlot.MAINHAND, item);
		}

		if(item.getItem() instanceof TinyArmorItem) {
			this.equipStack(((TinyArmorItem) item.getItem()).getPreferredSlot(), item);
		}

		this.swingHand(Hand.MAIN_HAND);
		return super.interactMob(player, hand);
	}

	public void rangedAttack(LivingEntity target, float pullProgress) {
			ItemStack itemStack = this.getArrowType(this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW)));
			PersistentProjectileEntity persistentProjectileEntity = TinyArrowEntity.createArrowProjectile(this,itemStack,pullProgress);

			double d = target.getX() - this.getX();
			double e = target.getBodyY(0.1) - persistentProjectileEntity.getY();
			double f = target.getZ() - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			persistentProjectileEntity.setVelocity(d, e + g * 0.2F, f, 1.6F, 0);
			this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.getWorld().spawnEntity(persistentProjectileEntity);
	}


}