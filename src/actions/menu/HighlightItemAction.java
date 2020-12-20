package actions.menu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import actions.Action;
import ui.ViewManager;
import ui.menu.Menu;

public class HighlightItemAction extends Action {
	public HighlightItemAction(MenuController mc) {
		super(mc);
	}
	
	@Override
	public void run() {
		Menu menu = (Menu)ViewManager.getView().getEntities().get(0);
    	if(menu != null) {
    		int mouseX = controlState.getMouseX();
    		int mouseY = controlState.getMouseY();
    		ArrayList<Integer> keysDown = controlState.getKeysDown();
    		
    		if(keysDown.size() > 0) {
				for(Integer key : keysDown) {
					if(key.equals(KeyEvent.VK_DOWN)) {
						menu.setHighlighted(true);
					}
					else if(key.equals(KeyEvent.VK_UP)) {
						menu.setHighlighted(false);
					}
				}
    		}
			else if(mouseX != -1 && mouseY != -1) {
    			menu.setHighlighted(mouseX, mouseY);
    		}
    	}
	}
}
