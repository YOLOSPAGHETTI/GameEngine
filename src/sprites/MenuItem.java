package sprites;

public class MenuItem extends Sprite {
	private boolean highlighted;
	
	public MenuItem(String fileSource, int x, int y) {
		super(fileSource, x, y);
	}
	
	public void setHighlighted(int mouseX, int mouseY) {
		if(mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
			highlighted = true;
		}
		highlighted = false;
	}
	
	public void setHighlighted(int lastHighlighted, boolean down) {
		if((lastHighlighted == id - 1 && down) || (lastHighlighted == id + 1 && !down)) {
			highlighted = true;
		}
		highlighted = false;
	}
	
	public boolean getHighlighted() {
		return highlighted;
	}
}
