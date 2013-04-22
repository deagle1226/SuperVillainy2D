package com.supervillainy.game.power;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;

public abstract class MeleePower extends Power {
	
	protected float rot;
	
	@Override
	public void render(Graphics graphics) {
		graphics.setAntiAlias(false);
		//graphics.rotate(shape.getX(), shape.getY()+shape.getHeight()/2, rot);
		graphics.setColor(Color.white);
		graphics.fill(shape);
		graphics.setAntiAlias(true);
		graphics.draw(shape);
		//graphics.resetTransform();
	}
	
	public abstract boolean active();

}
