package com.supervillainy.game.ai.minions.commands;

import java.util.ArrayList;

public class CommandQueue {
	
	private ArrayList<Command> commands;
	
	public CommandQueue(){
		commands = new ArrayList<Command>();
	}
	
	public int size(){
		return commands.size();
	}
	
	public Command pop(){
		Command c = commands.remove(0);
		return c;
	}
	
	public void add(Command c){
		commands.add(c);
	}
	
	public ArrayList<Command> getCommands(){
		return commands;
	}

}
