package com.supervillainy.game.ai;

import java.util.ArrayList;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class SquadManager {
	
	private ArrayList<Squad> squads;
	private int selected;
	
	public SquadManager(){
		squads = new ArrayList<Squad>();
	}
	
	public void add(Squad s){
		squads.add(s);
	}
	
	public void moveMinion(Minion m, Squad origin, Squad target){
		if (!target.isFull()){
			target.add(m);
			origin.remove(m);
		}
	}
	
	public void next(){
		selected++;
		if (selected > squads.size()-1){
			selected = 0;
		}
	}
	
	public void prev(){
		selected--;
		if (selected<0){
			selected = squads.size()-1;
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta){
		for (Squad s : squads){
			s.update(gc, sbg, delta);
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float x, float y){
		int i = 0;
		for(Squad s : squads){
			if (i==selected) s.render(gc, sbg, g, x, y, true);
			else s.render(gc, sbg, g, x, y, false);
			x += s.mWidth()*2;
			i++;
		}
	}
	
	public Squad selected(){
		return squads.get(selected);
	}

}
