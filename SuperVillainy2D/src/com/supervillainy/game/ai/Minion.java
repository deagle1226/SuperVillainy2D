package com.supervillainy.game.ai;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import com.supervillainy.game.ai.minions.abilities.Ability;
import com.supervillainy.game.ai.tasks.Task;

public abstract class Minion implements Comparable<Minion> {
	
	protected int rank;
	protected int experience;
	protected int experienceCap;
	protected ArrayList<Ability> abilities;
	private float dimx = 50;
	private float dimy = 30;
	protected float r, g, b, a;
	private float x, y;
	
	public Minion(){
		this(0);
	}
	
	public Minion(int xp){
		this(xp, new ArrayList<Ability>());
	}
	
	public Minion(int xp, ArrayList<Ability> abilities){
		experience = xp;
		this.abilities = abilities;
	}
	
	public void update(Task task, GameContainer gc, StateBasedGame sbg, int delta) {
		int mx = gc.getInput().getMouseX();
		int my = gc.getInput().getMouseY();
		if (mx > x && mx < x + dimx){
			if (my > y && my < y + dimy){
				if (gc.getInput().isMousePressed(0)){
					
				}
			}
		}
		for (Ability a : abilities){
			a.update();
		}
	}
	
	@Override
	public int compareTo(Minion m) {
		return -Double.compare(rank, m.rank);
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
	public void addXp(int xp){
		experience += xp;
	}
	
	public int getXpCap(){
		return experienceCap;
	}
	
	public float getWidth(){
		return dimx;
	}
	
	public float getHeight(){
		return dimy;
	}
	
	public abstract Minion nextRank();
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics, float x, float y, boolean selected){
		this.x = x;
		this.y = y;
		graphics.setColor(new Color(r, g, b, a));
		if (selected) graphics.setColor(new Color(r, g, b, a/2f));
		graphics.fill(new Rectangle(x, y, dimx, dimy));
		graphics.setColor(Color.black);
		graphics.drawString(this.getClass().getSimpleName(), x, y);
	}
}
