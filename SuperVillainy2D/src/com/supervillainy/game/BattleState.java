package com.supervillainy.game;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Player;
import com.supervillainy.game.entity.enemy.TestEnemy;
import com.supervillainy.game.entity.particles.Particle;
import com.supervillainy.game.gui.HealthBar;
import com.supervillainy.game.map.Map;

public class BattleState extends BasicGameState implements EntityManager {
	
	/** The entity representing the player */
	private Player player;
	/** The entities in the game */
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	/** The list of entities to be added at the next opportunity */
	private ArrayList<Entity> addList = new ArrayList<Entity>();
	/** The list of entities to be removed at the next opportunity */
	private ArrayList<Entity> removeList = new ArrayList<Entity>();
	
	private Map map;
	
	public static float rotation = 0f;
	private HealthBar health;
	
	public boolean paused = false;

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		map = new Map();
		container.setMouseCursor("images/reticle.png", 16,16);
		
		player = new Player();
		entities.add(player);
		
		for (int i = 0; i < 10; i++){
			TestEnemy enemy = new TestEnemy(player);
			entities.add(enemy);
		}
		health = new HealthBar(player);
		paused = false;
		super.enter(container, game);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		entities.clear();
		super.leave(container, game);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

			
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setAntiAlias(true);
		g.rotate(GameWindow.WINDOW_WIDTH/2, GameWindow.WINDOW_HEIGHT/2, rotation);
		map.render(g);
		for (Entity e : entities){
			e.render(g);
		}
		g.resetTransform();
		health.render(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {	
		if (!paused){
			if (gc.getInput().isKeyPressed(Keyboard.KEY_SPACE)){
				paused = true;
			}
			if (player.getHealth() <= 0){
				sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
			}
			map.update(delta);
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
			health.update();
		} else {
			if (gc.getInput().isKeyPressed(Keyboard.KEY_SPACE)){
				paused = false;
			}
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
