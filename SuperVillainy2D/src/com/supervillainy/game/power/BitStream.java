package com.supervillainy.game.power;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.particles.ParticleManager;
import com.supervillainy.game.map.Map;

public class BitStream extends RangedPower {
	
	private float speed = 0.5f;
	private int life = 4000;
	
	private ParticleManager particles;
	
	public BitStream(Vector2f pos, Vector2f rot){
		vel = rot.scale(speed);
		shape = new Circle(pos.x-2.5f, pos.y-2.5f, 5f);
		particles = new ParticleManager(2.5f, 150f, 6, 30);
	}
	
	@Override
	public void update(EntityManager manager, int delta) {
		super.update(manager, delta);
		particles.update(new Vector2f(shape.getX(), shape.getY()), delta, new Vector2f(0,0));
		//shape.setLocation(shape.getX() + Map.pos.x, shape.getY() + Map.pos.y);
		life -= delta;
		if (life < 0){
			manager.removeEntity(this);
		}
	}
	
	@Override
	public void render(Graphics graphics) {
		particles.render(graphics);
		//super.render(graphics);
	}

	@Override
	public void collide(EntityManager manager, Entity other) {
		
		
	}

	@Override
	public int getDamage() {
		return 1;
	}

}
