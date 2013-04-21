package com.supervillainy.game.power;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;

public class BasicPunch extends MeleePower {
	
	private float maxLife = 750;
	private float life;
	private float x = 1f;
	private float speed = 2.5f;
	
	public BasicPunch(Vector2f pos, float rot){
		this.rot = rot;
		shape = new Rectangle(pos.x, pos.y-10, 100, 20);
		life = maxLife;
	}
	
	public BasicPunch(float life){
		this(new Vector2f(0,0), 0);
		this.life = life;
	}

	@Override
	public void update(EntityManager manager, int delta) {
		life -= delta;
		if (life > maxLife/2f){
			x += speed;
		} else {
			x -= speed;
		}
		if (life < 0){
			manager.removeEntity(this);
		}
		((Rectangle) shape).setWidth(x);
	}

	@Override
	public void collide(EntityManager manager, Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean active() {
		return life > 0;
	}

}
