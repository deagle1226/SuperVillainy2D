package com.supervillainy.game.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.GameWindow;


public class Player extends AbstractEntity {
	 
	private float speed = 5f;
	
	private float r, g, b, a;
	
	private Vector2f orientation;
	
	public Player(){
		pos = new Vector2f(100f,100f);
		vel = new Vector2f(0f,0f);
		orientation = new Vector2f(1f,0f);
		r = 1f;
		b = 1f;
		g = 1f;
		a = 1f;
	}
	
	@Override
	public void update(EntityManager manager, int delta) {
		updateKeys(manager, delta);
		updateMouse(manager, delta);
		
		super.update(manager, delta);
	}

	private void updateKeys(EntityManager manager, int delta) {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)){
			vel.y = -speed;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)){
			vel.y = speed;
		} else {
			vel.y = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)){
			vel.x = speed;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_A)){
			vel.x = -speed;
		} else {
			vel.x = 0;
		}
	}

	private void updateMouse(EntityManager manager, int delta) {
		Vector2f mouse = new Vector2f(Mouse.getX(), GameWindow.WINDOW_HEIGHT-Mouse.getY());
		Vector2f dist = mouse.sub(pos);
		orientation = dist.normalise();
		rotation = (float) ((-Math.atan2(orientation.x, orientation.y) * 180 / Math.PI) + 180);
		if (Mouse.isButtonDown(0)){
			Shot shot = new Shot(new Vector2f(pos.x, pos.y), orientation);
			manager.addEntity(shot);
		}
	}

	@Override
	public float size() {
		return 30;
	}

	@Override
	public void collide(EntityManager manager, Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(new Color(r,g,b,a));
		graphics.fill(new Circle(pos.x,pos.y,size()));
		graphics.setAntiAlias(true);
		graphics.draw(new Circle(pos.x,pos.y,size()));
	}

}
