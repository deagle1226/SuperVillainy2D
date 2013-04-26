package com.supervillainy.game.power;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.GameWindow;
import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Player;

public class Shot extends Power {
	
	private boolean active = false;
	
	private int cooldown = 500;
	private int time = cooldown;
	
	private Player player;
	
	public Shot(Player p){
		player = p;
		shape = new Circle(0,0,1);
	}
	
	@Override
	public void update(EntityManager manager, int delta) {
		time += delta;
		if (active){
			Vector2f mouse = new Vector2f(Mouse.getX(), GameWindow.WINDOW_HEIGHT-Mouse.getY());
			Vector2f dist = mouse.sub(new Vector2f(player.getShape().getX(), player.getShape().getY())).sub(new Vector2f(player.getShape().getWidth()/2, player.getShape().getWidth()/2));
			Vector2f orientation = dist.normalise();
			Power shot = new BitStream(new Vector2f(player.getShape().getX()+player.getShape().getWidth()/2, player.getShape().getY()+player.getShape().getWidth()/2), orientation);
			manager.addEntity(shot);
			active = false;
		}
	}

	@Override
	public void collide(EntityManager manager, Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void activate(){
		if (time > cooldown){
			active = true;
			time = 0;
		}
	}

	@Override
	public void render(Graphics graphics) {
	}

}
