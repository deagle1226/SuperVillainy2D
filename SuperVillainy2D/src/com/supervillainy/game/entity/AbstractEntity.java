package com.supervillainy.game.entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public abstract class AbstractEntity implements Entity {
	
	protected float rotation;
	protected Vector2f pos;
	protected Vector2f vel;

	@Override
	public void update(EntityManager manager, int delta) {
		pos = pos.add(vel);
	}

	@Override
	public Vector2f pos(){
		return pos;
	}

	@Override
	public boolean collides(Entity other) {
		// We're going to use simple circle collision here since we're 
		// only worried about 2D collision.
		//
		// Normal math tells us that if the distance between the two
		// centres of the circles is less than the sum of their radius
		// then they collide. However, working out the distance between
		// the two would require a square root (Math.sqrt((dx*dx)+(dy*dy))
		// which could be quite slow.
		//
		// Instead we're going to square the sum of their radius and compare
		// that against the un-rooted value. This is equivilent but 
		// much faster
		
		// Get the size of the other entity and combine it with our
		// own, giving the range of collision. Square this so we can
		// compare it against the current distance.
		float otherSize = other.size();
		float range = (otherSize + size());
		range *= range;

		// Get the distance on X and Y between the two entities, then
		// find the squared distance between the two.
		float dx = pos().x - other.pos().x;
		float dy = pos().y - other.pos().y;
		float distance = (dx*dx)+(dy*dy);
		
		// if the squared distance is less than the squared range
		// then we've had a collision!
		return (distance <= range);
	}

}
