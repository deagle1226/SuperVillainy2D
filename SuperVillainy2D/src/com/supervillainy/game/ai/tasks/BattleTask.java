package com.supervillainy.game.ai.tasks;

public class BattleTask implements Task {
	
	public static final String NAME = "Fighting";
	
	private int count;
	
	private boolean active = true;

	@Override
	public void update() {
		count++;
		if (count > 300){
			active = false;
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getReward() {
		return 60;
	}

}
