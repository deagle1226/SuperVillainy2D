package com.supervillainy.game.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Color;

public class MapRipple {
	
	private int life;
	private Vector2f scale;
	private Color color;
	
	public MapRipple(){
		life = 5000;
		scale = new Vector2f(16,9).scale(0.1f);
		color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 0.2f);
		double r = Math.random();
		if (r < 0.8){
			color = Color.black;
		}
		
	}
	
	public void update(MapEffects manager, int delta){
		life -= delta;
		if (life < 0){
			manager.removeRipple(this);
		}
		scale.scale(1.025f);
	}
	
	public void render(Graphics g){
		g.setColor(color);
		g.setAntiAlias(false);
		g.fill(new Rectangle(Map.pos.x-scale.x, Map.pos.y-scale.y, Map.dim.x+scale.x*2, Map.dim.y+scale.y*2));
		g.setAntiAlias(true);
		g.setColor(new Color(color.r, color.g, color.b, color.a*3));
		g.draw(new Rectangle(Map.pos.x-scale.x, Map.pos.y-scale.y, Map.dim.x+scale.x*2, Map.dim.y+scale.y*2));
	}

}
