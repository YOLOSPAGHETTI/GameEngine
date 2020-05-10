package actions;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import controls.Control;
import controls.ControlState;
import controls.Input;
import ui.ViewManager;
import ui.Menu;

public class MenuController extends ActionController {
	private HashMap<Integer, Menu> menus;
	
	private ArrayList<Control> defaultControls = new ArrayList<Control>();
	private final Action highlightItemCheckAction = new Action(MenuController.class, this, "highlightItemCheck");
	private final Action nullifyHighlightedItemAction = new Action(MenuController.class, this, "nullifyHighlightedItem");
	private final Action selectHighlightedItemAction = new Action(MenuController.class, this, "selectHighlightedItem");
	private final Action checkHighlightedItemAction = new Action(MenuController.class, this, "checkHighlightedItem");
	private final Action selectScrollBarAction = new Action(MenuController.class, this, "selectScrollBar");
	private final Action goBackAction = new Action(MenuController.class, this, "goBack");
	
	public MenuController() {
		super();
		setupDefaultControls();
	}
	
	public MenuController(HashMap<Integer, Menu> menus) {
		super();
		this.menus = menus;
		setupDefaultControls();
	}
	
	public void addMenu(Menu menu) {
		menus.put(menu.getId(), menu);
	}
	
	public HashMap<Integer, Menu> getMenus() {
    	return menus;
    }
	
	private void setupDefaultControls() {
		Input mouseMoveInput = new Input(MouseEvent.MOUSE_MOVED);
		Input mouse1ClickInput = new Input(MouseEvent.MOUSE_CLICKED, MouseEvent.BUTTON1);
		Input mouse1PressInput = new Input(MouseEvent.MOUSE_PRESSED, MouseEvent.BUTTON1);
		Input mouse1ReleaseInput = new Input(MouseEvent.MOUSE_RELEASED, MouseEvent.BUTTON1);
		
		Input downKeyInput = new Input(KeyEvent.VK_DOWN);
		Input upKeyInput = new Input(KeyEvent.VK_DOWN);
		Input enterKeyInput = new Input(KeyEvent.VK_ENTER);
		Input escapeKeyInput = new Input(KeyEvent.VK_ESCAPE);
		
		Control highlightItemCheckControl1 = new Control(mouseMoveInput, this, highlightItemCheckAction);
		defaultControls.add(highlightItemCheckControl1);
		
		Control highlightItemCheckControl2 = new Control(downKeyInput, this, highlightItemCheckAction);
		defaultControls.add(highlightItemCheckControl2);
		
		Control highlightItemCheckControl3 = new Control(upKeyInput, this, highlightItemCheckAction);
		defaultControls.add(highlightItemCheckControl3);
		
		Control nullifyHighlightedItemActionControl = new Control(mouseMoveInput, this, nullifyHighlightedItemAction);
		defaultControls.add(nullifyHighlightedItemActionControl);
		
		Control selectHighlightedItemActionControl = new Control(enterKeyInput, this, selectHighlightedItemAction);
		defaultControls.add(selectHighlightedItemActionControl);
		
		Control checkHighlightedItemActionControl = new Control(mouse1ClickInput, this, checkHighlightedItemAction);
		defaultControls.add(checkHighlightedItemActionControl);
		
		Control selectScrollBarActionControl1 = new Control(mouse1PressInput, this, selectScrollBarAction);
		defaultControls.add(selectScrollBarActionControl1);
		
		Control selectScrollBarActionControl2 = new Control(mouse1ReleaseInput, this, selectScrollBarAction);
		defaultControls.add(selectScrollBarActionControl2);
		
		Control goBackActionControl = new Control(escapeKeyInput, this, goBackAction);
		defaultControls.add(goBackActionControl);
	}
	
	public ArrayList<Control> getDefaultControls() {
		return defaultControls;
	}
	
	public void highlightItemCheck(ControlState controlState) {
		Menu menu = menus.get(ViewManager.getViewId());
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
    
    public void nullifyHighlightedItem(ControlState controlState) {
    	Menu menu = menus.get(ViewManager.getViewId());
    	if(menu != null) {
    		menu.setHighlighted(-1, -1);
    		menu.nullifyHighlightedItem();
    	}
    }
    
    public void selectHighlightedItem(ControlState controlState) {
    	Menu menu = menus.get(ViewManager.getViewId());
    	int destination = menu.getSelectedItemDestination();
		if(destination != -1) {
			ViewManager.setView(destination);
		}
		else {
			System.exit(0);
		}
    }
    
    public void checkHighlightedItem(ControlState controlState) {
    	Menu menu = menus.get(ViewManager.getViewId());
    	if(menu != null) {
    		int mouseY = controlState.getMouseY();
    		if(menu.isScrollEdgeHighlighted()) {
    			menu.moveScrollBar(mouseY);
    		}
    		else {
    			selectHighlightedItem(controlState);
    		}
    	}
    }
    
    public void selectScrollBar(ControlState controlState) {
    	Menu menu = menus.get(ViewManager.getViewId());
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
    
    public void goBack(ControlState controlState) {
    	Menu menu = menus.get(ViewManager.getViewId());
    	if(menu != null) {
    		int parentId = menu.getParentId();
    		if(parentId != -1) {
    			ViewManager.setView(parentId);
        	}
    	}
    }
}
