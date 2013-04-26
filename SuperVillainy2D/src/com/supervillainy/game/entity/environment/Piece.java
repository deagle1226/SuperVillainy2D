package com.supervillainy.game.entity.environment;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.map.Map;

public abstract class Piece implements Entity {
	
	protected Shape shape;
	
	@Override
	public void update(EntityManager manager, int delta) {
		shape.setLocation(shape.getX()+Map.vel.x, shape.getY()+Map.vel.y);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.fill(shape);
	}

	@Override
	public boolean collides(Entity other) {
		return shape.intersects(other.getShape());
	}

	@Override
	public Shape getShape() {
		return shape;
	}

}
