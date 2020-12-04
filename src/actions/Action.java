package actions;

import controls.ControlState;

public class Action extends Animation {
	private static final long serialVersionUID = 1L;
	protected ControlState controlState;
	private ActionController ac;
	
	// Instant action
	public Action() {
		super(0, 0);
	}
	
	public Action(ActionController ac) {
		super(0, 0);
		this.ac = ac;
	}
	
	public Action(long duration, int interruptLevel) {
		super(duration, interruptLevel);
	}
	
	public ControlState getControlState() {
		return controlState;
	}
	
	public void setControlState(ControlState controlState) {
		this.controlState = controlState;
	}
	
	public void execute() {
		// Do some action
	}
}