package actions.menu;

import actions.Action;
import ui.ViewManager;
import ui.menu.Menu;

public class GoBackAction extends Action {
	public GoBackAction() {
		super();
	}
	
	@Override
	public void execute() {
		Menu menu = (Menu)ViewManager.getView();
    	if(menu != null) {
    		int parentId = menu.getParentId();
    		if(parentId != -1) {
    			ViewManager.setView(parentId);
        	}
    	}
	}
}
