package com.supervillainy.game.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.GameWindow;
import com.supervillainy.game.entity.particles.ParticleManager;
import com.supervillainy.game.map.Map;


public class Player extends AbstractEntity {
	 
	private float speed = 5f;
	
	private float r, g, b, a;
	
	private Vector2f orientation;
	private ParticleManager particles;
	private Vector2f relPos;
	private Shape shape;
	
	private int shotTime = 0;
	private int shotFreq = 1;
	
	private Rectangle bounds = new Rectangle(GameWindow.WINDOW_WIDTH/2-GameWindow.WINDOW_WIDTH/4,
			GameWindow.WINDOW_HEIGHT/2-GameWindow.WINDOW_HEIGHT/4,
			GameWindow.WINDOW_WIDTH/2,
			GameWindow.WINDOW_HEIGHT/2);
	
	public Player(){
		pos = new Vector2f(GameWindow.WINDOW_WIDTH/2,GameWindow.WINDOW_HEIGHT/2);
		vel = new Vector2f(0f,0f);
		orientation = new Vector2f(1f,0f);
		r = 1f;
		b = 1f;
		g = 1f;
		a = 1f;
		particles = new ParticleManager(7f, 300f, 3, 5);
		shape = new Rectangle(pos.x,pos.y,size(),size());
	}
	
	@Override
	public void update(EntityManager manager, int delta) {
		relPos = new Vector2f(pos.x-Map.pos.x,pos.y-Map.pos.y);
		updateKeys(manager, delta);
		updateMouse(manager, delta);
		Vector2f temp = new Vector2f(pos);
		particles.update(temp.add(new Vector2f(size()/2-4,size()/2-4)), delta);
		super.update(manager, delta);
		shape.setLocation(pos.x, pos.y);
	}

	private void updateKeys(EntityManager manager, int delta) {
		vel.y = 0;
		vel.x = 0;
		Map.changeVel(new Vector2f(0,0));
		if (Keyboard.isKeyDown(Keyboard.KEY_W)){
			vel.y = -speed;
			if (pos.y <= bounds.getY()){
				vel.y = 0;
				Map.changeVel(new Vector2f(0,speed));
				if (Map.pos.y >= pos.y){
					Map.changeVel(new Vector2f(0,0));
				}
			}
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)){
			vel.y = speed;
			if (pos.y >= bounds.getY() + bounds.getHeight()-size()){
				vel.y = 0;
				Map.changeVel(new Vector2f(0,-speed));
				if (Map.pos.y+Map.dim.y <= pos.y+size()){
					Map.changeVel(new Vector2f(0,0));
				}
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)){
			vel.x = speed;
			if (pos.x >= bounds.getX() + bounds.getWidth()-size()){
				vel.x = 0;
				Map.changeVel(new Vector2f(-speed,0));
				if (Map.pos.x+Map.dim.x <= pos.x+size()){
					Map.changeVel(new Vector2f(0,0));
				}
			}
		} else if (Keyboard.isKeyDown(Keyboard.KEY_A)){
			vel.x = -speed;
			if (pos.x < bounds.getX()){
				vel.x = 0;
				Map.changeVel(new Vector2f(speed,0));
				if (Map.pos.x >= pos.x){
					Map.changeVel(new Vector2f(0,0));
				}
			}
		}
	}

	private void updateMouse(EntityManager manager, int delta) {
		Vector2f mouse = new Vector2f(Mouse.getX(), GameWindow.WINDOW_HEIGHT-Mouse.getY());
		Vector2f dist = mouse.sub(pos);
		orientation = dist.normalise();
		rotation = (float) ((-Math.atan2(orientation.x, orientation.y) * 180 / Math.PI) + 180);
		shotTime -= delta;
		if (shotTime < 0){
			if (Mouse.isButtonDown(0)){
				Shot shot = new Shot(new Vector2f(relPos.x+size()/2, relPos.y+size()/2), orientation);
				manager.addEntity(shot);
				shotTime = shotFreq;
			}
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
		particles.render(graphics);
//		graphics.setColor(new Color(r,g,b,a));
//		graphics.fill(new Circle(pos.x,pos.y,size()));
//		graphics.setAntiAlias(true);
//		graphics.draw(new Circle(pos.x,pos.y,size()));
		graphics.setColor(new Color(r,g,b,a/1.5f));
		graphics.fill(shape);
		graphics.setAntiAlias(true);
		graphics.setColor(new Color(r,g,b,a));
		graphics.draw(shape);
		//graphics.draw(bounds);
		
	}

}
