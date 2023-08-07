package io.github.Bubblie01.terracotta_knights.entities.ai;

import io.github.Bubblie01.terracotta_knights.TerracottaRegistry;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.BowItem;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TerracottaKnightAttackGoal extends Goal{
	private TerracottaKnightEntity knightEntity;
	private PathAwareEntity targetEntity;
	private Path path;
	private Vec3d pos;
	private List<Entity> entityList;
	private List<TerracottaKnightEntity> enemyList = new ArrayList<TerracottaKnightEntity>();
	private Box searchBox;
	private float searchRange;
	private float attackRange;

	private boolean sideways;

	private boolean backwards = false;
	public TerracottaKnightAttackGoal(TerracottaKnightEntity knightEntity, float searchRange, float attackRange) {
		this.knightEntity = knightEntity;
		this.searchRange = searchRange;
		this.attackRange = attackRange;
		this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
	}


	@Override
	public boolean canStart() {
		searchBox = knightEntity.getBoundingBox().expand((double)searchRange,(double)searchRange,(double)searchRange);
		entityList = knightEntity.getWorld().getEntitiesByClass(Entity.class,searchBox, knightEntity -> knightEntity != null);
		for(int i = 0; i<entityList.size(); i++) {
			Entity entity = entityList.get(i);
			if(entity instanceof TerracottaKnightEntity) {
				int color = ((TerracottaKnightEntity) entity).getColor();
				if(color != knightEntity.getColor()) {
					enemyList.add((TerracottaKnightEntity) entity);
				}
				else {
					enemyList.remove(entity);
				}
			}
		}

		if((enemyList != null && enemyList.size() != 0)) {
			if((knightEntity.getTarget() == null || knightEntity.getTarget().isDead())) {
					int random = (int) (Math.random() * enemyList.size());
					if(knightEntity.getVisibilityCache().canSee(enemyList.get(random)))
						knightEntity.setTarget(enemyList.get(random));
					else
						return false;

			}
			else {
				int knightColor = knightEntity.getColor();
				int enemyColor = ((TerracottaKnightEntity)knightEntity.getTarget()).getColor();
				if(enemyColor == knightColor) {
					knightEntity.setTarget(null);
				}
			}
			enemyList.clear();
			return true;
		}

		return false;

	}

	@Override
	public void start() {
		knightEntity.setAttacking(true);
	}

	@Override
	public void tick() {
		//CLEAN UP THIS CODE LOSER JESUS FUCKING CHRIST
		if(knightEntity.getAttacker() instanceof TerracottaKnightEntity && knightEntity.getColor() != ((TerracottaKnightEntity) knightEntity.getAttacker()).getColor() && knightEntity.getAttacker() != null) {
			knightEntity.setTarget(knightEntity.getAttacker());
		}
		if(knightEntity.getTarget() != null) {
			path = knightEntity.getNavigation().findPathTo(knightEntity.getTarget(), 0);
			//knightEntity.getLookControl().lookAt(knightEntity.getTarget());
			//knightEntity.getNavigation().startMovingAlong(path, 0.5f);
			if (knightEntity.squaredDistanceTo(knightEntity.getTarget()) <= this.getAttackDistance((PathAwareEntity) knightEntity.getTarget()) && !knightEntity.isHolding(TerracottaRegistry.TINY_BOW_ITEM)) {
				knightEntity.tryAttack(knightEntity.getTarget());
				knightEntity.swingHand(Hand.MAIN_HAND);
			} else if (knightEntity.isHolding(TerracottaRegistry.TINY_BOW_ITEM)) {
				if (knightEntity.squaredDistanceTo(knightEntity.getTarget()) >= 10) {
					knightEntity.setCurrentHand(ProjectileUtil.getHandPossiblyHolding(knightEntity, TerracottaRegistry.TINY_BOW_ITEM));
					int useTime = knightEntity.getItemUseTime();
					if (useTime >= 20) {
						knightEntity.clearActiveItem();
						knightEntity.rangedAttack(knightEntity.getTarget(), BowItem.getPullProgress(useTime));
					}
					knightEntity.getMoveControl().strafeTo(0.5f, 0.5f);
					knightEntity.lookAtEntity(knightEntity.getTarget(), 30.0F, 30.0F);

				} else if (knightEntity.squaredDistanceTo(knightEntity.getTarget()) <= 5) {
					knightEntity.getMoveControl().strafeTo(-0.5f, -0.5f);
					knightEntity.lookAtEntity(knightEntity.getTarget(), 30.0F, 30.0F);
				}
			} else {
				knightEntity.getLookControl().lookAt(knightEntity.getTarget());
				knightEntity.getNavigation().startMovingAlong(path, 0.5f);
			}
		}
		super.tick();
	}

	@Override
	public boolean canStop() {
		if(knightEntity.getTarget() != null)
			if(!knightEntity.getVisibilityCache().canSee(knightEntity.getTarget()))
				return true;
		return false;
	}

	@Override
	public void stop() {
		knightEntity.setAttacking(false);
		knightEntity.getNavigation().stop();
	}

	private double getAttackDistance(PathAwareEntity entity) {
		return (double)(knightEntity.getWidth() * attackRange * knightEntity.getWidth() * attackRange + entity.getWidth());
	}
}
