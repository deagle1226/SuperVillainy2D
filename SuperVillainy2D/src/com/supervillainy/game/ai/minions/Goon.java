package com.supervillainy.game.ai.minions;

import com.supervillainy.game.ai.Minion;

public class Goon extends Minion {
	
	public Goon(){
		super();
		rank = 1;
		experienceCap = 100;
		r = 1f;
		g = 1f;
		b = 1f;
		a = 1f;
	}

	@Override
	public Minion nextRank() {
		return new Thug(experience - experienceCap, abilities);
	}

}
