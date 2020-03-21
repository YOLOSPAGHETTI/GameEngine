package controls;

import java.util.HashMap;

import game.Action;

public class PlayerControls {
	private HashMap<Character, Action> keyboardControls;
	//private HashMap<Character, Action> keyboardControls;
	
	public PlayerControls() {
		keyboardControls = new HashMap<Character, Action>();
	}
	
	public void addKeyBoardControl(char character, Action action) {
		keyboardControls.put(character, action);
	}
	
	public Action getActionFromKeyInput(char character) {
		return keyboardControls.get(character);
	}
}
