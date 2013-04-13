package com.supervillainy.game;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameWindow extends StateBasedGame {

	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	
	public GameWindow() {
		super("Super Villainy");
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setTargetFrameRate(60);
		gc.getGraphics().setAntiAlias(true);
		gc.getGraphics().setFont(new TrueTypeFont(new Font("Oswald", Font.PLAIN, 18), true));
		this.addState(new StartMenuState());
		this.addState(new MinionState());
		this.addState(new BattleState());
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if (gc.getInput().isKeyPressed(Keyboard.KEY_ESCAPE)){
			if (getCurrentStateID() == 0){
				System.exit(0);
			}
			enterState(0);
		}
		super.update(gc, delta);
	}

	public static void main(String[] args) throws SlickException{
		GameWindow window = new GameWindow();
		window.addState(new StartMenuState());
		
		AppGameContainer app = new AppGameContainer(new GameWindow());
		app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
		app.start();
	}
}
