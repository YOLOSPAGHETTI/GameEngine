package actions.menu;

import actions.Action;
import ui.ViewManager;
import ui.menu.Menu;

public class GoBackAction extends Action {	
	@Override
	public void run() {
		Menu menu = (Menu)ViewManager.getView().getEntities().get(0);
    	if(menu != null) {
    		int parentId = menu.getParentId();
    		if(parentId != -1) {
    			ViewManager.setView(parentId);
        	}
    	}
	}
}
