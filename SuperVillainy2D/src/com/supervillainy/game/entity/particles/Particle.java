package com.supervillainy.game.entity.particles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;

public class Particle {
	
	private float life = 300f;
	
	private float size = 5;
	
	private float fade;
	private Vector2f pos;
	private float r,g,b,a;
	private Color color;
	
	public Particle(Vector2f pos, float size, float life, float freq){
		this.life = life;
		fade = life;
		this.pos = pos;
		double r = Math.random()*100;
		if (r < freq*1){
			color = Color.red;
		} else if (r < freq*2){
			color = Color.green;
		} else if (r < freq*3){
			color = Color.blue;
		} else {
			color = Color.white;
		}
		a = 1f;
		this.size = (float) (Math.random()*size + 3);			
	}
	
	public void update(ParticleManager manager, int delta, Vector2f vel){
		pos = pos.add(vel);
		fade-=delta;
		a = (fade*fade)/(life*life);
		if (fade < 0){
			manager.remove(this);
		}
	}

	public void render(Graphics graphics) {
		graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), a/2));
		graphics.fill(new Rectangle(pos.x, pos.y, size, size));
		graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), a));
		graphics.draw(new Rectangle(pos.x, pos.y, size, size));
	}



}
