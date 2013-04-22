package com.supervillainy.game.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class StatBar {
	
	protected float max;
	protected float current;
	protected Shape maxBar;
	protected Shape currentBar;
	
	public StatBar(int max, int current, float x, float y, float dimx, float dimy) {
		this.max = max;
		this.current = current;
		maxBar = new Rectangle(x, y, dimx, dimy);
		currentBar = new Rectangle(x, y, maxBar.getWidth() * (current/max), dimy);
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fill(maxBar);
		g.setColor(Color.red);
		g.fill(currentBar);
	}

}
