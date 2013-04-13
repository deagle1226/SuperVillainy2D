package com.supervillainy.game;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.supervillainy.game.gui.Button;

public abstract class MenuState extends BasicGameState {
	
	protected ArrayList<Button> buttons;
	private TrueTypeFont font;
	
	public MenuState(){
		buttons = new ArrayList<Button>();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {	
		font = new TrueTypeFont(new Font("Oswald", Font.PLAIN, 18), true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setFont(font);
		for (Button b : buttons){
			b.render(g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		for (Button b : buttons){
			b.update(gc, sbg, delta);
		}
	}

}
