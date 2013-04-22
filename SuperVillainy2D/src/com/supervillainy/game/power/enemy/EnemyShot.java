package com.supervillainy.game.power.enemy;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.entity.EntityManager;

public class EnemyShot extends EnemyRangedPower {
	
	private float speed = 0.5f;
	private int life = 4000;
	
	public EnemyShot(Vector2f pos, Vector2f rot){
		vel = rot.scale(speed);
		shape = new Circle(pos.x-2.5f, pos.y-2.5f, 5f);
	}

	@Override
	public void update(EntityManager manager, int delta) {
		super.update(manager, delta);
		life -= delta;
		if (life < 0){
			manager.removeEntity(this);
		}
	}

	@Override
	public int getDamage() {
		return 1;
	}

}
