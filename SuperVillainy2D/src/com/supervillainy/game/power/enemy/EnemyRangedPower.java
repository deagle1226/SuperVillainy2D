package com.supervillainy.game.power.enemy;

import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.map.Map;

public abstract class EnemyRangedPower extends EnemyPower {
	
	protected Vector2f vel;
	
	@Override
	public void update(EntityManager manager, int delta) {
		shape.setLocation((shape.getX()+Map.vel.x) + vel.x*delta, (shape.getY()+Map.vel.y) + vel.y*delta);
	}

}
