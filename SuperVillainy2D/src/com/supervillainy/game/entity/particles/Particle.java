package com.supervillainy.game.entity.particles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;

public class Particle {
	
	public static final float life = 300f;
	
	private float size = 5;
	
	private float fade;
	private Vector2f pos;
	private float r,g,b,a;
	
	public Particle(Vector2f pos){
		fade = life;
		this.pos = pos;
		r = 1f;
		g = 1f;
		b = 1f;
		a = 1f;
		size = (float) (Math.random()*5 + 3);			
	}
	
	public void update(ParticleManager manager, int delta){
		fade-=delta;
		a = (fade*fade)/(life*life);
		if (fade < 0){
			manager.remove(this);
		}
	}

	public void render(Graphics graphics) {
		graphics.setColor(new Color(r,g,b,a/2));
		graphics.fill(new Rectangle(pos.x, pos.y, size, size));
		graphics.setColor(new Color(r,g,b,a));
		graphics.draw(new Rectangle(pos.x, pos.y, size, size));
	}



}
