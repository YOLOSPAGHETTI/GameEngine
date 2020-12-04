package actions.menu;

import java.awt.event.MouseEvent;

import actions.Action;
import ui.ViewManager;
import ui.menu.Menu;

public class SelectScrollBarAction extends Action {	
	@Override
	public void execute() {
		Menu menu = (Menu)ViewManager.getView();
    	if(menu != null) {
			if(menu.isScrollBarHighlighted()) {
				boolean mouseDown = controlState.isMouseButtonDown();
				int mouseButton = controlState.getLastMouseButtonPressed();
				int mouseY = controlState.getMouseY();
				if(mouseButton == MouseEvent.BUTTON1) {
					menu.setScrollBarSelected(mouseDown, mouseY);
				}
			}
    	}
	}
}
