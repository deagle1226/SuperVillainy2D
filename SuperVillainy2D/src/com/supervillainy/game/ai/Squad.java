package com.supervillainy.game.ai;

import java.util.PriorityQueue;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.supervillainy.game.ai.minions.commands.Command;
import com.supervillainy.game.ai.minions.commands.CommandQueue;
import com.supervillainy.game.ai.tasks.Task;
import com.supervillainy.game.ai.tasks.WaitTask;

public class Squad {
	
	public static final int SIZE = 5;
	
	private PriorityQueue<Minion> minions;
	private Task task;
	private CommandQueue commands;
	private int selected;
	
	public Squad(){
		minions = new PriorityQueue<Minion>();
		commands = new CommandQueue();
		task = new WaitTask();
	}
	
	public void add(Minion m){
		if (minions.size() < SIZE){
			minions.add(m);
		} else {
			System.out.println("squad is full");
		}
	}
	
	public void changeTask() {
		levelUp();
		if (commands.size() == 0){
			task = new WaitTask();
		} else {
			Command c = commands.pop();
			task = c.parse(task);
		}
	}
	
	public void levelUp(){
		PriorityQueue<Minion> temp = new PriorityQueue<Minion>();
		for (Minion m : minions){
			m.addXp(task.getReward());
			if (m.experience >= m.getXpCap()){
				temp.add(m.nextRank());
			} else {
				temp.add(m);
			}
		}
		minions = temp;
	}
	
	public void remove(Minion m){
		if (minions.contains(m)){
			minions.remove(m);
		}
	}
	
	public boolean isFull(){
		return !(minions.size() < SIZE);
	}
	
	public void next(){
		selected++;
		if (selected > minions.size()-1){
			selected = 0;
		}
	}
	
	public void prev(){
		selected--;
		if (selected<0){
			selected = minions.size()-1;
		}
	}
	
	public void command(Command c){
		commands.add(c);
	}
	
	
	public float mWidth() {
		return minions.peek().getWidth();
	}
	
	public Minion selected() {
		int i = 0;
		for (Minion m : minions){
			if (i == selected) return m;
			i++;
		}
		return null;
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta){
		task.update();
		if (task.isActive()){
			for (Minion m : minions){
				m.update(task, gc, sbg, delta);
			}
		} else {
			changeTask();
		}
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float x, float y, boolean selected){
		g.setColor(new Color(1f,1f,1f,0.3f));
		if (selected) g.setColor(new Color(1f,1f,1f,0.5f));
		g.fill(new Rectangle(x, y, minions.peek().getWidth()+20, (minions.size()*(minions.peek().getHeight()+5))+35));
		g.setColor(Color.white);
		g.drawString(task.getName(), x+10, y);
		y+=30;
		int i = 0;
		for (Minion m : minions) {
			if (i == this.selected && selected) m.render(gc, sbg, g, x+10, y, true);
			else m.render(gc, sbg, g, x+10, y, false);
			y += m.getHeight()+5;
			i++;
		}
		g.setColor(Color.white);
		for (Command c : commands.getCommands()){
			y += 20;
			g.drawString(c.getClass().getSimpleName(), x, y);
		}
	}

}
