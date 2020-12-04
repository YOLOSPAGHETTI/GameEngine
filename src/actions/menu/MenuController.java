package actions.menu;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import actions.Action;
import actions.ActionController;
import controls.Control;
import controls.ControlState;
import controls.Input;
import game.Entity;
import ui.ViewManager;
import ui.menu.Menu;

public class MenuController extends ActionController {
	private ArrayList<Control> defaultControls = new ArrayList<Control>();
	private final HighlightItemAction highlightItemAction = new HighlightItemAction();
	private final SelectItemAction selectItemAction = new SelectItemAction();
	private final SelectScrollBarAction selectScrollBarAction = new SelectScrollBarAction();
	private final GoBackAction goBackAction = new GoBackAction();
	
	public MenuController() {
		super();
		setupDefaultControls();
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
		
		Control highlightItemControlMouse = new Control(mouseMoveInput, this, highlightItemAction);
		defaultControls.add(highlightItemControlMouse);
		
		Control highlightItemControlDown = new Control(downKeyInput, this, highlightItemAction);
		defaultControls.add(highlightItemControlDown);
		
		Control highlightItemControlUp = new Control(upKeyInput, this, highlightItemAction);
		defaultControls.add(highlightItemControlUp);
		
		Control selectItemControlEnter = new Control(enterKeyInput, this, selectItemAction);
		defaultControls.add(selectItemControlEnter);
		
		Control selectItemControlClick = new Control(mouse1ClickInput, this, selectItemAction);
		defaultControls.add(selectItemControlClick);
		
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
}
