package com.supervillainy.game.gui.buttons;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.supervillainy.game.gui.Button;

public class StartMenuButton extends Button {
	
	public static final float height = 50f;
	public static final float width = 100f;
	private int target;

	public StartMenuButton(String text, float x, float y, int target) {
		super(text, x, y, width, height, 0.7f, 0.7f, 0.7f, 1f);
		this.target = target;
	}

	@Override
	public void mouseIn() {
		a = 0.7f;
	}

	@Override
	public void action(StateBasedGame sbg) {
		sbg.enterState(target, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public void mouseOut() {
		a = 1f;
	}

}
