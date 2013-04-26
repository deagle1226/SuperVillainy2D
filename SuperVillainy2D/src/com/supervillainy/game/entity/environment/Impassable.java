package com.supervillainy.game.entity.environment;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Player;
import com.supervillainy.game.power.RangedPower;
import com.supervillainy.game.power.enemy.EnemyRangedPower;

public abstract class Impassable extends Piece {
	
	private float threshold = 10f;

	@Override
	public void collide(EntityManager manager, Entity other) {
		if (other instanceof RangedPower || other instanceof EnemyRangedPower){
			manager.removeEntity(other);
		} else if (other instanceof Entity){
			if (other.getShape().getMinX() < shape.getMaxX() &&
					other.getShape().getMinX()+threshold > shape.getMaxX()){
				other.getShape().setX(shape.getMaxX());
				//other.getShape().setLocation(shape.getMaxX(), other.getShape().getY());
			} else if (other.getShape().getMaxX() > shape.getMinX() && 
					other.getShape().getMaxX()-threshold < shape.getMinX()){
				other.getShape().setX(shape.getMinX()-other.getShape().getWidth());
				//other.getShape().setLocation(shape.getMinX()-other.getShape().getWidth(), other.getShape().getY());
			} else if (other.getShape().getMinY() < shape.getMaxY() && 
					other.getShape().getMinY()+threshold > shape.getMaxY()){
				other.getShape().setY(shape.getMaxY());
				//other.getShape().setLocation(other.getShape().getX(), shape.getMaxY());
			} else if (other.getShape().getMaxY() > shape.getMinY() && 
					other.getShape().getMaxY()-threshold < shape.getMinY()){
				other.getShape().setY(shape.getMinY()-other.getShape().getHeight());
				//other.getShape().setLocation(other.getShape().getX(), shape.getMinY()-other.getShape().getHeight());
			}
		}
	}

}
