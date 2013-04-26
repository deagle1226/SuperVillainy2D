package com.supervillainy.game.power;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Player;
import com.supervillainy.game.entity.enemy.Enemy;

public class Blade extends Power {
	
	private Player player;
	private boolean main;
	
	private boolean active = false;
	
	private float length = 75;
	private float width = 10;
	
	private float sheathed = (float) Math.PI * 0.40f;
	private float fullSwing = (float) Math.PI * 1.45f;
	private float rot = sheathed;
	private float swingSpeed = 0.01f;
	
	private int cooldown = 750;
	private int time = cooldown;
	
	private float orientation;
	
	private ArrayList<Shape> fan;
	private int fanFreq = 0;
	
	public Blade(Player p, boolean main){
		if (!main){
			sheathed = (float) Math.PI * 0.45f;
			fullSwing = (float) Math.PI * -0.55f;
			rot = sheathed;
		}
		this.player = p;
		this.main = main;
		if (main){
			shape = new Rectangle(player.getPos().x-5, player.getPos().y+width, length, width);
			shape = shape.transform(Transform.createRotateTransform(rot, shape.getX(), shape.getY()));
		} else {
			shape = new Rectangle(player.getShape().getMaxX()+5, player.getPos().y, length, width);
			shape = shape.transform(Transform.createRotateTransform(rot, shape.getX(), shape.getY()+width));
		}
		fan = new ArrayList<Shape>();
	}

	@Override
	public void update(EntityManager manager, int delta) {
		time += delta;
		fanFreq -= delta;
		if (main){
			if (active) {
				rot += swingSpeed * delta;
				if (rot + orientation > fullSwing + orientation) active = false;
			} else {
				rot -= swingSpeed * delta;
				if (rot + orientation < sheathed + orientation) rot = sheathed;
			}
			if (fanFreq < 0){
				fanFreq = 5;
				fan.add(shape);
				if (fan.size()>5){
					fan.remove(0);
				}
			}
			if (rot == sheathed) fan.clear();
			shape = new Rectangle(player.getPos().x, player.getShape().getCenterY(), length, width);
			shape = shape.transform(Transform.createRotateTransform(rot, shape.getX(), shape.getY()));
			shape = shape.transform(Transform.createRotateTransform(orientation + rot/4, player.getShape().getCenterX(), player.getShape().getCenterY()));
		} else {
			if (active) {
				rot -= swingSpeed * delta;
				if (rot + orientation < fullSwing + orientation) active = false;
			} else {
				rot += swingSpeed * delta;
				if (rot + orientation > sheathed + orientation) rot = sheathed;
			}
			Vector2f vector = new Vector2f(1,0);
			vector.setTheta((orientation)*(180/Math.PI));
			System.out.println(vector);
			shape = new Rectangle(player.getShape().getMaxX()+(player.getShape().getWidth()*vector.x), player.getPos().y+(player.getShape().getHeight()*vector.y), length, width);
			shape = shape.transform(Transform.createRotateTransform(rot + orientation, this.getShape().getX(), this.getShape().getY()+width));
		}
		
	}
	
	public void activate(){
		if (time > cooldown){
			active = true;
			time = 0;
		}
		
	}

	@Override
	public void render(Graphics graphics) {
		float i = 5;
		for (Shape s : fan){
			graphics.setColor(new Color(1,1,1,(1f/i)*0.5f));
			graphics.fill(s);
			i--;
		}
		super.render(graphics);
	}

	@Override
	public void collide(EntityManager manager, Entity other) {
		if (other instanceof Enemy){
			((Enemy) other).setHealth(((Enemy) other).getHealth()-getDamage());
		}
		
	}

	@Override
	public int getDamage() {
		return 1;
	}
	
	public void setRot(float rot){
		orientation = (float) ((float) (rot * (Math.PI/180))+Math.PI/2);
	}


}
