package controls;

import actions.Action;
import actions.ActionController;

public class Control {
	private Input input;
	private ActionController controller;
	private Action action;
	private boolean cancelOnRelease;
	
	public Control(Input input, ActionController controller, Action action) {
		this.input = input; 
		this.controller = controller;
		this.action = action;
		this.cancelOnRelease = false;
	}
	
	public Control(Input input, ActionController controller, Action action, boolean cancelOnRelease) {
		this.input = input; 
		this.controller = controller;
		this.action = action;
		this.cancelOnRelease = cancelOnRelease;
	}
	
	public void setInput(Input input) {
		this.input = input;
	}
	
	public Input getInput() {
		return input;
	}
	
	public ActionController getController() {
		return controller;
	}
	
	public Action getAction() {
		return action;
	}
	
	public boolean shouldCancelOnRelease() {
		return cancelOnRelease;
	}
}
