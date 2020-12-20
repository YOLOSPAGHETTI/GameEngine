package actions;

import controls.ControlState;

public class Action extends Thread {
	protected ControlState controlState;
	private Animation animation;
	private int interruptLevel;
	
	// Instant Action
	public Action() {
		this.interruptLevel = 0;
	}
	
	public Action(Animation animation) {
		this.animation = animation;
		this.interruptLevel = 0;
	}
	
	public Action(int interruptLevel) {
		this.interruptLevel = interruptLevel;
	}
	
	public Action(Animation animation, int interruptLevel) {
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
	
	public void animationComplete() {
		// do something when the animation completes
	}
}