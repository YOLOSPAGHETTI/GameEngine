package controls;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Input {		
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private int mouseInput = -1;
	private int mouseButton = -1;
	
	public Input(ArrayList<Integer> keys) {
		this.keys.addAll(keys);
	}
	
	public Input(int input) {
		if(isMouseInput(input)) {
			this.mouseInput = input;
		}
		else {
			keys.add(input);
		}
	}
	
	public Input(int mouseInput, int mouseButton) {
		this.mouseInput = mouseInput;
		this.mouseButton = mouseButton;
	}
	
	public ArrayList<Integer> getKeyInputs() {
		return keys;
	}
	
	public int getMouseInput() {
		return mouseInput;
	}
	
	public int getMouseButton() {
		return mouseButton;
	}
	
	private boolean isMouseInput(int input) {
		if(input == MouseEvent.MOUSE_CLICKED || 
				input == MouseEvent.MOUSE_DRAGGED || 
				input == MouseEvent.MOUSE_ENTERED || 
				input == MouseEvent.MOUSE_EXITED || 
				input == MouseEvent.MOUSE_FIRST || 
				input == MouseEvent.MOUSE_LAST ||
				input == MouseEvent.MOUSE_MOVED ||
				input == MouseEvent.MOUSE_PRESSED ||
				input == MouseEvent.MOUSE_RELEASED ||
				input == MouseEvent.MOUSE_WHEEL) {
			return true;
		}
		return false;
	}
}
