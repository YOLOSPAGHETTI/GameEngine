package game;

import ui.Animation;

public class AnimatedAction extends Animation {
	private static final long serialVersionUID = 1L;
	private Action action;
	
	public AnimatedAction(long duration, int interruptLevel, Action action) {
		super(duration, interruptLevel);
		this.action = action;
	}
	
	public Action getAction() {
		return action;
	}
	
	public void execute() {
		action.execute();
	}
}