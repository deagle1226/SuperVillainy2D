package com.supervillainy.game.power.enemy;

import org.newdawn.slick.Graphics;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Player;
import com.supervillainy.game.power.Power;

public abstract class EnemyPower extends Power {

	@Override
	public void collide(EntityManager manager, Entity other) {
		if (other instanceof Player){
			manager.removeEntity(this);
		}
		
	}

}
