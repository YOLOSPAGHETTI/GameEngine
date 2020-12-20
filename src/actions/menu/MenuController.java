package actions.menu;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import actions.ActionController;
import controls.Control;
import controls.Input;

public class MenuController extends ActionController {
	private static ArrayList<Control> defaultControls = new ArrayList<Control>();
	private final HighlightItemAction highlightItemAction = new HighlightItemAction(this);
	private final SelectItemAction selectItemAction = new SelectItemAction(this);
	private final SelectScrollBarAction selectScrollBarAction = new SelectScrollBarAction(this);
	private final GoBackAction goBackAction = new GoBackAction(this);
	private final NullifyHighlightedItemAction nullifyHighlightedItemAction = new NullifyHighlightedItemAction(this);
	
	public MenuController() {
		super();
		setupDefaultControls();
	}
	
	private void setupDefaultControls() {
		Input mouseMoveInput = new Input(MouseEvent.MOUSE_MOVED);
		Input mouse1ClickInput = new Input(MouseEvent.MOUSE_CLICKED, MouseEvent.BUTTON1);
		Input mouse1PressInput = new Input(MouseEvent.MOUSE_PRESSED, MouseEvent.BUTTON1);
		Input mouse1ReleaseInput = new Input(MouseEvent.MOUSE_RELEASED, MouseEvent.BUTTON1);
		Input mouseExitedInput = new Input(MouseEvent.MOUSE_EXITED);
		
		Input downKeyInput = new Input(KeyEvent.VK_DOWN);
		Input upKeyInput = new Input(KeyEvent.VK_UP);
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
		
		Control selectScrollBarActionControlPress = new Control(mouse1PressInput, this, selectScrollBarAction);
		defaultControls.add(selectScrollBarActionControlPress);
		
		Control selectScrollBarActionControlRelease = new Control(mouse1ReleaseInput, this, selectScrollBarAction);
		defaultControls.add(selectScrollBarActionControlRelease);
		
		Control goBackActionControl = new Control(escapeKeyInput, this, goBackAction);
		defaultControls.add(goBackActionControl);
		
		Control nullifyHighlightedItemActionControl = new Control(mouseExitedInput, this, nullifyHighlightedItemAction);
		defaultControls.add(nullifyHighlightedItemActionControl);
	}
	
	public static ArrayList<Control> getDefaultControls() {
		return defaultControls;
	}
}
