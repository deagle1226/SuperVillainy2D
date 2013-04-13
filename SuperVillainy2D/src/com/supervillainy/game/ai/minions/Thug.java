package com.supervillainy.game.ai.minions;

import java.util.ArrayList;

import com.supervillainy.game.ai.Minion;
import com.supervillainy.game.ai.minions.abilities.Ability;

public class Thug extends Minion {
	
	public Thug(int xp, ArrayList<Ability> abilities){
		super(xp, abilities);
		rank = 2;
		experienceCap = 300;
		r = 1f;
		g = 0.8f;
		b = 1f;
		a = 1f;
		//abilities.add(new ThugAbility());
	}

	@Override
	public Minion nextRank() {
		return new Thug(experience - experienceCap, abilities);
	}
}
