package com.supervillainy.game.entity.enemy;

import org.newdawn.slick.Graphics;

import com.supervillainy.game.entity.AbstractEntity;
import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.power.Power;
import com.supervillainy.game.power.RangedPower;
import com.supervillainy.game.power.enemy.EnemyPower;

public class Enemy extends AbstractEntity {
	
	protected int health;
	
	@Override
	public void update(EntityManager manager, int delta) {
		super.update(manager, delta);
		if (health <= 0){
			manager.removeEntity(this);
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setAntiAlias(false);
		graphics.fill(shape);
		graphics.setAntiAlias(true);
		graphics.draw(shape);
	}

	@Override
	public void collide(EntityManager manager, Entity other) {
		if (other instanceof Power && !(other instanceof EnemyPower)) {
			health -= ((Power) other).getDamage();
			if (other instanceof RangedPower){
				manager.removeEntity(other);
			}
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
