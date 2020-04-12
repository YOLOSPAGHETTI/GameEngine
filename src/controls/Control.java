package controls;

import game.AnimatedAction;
import game.Entity;

public class Control {
	private Input input;
	private Entity entity;
	private AnimatedAction action;
	private boolean cancelOnRelease;
	
	public Control(Input input, Entity entity, AnimatedAction action, boolean cancelOnRelease) {
		this.input = input; 
		this.entity = entity;
		this.action = action;
		this.cancelOnRelease = cancelOnRelease;
	}
	
	public void setInput(Input input) {
		this.input = input;
	}
	
	public Input getInput() {
		return input;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public AnimatedAction getAction() {
		return action;
	}
	
	public boolean shouldCancelOnRelease() {
		return cancelOnRelease;
	}
}
