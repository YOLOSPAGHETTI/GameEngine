package game;

import actions.ActionController;
import ui.MobileSprite;

public class Entity {
	private ActionController controller;
	
	public Entity(ActionController controller) {
		this.controller = controller;
	}
	
	public ActionController getController() {
		return controller;
	}
	
	public MobileSprite getSprite() {
		return controller.getCurrentSprite();
	}
	
	public void checkNextAction(long frameTime) {
		controller.checkNextAction(frameTime);
	}
}
