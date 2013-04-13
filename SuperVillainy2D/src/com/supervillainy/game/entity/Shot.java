package com.supervillainy.game.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public class Shot extends AbstractEntity {
	
	private float speed = 10f;
	
	private int life = 2000;
	
	public Shot(Vector2f pos, Vector2f o){
		this.pos = pos;
		this.vel = o.scale(speed);
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
	public void render(Graphics graphics) {
		graphics.setColor(Color.blue);
		graphics.fill(new Circle(pos.x,pos.y,size()));
		graphics.setAntiAlias(true);
		graphics.draw(new Circle(pos.x,pos.y,size()));
	}

	@Override
	public float size() {
		return 2;
	}

	@Override
	public void collide(EntityManager manager, Entity other) {
		// TODO Auto-generated method stub
		
	}

}
