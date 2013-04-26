package com.supervillainy.game.entity.environment;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import com.supervillainy.game.map.Map;

public class Wall extends Impassable {
	
	public Wall(float x, float y, float dimx, float dimy) {
		shape = new Rectangle(x, y, dimx, dimy);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.black);
		graphics.setAntiAlias(false);
		super.render(graphics);
		graphics.setAntiAlias(true);
		graphics.draw(shape);
	}

}
