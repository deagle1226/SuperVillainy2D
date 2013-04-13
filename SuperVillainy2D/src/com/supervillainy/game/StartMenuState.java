package com.supervillainy.game;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.supervillainy.game.gui.Button;
import com.supervillainy.game.gui.buttons.StartMenuButton;

public class StartMenuState extends MenuState {
	
	public StartMenuState(){
		super();
		buttons.add(new StartMenuButton("debug_ai",100, 100, 1));
		buttons.add(new StartMenuButton("debug_battle",100, 300, 2));
	}

	@Override
	public int getID() {
		return 0;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		super.update(gc, sbg, delta);
		if (gc.getInput().isKeyPressed(Keyboard.KEY_RETURN)){
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
	}

}
