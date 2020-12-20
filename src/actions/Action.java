package actions;

import controls.ControlState;

public class Action extends Thread {
	protected ControlState controlState;
	private ActionController ac;
	private Animation animation;
	private int interruptLevel;
	
	// Instant Action
	public Action(ActionController ac) {
		this.ac = ac;
		this.interruptLevel = 0;
	}
	
	public Action(ActionController ac, Animation animation) {
		this.ac = ac;
		this.animation = animation;
		this.interruptLevel = 0;
	}
	
	public Action(ActionController ac, int interruptLevel) {
		this.ac = ac;
		this.interruptLevel = interruptLevel;
	}
	
	public Action(ActionController ac, Animation animation, int interruptLevel) {
		this.ac = ac;
		this.animation = animation;
		this.interruptLevel = interruptLevel;
	}
	
	public ControlState getControlState() {
		return controlState;
	}
	
	public void setControlState(ControlState controlState) {
		this.controlState = controlState;
	}
	
	public boolean hasAnimation() {
		return animation != null;
	}
	
	public Animation getAnimation() {
		return animation;
	}
	
	public int getInterruptLevel() {
		return interruptLevel;
	}
}