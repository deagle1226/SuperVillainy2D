package com.supervillainy.game.entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class AbstractEntity implements Entity {
	
	protected float rotation;
	protected Vector2f vel;
	protected Shape shape;

	@Override
	public void update(EntityManager manager, int delta) {
		shape.setLocation(shape.getX() + vel.x*delta, shape.getY() + vel.y*delta);
	}

	@Override
	public boolean collides(Entity other) {
		return shape.intersects(other.getShape());
	}
	
	public Shape getShape(){
		return shape;
	}

}
