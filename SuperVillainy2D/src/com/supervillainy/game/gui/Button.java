package com.supervillainy.game.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Button {
	
	private Rectangle rect;
	protected String text;
	protected float x, y, dimx, dimy, r, g ,b, a;
	
	public Button(String text, float x, float y, float dimx, float dimy, float r, float g, float b, float a){
		this.text = text;
		this.x = x;
		this.y = y;
		this.dimx = dimx;
		this.dimy = dimy;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		rect = new Rectangle(x, y, dimx, dimy);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta){
		int mx = gc.getInput().getMouseX();
		int my = gc.getInput().getMouseY();
		mouseOut();
		if (mx > x && mx < x + dimx){
			if (my > y && my < y + dimy){
				mouseIn();
				if (gc.getInput().isMousePressed(0)){
					action(sbg);
				}
			}
		}
		
	}
	
	public void render(Graphics graphics){
		graphics.setColor(new Color(r,g,b,a/2));
		graphics.fill(rect);
		graphics.setColor(new Color(r,g,b,a));
		graphics.drawString(text.toUpperCase(), x, y);
	}
	
	public abstract void mouseIn();
	
	public abstract void mouseOut();
	
	public abstract void action(StateBasedGame sbg);

}
