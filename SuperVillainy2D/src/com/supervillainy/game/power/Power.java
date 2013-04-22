package com.supervillainy.game.power;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.entity.Entity;

public abstract class Power implements Entity {
	
	protected Shape shape;

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.white);
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
	
	public abstract int getDamage();
	

}
