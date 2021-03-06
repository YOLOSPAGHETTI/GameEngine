package actions.menu;

import actions.Action;
import controls.ControlState;
import ui.ViewManager;
import ui.menu.Menu;

public class SelectItemAction extends Action {	
	@Override
	public void run() {
		Menu menu = (Menu)ViewManager.getView().getEntities().get(0);
    	if(menu != null) {
    		selectHighlightedItem(controlState);
    	}
	}
	
	public void selectHighlightedItem(ControlState controlState) {
    	Menu menu = (Menu)ViewManager.getView().getEntities().get(0);
    	int destination = menu.getSelectedItemDestination();
		if(destination != -1) {
			System.out.println("Select new menu destination: " + destination);
			ViewManager.setView(destination);
		}
		else {
			System.out.println("Destination is -1: Exiting");
			System.exit(0);
		}
    }
}
