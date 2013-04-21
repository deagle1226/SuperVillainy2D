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

import com.supervillainy.game.BattleState;
import com.supervillainy.game.GameWindow;
import com.supervillainy.game.entity.particles.ParticleManager;
import com.supervillainy.game.map.Map;
import com.supervillainy.game.power.BasicPunch;
import com.supervillainy.game.power.BitStream;
import com.supervillainy.game.power.MeleePower;
import com.supervillainy.game.power.Power;


public class Player extends AbstractEntity {
	 
	private float speed = 0.25f;
	
	private float r, g, b, a;
	
	private Vector2f orientation;
	private ParticleManager particles;
	private Vector2f relPos;
	
	private int shotTime = 0;
	private int shotFreq = 1;
	private int meleeTime = 0;
	private int meleeFreq = 900;
	
	private Rectangle bounds = new Rectangle(GameWindow.WINDOW_WIDTH/2-GameWindow.WINDOW_WIDTH/4,
			GameWindow.WINDOW_HEIGHT/2-GameWindow.WINDOW_HEIGHT/4,
			GameWindow.WINDOW_WIDTH/2,
			GameWindow.WINDOW_HEIGHT/2);
	
	private MeleePower melee;
	
	public Player(){
		vel = new Vector2f(0f,0f);
		orientation = new Vector2f(1f,0f);
		r = 1f;
		b = 1f;
		g = 1f;
		a = 1f;
		particles = new ParticleManager(7f, 300f, 3, 5);
		shape = new Rectangle(GameWindow.WINDOW_WIDTH/2,GameWindow.WINDOW_HEIGHT/2,30,30);
		melee = new BasicPunch(0);
	}
	
	@Override
	public void update(EntityManager manager, int delta) {
		relPos = new Vector2f(shape.getX()-Map.pos.x,shape.getY()-Map.pos.y);
		updateKeys(manager, delta);
		updateMouse(manager, delta);
		Vector2f temp = new Vector2f(shape.getX(), shape.getY());
		particles.update(temp.add(new Vector2f(shape.getWidth()/2-4,shape.getWidth()/2-4)), delta, Map.vel);
		super.update(manager, delta);
		float rotation = ((relPos.x + shape.getWidth()/2 - Map.dim.x/2) - (relPos.y + shape.getHeight()/2 - Map.dim.y/2))/300;
		BattleState.rotation = rotation;
	}

	private void updateKeys(EntityManager manager, int delta) {
		
		vel.y = 0;
		vel.x = 0;
		Map.changeVel(new Vector2f(0,0));
		if (Keyboard.isKeyDown(Keyboard.KEY_W)){
			if (!melee.active()) vel.y = -speed;
			if (shape.getY() <= bounds.getY()){
				vel.y = 0;
				if (!melee.active())Map.changeVel(new Vector2f(0,speed).scale(delta));
				if (Map.pos.y >= shape.getY()){
					Map.changeVel(new Vector2f(0,0));
				}
			}

		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)){
			if (!melee.active()) vel.y = speed;
			if (shape.getY() >= bounds.getY() + bounds.getHeight()-shape.getHeight()){
				vel.y = 0;
				if (!melee.active())Map.changeVel(new Vector2f(0,-speed).scale(delta));
				if (Map.pos.y+Map.dim.y <= shape.getY()+shape.getHeight()){
					Map.changeVel(new Vector2f(0,0));
				}
			}
		} 
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D)){
			if (!melee.active()) {
				vel.x = speed;
				
			}
			if (shape.getX() >= bounds.getX() + bounds.getWidth()-shape.getWidth()){
				vel.x = 0;
				if (!melee.active()){
					Map.changeVel(new Vector2f(-speed,0).scale(delta));
				}
				if (Map.pos.x+Map.dim.x <= shape.getX()+shape.getWidth()){
					Map.changeVel(new Vector2f(0,0));
				}
			}
		} else if (Keyboard.isKeyDown(Keyboard.KEY_A)){
			if (!melee.active()) {
				vel.x = -speed;
			}
			if (shape.getX() < bounds.getX()){
				vel.x = 0;
				if (!melee.active()) {
					Map.changeVel(new Vector2f(speed,0).scale(delta));
				}
				if (Map.pos.x >= shape.getX()){
					Map.changeVel(new Vector2f(0,0));
				}
			}
		}
		
	}

	private void updateMouse(EntityManager manager, int delta) {
		Vector2f mouse = new Vector2f(Mouse.getX(), GameWindow.WINDOW_HEIGHT-Mouse.getY());
		Vector2f dist = mouse.sub(new Vector2f(shape.getX(), shape.getY())).sub(new Vector2f(shape.getWidth()/2, shape.getWidth()/2));
		orientation = dist.normalise();
		rotation = (float) ((-Math.atan2(orientation.x, orientation.y) * 180 / Math.PI) + 180);
		shotTime -= delta;
		if (shotTime < 0){
			if (Mouse.isButtonDown(0)){
				Power shot = new BitStream(new Vector2f(shape.getX()+shape.getWidth()/2, shape.getY()+shape.getWidth()/2), orientation);
				manager.addEntity(shot);
				shotTime = shotFreq;
			}
			
		}
		meleeTime -= delta;
		if (meleeTime < 0){
			if (Mouse.isButtonDown(1)){
				melee = new BasicPunch(new Vector2f(shape.getX()+shape.getWidth()/2, shape.getY()+shape.getHeight()/2), (float) orientation.getTheta());
				manager.addEntity(melee);
				meleeTime = meleeFreq;
			}
		}
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
