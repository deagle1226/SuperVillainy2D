package com.supervillainy.game.map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Map {
	
	public static Vector2f pos;
	public static Vector2f vel;
	public static Vector2f dim;
	public MapEffects effects;
	
	public Map(){
		pos = new Vector2f(0,0);
		vel = new Vector2f(0,0);
		dim = new Vector2f(1920, 1080);
		effects = new MapEffects();
	}
	
	public static void changeVel(Vector2f delta){
		vel = delta;
	}
	
	public void update(int delta){
		pos = pos.add(vel);
		effects.update(delta);
	}
	
	public void render(Graphics graphics){
		effects.render(graphics);
		graphics.setColor(new Color(0.2f,0.2f,0.2f,1f));
		graphics.setAntiAlias(false);
		graphics.fill(new Rectangle(pos.x, pos.y, dim.x, dim.y));
		graphics.setColor(Color.white);
		graphics.setAntiAlias(true);
		graphics.draw(new Rectangle(pos.x, pos.y, dim.x, dim.y));
	}

}
