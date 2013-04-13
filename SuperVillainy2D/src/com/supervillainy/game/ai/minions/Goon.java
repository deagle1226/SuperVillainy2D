package com.supervillainy.game.ai.minions;

import com.supervillainy.game.ai.Minion;

public class Goon extends Minion {
	
	public Goon(){
		super();
		rank = 1;
		experienceCap = 100;
		r = 1f;
		g = 0.5f;
		b = 0.5f;
		a = 1f;
	}

	@Override
	public Minion nextRank() {
		return new Thug(experience - experienceCap, abilities);
	}

}
