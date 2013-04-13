package com.supervillainy.game.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.entity.particles.ParticleManager;
import com.supervillainy.game.map.Map;

public class Shot extends AbstractEntity {
	
	private float speed = 10f;
	
	private int life = 5000;
	private ParticleManager particles;
	private Circle bounds;
	private float radius;
	
	public Shot(Vector2f pos, Vector2f o){
		this.pos = pos;
		this.vel = o.scale(speed);
		this.radius = 2.5f;
		particles = new ParticleManager(radius, 150f, 6, 30);
		bounds = new Circle(pos.x, pos.y, size());
	}
	
	@Override
	public void update(EntityManager manager, int delta) {
		super.update(manager, delta);
		particles.update(new Vector2f(Map.pos.x + pos.x, Map.pos.y + pos.y), delta);
		bounds.setLocation(pos.x-radius, pos.y-radius);
		life -= delta;
		if (life < 0){
			manager.removeEntity(this);
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setAntiAlias(true);
		//graphics.draw(bounds);
		particles.render(graphics);
	}
	
	@Override
	public float size() {
		return 6;
	}

	@Override
	public void collide(EntityManager manager, Entity other) {
		// TODO Auto-generated method stub
		
	}

}
