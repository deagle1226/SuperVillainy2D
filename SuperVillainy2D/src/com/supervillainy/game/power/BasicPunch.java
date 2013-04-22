package com.supervillainy.game.power;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.enemy.Enemy;

public class BasicPunch extends MeleePower {
	
	private float maxLife = 250;
	private float life;
	private float x = 1f;
	private float speed = 2.5f;
	
	public BasicPunch(Vector2f pos, float rot){
		this.rot = (float) (rot * (Math.PI/180f));
		shape = new Rectangle(pos.x, pos.y-10, 50, 20);
		shape = shape.transform(Transform.createRotateTransform(this.rot, pos.x, pos.y));
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
		//((Rectangle) shape).setWidth(x);
	}

	@Override
	public void collide(EntityManager manager, Entity other) {
	}

	@Override
	public boolean active() {
		return life > 0;
	}

	@Override
	public int getDamage() {
		return 2;
	}

}
