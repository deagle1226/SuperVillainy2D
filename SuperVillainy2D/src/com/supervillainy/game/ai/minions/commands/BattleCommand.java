package com.supervillainy.game.ai.minions.commands;

import com.supervillainy.game.ai.tasks.BattleTask;
import com.supervillainy.game.ai.tasks.Task;
import com.supervillainy.game.ai.tasks.WaitTask;

public class BattleCommand implements Command {

	@Override
	public Task parse(Task task) {
		return new BattleTask();
	}
	
	

}
