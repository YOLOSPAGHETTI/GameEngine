package actions.menu;

import java.awt.event.MouseEvent;

import actions.Action;
import ui.ViewManager;
import ui.menu.Menu;

public class SelectScrollBarAction extends Action {	
	@Override
	public void run() {
		Menu menu = (Menu)ViewManager.getView().getEntities().get(0);
    	if(menu != null) {
    		boolean mouseDown = controlState.isMouseButtonDown();
    		int mouseButton = controlState.getLastMouseButtonPressed();
    		int mouseY = controlState.getMouseY();
    		if(mouseButton == MouseEvent.BUTTON1) {
	 			if(menu.isScrollBarHighlighted()) {
					menu.setScrollBarSelected(mouseDown, mouseY);
				}
	 			else if(menu.isScrollEdgeHighlighted() && mouseDown) {
	 				menu.moveScrollBar(mouseY);
	 			}
    		}
    	}
	}
}
