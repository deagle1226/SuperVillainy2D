package com.supervillainy.game.entity;

import com.supervillainy.game.effects.Effect;

public interface EntityManager {
	
	public void removeEntity(Entity entity);
	
	public void addEntity(Entity entity);
	
	public void enemyKilled();
	
	public void playerHit();
	
	//public void effect(Effect effect);

}
