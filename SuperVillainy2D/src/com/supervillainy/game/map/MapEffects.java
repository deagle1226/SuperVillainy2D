package com.supervillainy.game.map;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class MapEffects {
	
	private ArrayList<MapRipple> ripples;
	private ArrayList<MapRipple> add;
	private ArrayList<MapRipple> remove;
	private int time;
	private int freq = 200;
	
	public MapEffects(){
		ripples = new ArrayList<MapRipple>();
		add = new ArrayList<MapRipple>();
		remove = new ArrayList<MapRipple>();
	}
	
	public void update(int delta){
		time += delta;
		if (time > freq){
			time = 0;
			addRipple(new MapRipple());
		}
		
		ripples.removeAll(remove);
		ripples.addAll(add);
		
		remove.clear();
		add.clear();
		
		for (MapRipple mr : ripples){
			mr.update(this, delta);
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		for (MapRipple mr : ripples){
			mr.render(g);
		}
	}
	
	public void removeRipple(MapRipple mr){
		remove.add(mr);
	}
	
	public void addRipple(MapRipple mr){
		add.add(mr);
	}

}
