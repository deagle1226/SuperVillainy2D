package com.supervillainy.game.entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public interface Entity {
	
	public void update(EntityManager manager, int delta);
	
	public void render(Graphics graphics);
	
	public float size();
	
	public Vector2f pos();
	
	public void collide(EntityManager manager, Entity other);
	
	public boolean collides(Entity other);

}
