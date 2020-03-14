package ui;

public class MenuScrollBar {
	private boolean right;
	private Sprite edgeBaseSprite;
	private Sprite edgeHighlightedSprite;
	private Sprite barBaseSprite;
	private Sprite barSelectedSprite;
	private boolean selected;
	private int selectedY;
	private int maxDistance;
	
	public MenuScrollBar(String fileSourceEdgeBase, String fileSourceBarBase, String fileSourceEdgeHighlighted, 
			String fileSourceBarSelected, boolean right, int height) {
		edgeBaseSprite = new Sprite(fileSourceEdgeBase, right);
		edgeHighlightedSprite = new Sprite(fileSourceEdgeHighlighted, right);
		barBaseSprite = new Sprite(fileSourceBarBase, right, height, edgeBaseSprite.getWidth());
		barSelectedSprite = new Sprite(fileSourceBarSelected, right, height, edgeBaseSprite.getWidth());
		
		this.right = right;
		
		maxDistance = ResourceLoader.frameHeight - height;
	}
	
	Sprite getBaseBarSprite() {
		return barBaseSprite;
	}
	
	Sprite getSelectedBarSprite() {
		return barSelectedSprite;
	}
	
	Sprite getBaseEdgeSprite() {
		return edgeBaseSprite;
	}
	
	Sprite getHighlightedEdgeSprite() {
		return edgeHighlightedSprite;
	}
	
	boolean getRight() {
		return right;
	}
	
	boolean isMouseOverEdge(int mouseX, int mouseY) {
		return isMouseOver(mouseX, mouseY, edgeBaseSprite.getX(), edgeBaseSprite.getY(), edgeBaseSprite.getWidth(), edgeBaseSprite.getHeight());
	}
	
	boolean isMouseOverBar(int mouseX, int mouseY) {
		return isMouseOver(mouseX, mouseY, barBaseSprite.getX(), barBaseSprite.getY(), barBaseSprite.getWidth(), barBaseSprite.getHeight());
	}
	
	private boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
		//System.out.println("mouseX: " + mouseX + " mouseY: " + mouseY);
		//System.out.println("startx: " + x + " starty: " + y + " endx: " + (x+width) + " endy: " + (y+height));
		if(mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
			//System.out.println(true);
			return true;
		}
		return false;
	}
	
	void setSelected(boolean selected, int mouseY) {
		this.selected = selected;
		selectedY = mouseY - barBaseSprite.getY();
	}
	
	boolean isSelected() {
		return selected;
	}
	
	int getSelectedY() {
		return selectedY;
	}
	
	int move(int mouseY) {
		if(selectedY > -1) {
			int oldY = barBaseSprite.getY();
			int newY = mouseY - selectedY;
			barBaseSprite.setY(newY);
			barSelectedSprite.setY(newY);
			return oldY - barBaseSprite.getY();
		}
		else {
			int oldY = barBaseSprite.getY();
			int newY = mouseY - barBaseSprite.getHeight()/2;
			barBaseSprite.setY(newY);
			barSelectedSprite.setY(newY);
			return oldY - barBaseSprite.getY();
		}
	}
	
	int getMaxDistance() {
		return maxDistance;
	}
}
