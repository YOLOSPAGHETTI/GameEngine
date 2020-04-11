package controls;

public class Input {		
	private Character characterInput;
	
	private int mouseInput = -1;
	private int mouseButton = -1;
	
	public Input(char c) {
		characterInput = c;
	}
	
	public Input(int mouseInput) {
		this.mouseInput = mouseInput;
	}
	
	public Input(int mouseInput, int mouseButton) {
		this.mouseInput = mouseInput;
		this.mouseButton = mouseButton;
	}
	
	public Character getCharacterInput() {
		return characterInput;
	}
	
	public int getMouseInput() {
		return mouseInput;
	}
	
	public int getMouseButton() {
		return mouseButton;
	}
}
