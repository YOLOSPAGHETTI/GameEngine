package actions.menu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import actions.Action;
import ui.ViewManager;
import ui.menu.Menu;

public class HighlightItemAction extends Action {	
	@Override
	public void execute() {
		Menu menu = (Menu)ViewManager.getView();
    	if(menu != null) {
    		int mouseX = controlState.getMouseX();
    		int mouseY = controlState.getMouseY();
    		if(mouseX != -1 && mouseY != -1) {
    			menu.setHighlighted(mouseX, mouseY);
    		}
    		else {
    			ArrayList<Integer> keysDown = controlState.getKeysDown();
    			for(Integer key : keysDown) {
    				if(key.equals(KeyEvent.VK_DOWN)) {
    					menu.setHighlighted(true);
    				}
    				else if(key.equals(KeyEvent.VK_UP)) {
    					menu.setHighlighted(false);
    				}
    			}
    		}
    	}
	}
}
