package actions.menu;

import actions.Action;
import ui.ViewManager;
import ui.menu.Menu;

public class NullifyHighlightedItemAction extends Action {
	public NullifyHighlightedItemAction(MenuController mc) {
		super(mc);
	}
	
	@Override
	public void run() {
		Menu menu = (Menu)ViewManager.getView().getEntities().get(0);
    	if(menu != null) {
    		menu.setHighlighted(-1, -1);
    		menu.nullifyHighlightedItem();
    	}
	}
}