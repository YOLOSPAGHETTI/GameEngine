package controls;

import java.util.ArrayList;

public class ControlState {
	private int mouseX = -1;
	private int mouseY = -1;
	private boolean mouseButtonDown;
	private int lastMouseButtonPressed = -1;
	private ArrayList<Integer> keysDown;
	
	public ControlState(int mouseX, int mouseY, boolean mouseButtonDown, int lastMouseButtonPressed, ArrayList<Integer> keysDown) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseButtonDown = mouseButtonDown;
		this.lastMouseButtonPressed = lastMouseButtonPressed;
		this.keysDown = keysDown;
	}
	
	void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	
	void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	
	void setMouseButtonDown(boolean mouseButtonDown) {
		this.mouseButtonDown = mouseButtonDown;
	}
	
	void setLastMouseButtonPressed(int lastMouseButtonPressed) {
		this.lastMouseButtonPressed = lastMouseButtonPressed;
	}
	
	void setKeysDown(ArrayList<Integer> keysDown) {
		this.keysDown = keysDown;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public boolean isMouseButtonDown() {
		return mouseButtonDown;
	}
	
	public int getLastMouseButtonPressed() {
		return lastMouseButtonPressed;
	}
	
	public ArrayList<Integer> getKeysDown() {
		return keysDown;
	}
}
