package com.supervillainy.game.entity.enemy;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.GameWindow;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Player;
import com.supervillainy.game.map.Map;
import com.supervillainy.game.power.enemy.EnemyShot;

public class TestEnemy extends Enemy {
	
	private int freq = 1000;
	private int time = 0;
	private Player player;
	
	public TestEnemy(Player p){
		shape = new Circle((float)Math.random() * Map.dim.x + Map.pos.x + 10, (float)Math.random() * Map.dim.y + Map.pos.y + 10, 10);
		health = 5;
		rotation = 0;
		vel = new Vector2f(0,0);
		player = p;
	}

	@Override
	public void update(EntityManager manager, int delta) {
		super.update(manager, delta);
		time -= delta;
		Vector2f orientation = player.getPos().sub(new Vector2f(shape.getX(),shape.getY()));
		Vector2f temp = new Vector2f(orientation);
		vel = temp.normalise().scale(-0.1f);
		if (time < 0){
			time = freq;
			//manager.addEntity(new EnemyShot(new Vector2f(shape.getX(), shape.getY()), orientation.normalise()));
		}
	}

}
