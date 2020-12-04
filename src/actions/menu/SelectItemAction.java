package actions.menu;

import actions.Action;
import controls.ControlState;
import ui.ViewManager;
import ui.menu.Menu;

public class SelectItemAction extends Action {	
	@Override
	public void execute() {
		Menu menu = (Menu)ViewManager.getView();
    	if(menu != null) {
    		if(controlState.isMouseButtonDown()) {
    			int mouseY = controlState.getMouseY();
        		if(menu.isScrollEdgeHighlighted()) {
        			menu.moveScrollBar(mouseY);
        		}
        		else {
        			selectHighlightedItem(controlState);
        		}
    		}
    		else {
    			selectHighlightedItem(controlState);
    		}
    	}
	}
	
	public void selectHighlightedItem(ControlState controlState) {
    	/*Menu menu = (Menu)ViewManager.getView();
    	int destination = menu.getSelectedItemDestination();
		if(destination != -1) {
			ViewManager.setView(destination);
		}
		else {
			System.exit(0);
		}*/
    }
}
