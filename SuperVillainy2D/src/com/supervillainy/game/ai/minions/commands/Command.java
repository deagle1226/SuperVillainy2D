package com.supervillainy.game.ai.minions.commands;

import com.supervillainy.game.ai.tasks.Task;

public interface Command {
	
	public Task parse(Task task);

}
