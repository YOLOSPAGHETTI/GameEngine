package ui;

import java.util.HashMap;

public class MenuController {
	private HashMap<Integer,Menu> menus;
	private FrameController fc;
	
	public MenuController(FrameController fc) {
		menus = ResourceLoader.setupMenus();
		this.fc = fc;
	}
	
	public HashMap<Integer,Menu> getMenus() {
    	return menus;
    }
	
	public void highlightItemCheck(int mouseX, int mouseY) {
    	Menu menu = menus.get(fc.getScreen());
    	if(menu != null) {
    		menu.setHighlighted(mouseX, mouseY);
    	}
    }
    
    public void highlightItemCheck(boolean down) {
    	Menu menu = menus.get(fc.getScreen());
    	if(menu != null) {
    		menu.setHighlighted(down);
    	}
    }
    
    public void nullifyHighlightedItem() {
    	Menu menu = menus.get(fc.getScreen());
    	if(menu != null) {
    		menu.setHighlighted(-1, -1);
    		menu.nullifyHighlightedItem();
    	}
    }
    
    public void selectHighlightedItem() {
    	Menu menu = menus.get(fc.getScreen());
    	int destination = menu.getSelectedItemDestination();
		if(destination != -1) {
			fc.setScreen(destination);
		}
		else {
			System.exit(0);
		}
    }
    
    public void checkHighlightedItem(int mouseY) {
    	Menu menu = menus.get(fc.getScreen());
    	if(menu != null) {
    		if(menu.isScrollEdgeHighlighted()) {
    			menu.moveScrollBar(mouseY);
    		}
    		else {
    			selectHighlightedItem();
    		}
    	}
    }
    
    public void selectScrollBar(boolean select, int mouseY) {
    	Menu menu = menus.get(fc.getScreen());
    	if(menu != null) {
			if(menu.isScrollBarHighlighted()) {
				menu.setScrollBarSelected(select, mouseY);
			}
    	}
    }
    
    public void goBack() {
    	Menu menu = menus.get(fc.getScreen());
    	if(menu != null) {
    		int parentId = menu.getParentId();
    		if(parentId != -1) {
    			fc.setScreen(parentId);
        	}
    	}
    }
}
