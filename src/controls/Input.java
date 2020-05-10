package controls;

import java.util.ArrayList;

public class Input {		
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private int mouseInput = -1;
	private int mouseButton = -1;
	
	public Input(ArrayList<Integer> keys) {
		this.keys.addAll(keys);
	}
	
	public Input(int mouseInput) {
		this.mouseInput = mouseInput;
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
}
