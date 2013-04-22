package com.supervillainy.game.gui;

import org.newdawn.slick.geom.Rectangle;

import com.supervillainy.game.entity.Player;

public class HealthBar extends StatBar {
	
	private Player p;

	public HealthBar(Player p) {
		super(p.getHealth(), p.getHealth(), 10, 10, 1000, 20);
		this.p = p;
	}
	
	public void update() {
		current = p.getHealth();
		((Rectangle) currentBar).setWidth(maxBar.getWidth() * (current/max));
	}

}
