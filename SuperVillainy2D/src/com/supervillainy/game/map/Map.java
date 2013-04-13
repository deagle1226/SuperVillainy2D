package com.supervillainy.game.map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Map {
	
	public static Vector2f pos;
	public static Vector2f vel;
	public static Vector2f dim;
	
	public Map(){
		pos = new Vector2f(0,0);
		vel = new Vector2f(0,0);
		dim = new Vector2f(1920, 1080);
	}
	
	public static void changeVel(Vector2f delta){
		vel = delta;
	}
	
	public void update(int delta){
		pos = pos.add(vel);
	}
	
	public void render(Graphics graphics){
		graphics.setColor(new Color(1f,1f,1f,0.2f));
		graphics.fill(new Rectangle(pos.x, pos.y, dim.x, dim.y));
		graphics.setColor(Color.white);
		graphics.draw(new Rectangle(pos.x, pos.y, dim.x, dim.y));
	}

}
