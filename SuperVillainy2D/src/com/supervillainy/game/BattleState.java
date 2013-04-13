package com.supervillainy.game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Player;

public class BattleState extends BasicGameState implements EntityManager {
	
	/** The entity representing the player */
	private Player player;
	/** The entities in the game */
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	/** The list of entities to be added at the next opportunity */
	private ArrayList<Entity> addList = new ArrayList<Entity>();
	/** The list of entities to be removed at the next opportunity */
	private ArrayList<Entity> removeList = new ArrayList<Entity>();

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		player = new Player();
		entities.add(player);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for (Entity e : entities){
			e.render(g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {	
		for (int i=0;i<entities.size();i++) {
			Entity entity = (Entity) entities.get(i);
			
			for (int j=i+1;j<entities.size();j++) {
				Entity other = (Entity) entities.get(j);
				
				if (entity.collides(other)) {
					entity.collide(this, other);
					other.collide(this, entity);
				}
			}
		}
		
		entities.removeAll(removeList);
		entities.addAll(addList);
		
		removeList.clear();
		addList.clear();
		
		for (Entity e : entities) {
			e.update(this, delta);
		}
	}

	@Override
	public int getID() {
		return 2;
	}

	@Override
	public void removeEntity(Entity entity) {
		removeList.add(entity);
	}

	@Override
	public void addEntity(Entity entity) {
		addList.add(entity);
	}

	@Override
	public void enemyKilled() {
	}

	@Override
	public void playerHit() {
	}

}
