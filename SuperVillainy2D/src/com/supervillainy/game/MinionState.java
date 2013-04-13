package com.supervillainy.game;

import java.awt.Font;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.supervillainy.game.ai.Minion;
import com.supervillainy.game.ai.Squad;
import com.supervillainy.game.ai.SquadManager;
import com.supervillainy.game.ai.minions.Goon;
import com.supervillainy.game.ai.minions.commands.BattleCommand;

public class MinionState extends BasicGameState {
		
	private SquadManager squads;
	private TrueTypeFont font;
	private Minion selected;
	private boolean moving = false;
	private Squad origin;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		font = new TrueTypeFont(new Font("Oswald", Font.PLAIN, 18), true);
		squads = new SquadManager();
		Squad s = new Squad();
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		squads.add(s);
		s = new Squad();
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		squads.add(s);
		s = new Squad();
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		squads.add(s);
		s = new Squad();
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		squads.add(s);
		s = new Squad();
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		squads.add(s);
		s = new Squad();
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		squads.add(s);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setFont(font);
		squads.render(gc, sbg, g, 10, 100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		updateKeys(gc);
		squads.update(gc, sbg, delta);
	}
	
	public void updateKeys(GameContainer gc){
		if (gc.getInput().isKeyPressed(Keyboard.KEY_RIGHT)){
			squads.next();
		} else if(gc.getInput().isKeyPressed(Keyboard.KEY_LEFT)){
			squads.prev();
		} else if (gc.getInput().isKeyPressed(Keyboard.KEY_UP)){
			squads.selected().prev();
		} else if(gc.getInput().isKeyPressed(Keyboard.KEY_DOWN)){
			squads.selected().next();
		} else if (gc.getInput().isKeyPressed(Keyboard.KEY_1)){
			squads.selected().command(new BattleCommand());
		} else if (gc.getInput().isKeyPressed(Keyboard.KEY_RETURN)){
			if (!moving){
				moving = true;
				selected = squads.selected().selected();
				origin = squads.selected();
			} else {
				moving = false;
				squads.moveMinion(selected, origin, squads.selected());
			}
		}
	}

	@Override
	public int getID() {
		return 1;
	}

}
