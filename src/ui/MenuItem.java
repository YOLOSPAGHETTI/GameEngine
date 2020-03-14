package ui;

public class MenuItem {
	private String text;
	private Sprite baseSprite;
	private Sprite highlightedSprite;
	private int index;
	private int destination;
	private int width;
	private int height;
	private int x;
	private int y;
	
	public MenuItem(String text, int destination, String fileSourceBase, String fileSourceHighlighted, int menuSize) {
		baseSprite = new Sprite(fileSourceBase, menuSize);
		highlightedSprite = new Sprite(fileSourceHighlighted, menuSize);
		this.text = text;
		this.destination = destination;
		index = menuSize;
		width = baseSprite.getWidth();
		height = baseSprite.getHeight();
		x = baseSprite.getX();
		y = baseSprite.getY();
	}
	
	public MenuItem(String text, int destination, String fileSourceBase, String fileSourceHighlighted, int menuSize, int x, int y) {
		this.x = x;
		this.y = y;
		baseSprite = new Sprite(fileSourceBase, x, y);
		highlightedSprite = new Sprite(fileSourceHighlighted, x, y);
		this.destination = destination;
		index = menuSize;
		width = baseSprite.getWidth();
		height = baseSprite.getHeight();
	}
	
	public String getText() {
		return text;
	}
	
	boolean isMouseOver(int mouseX, int mouseY) {
		//System.out.println("mouseX: " + mouseX + " mouseY: " + mouseY);
		//System.out.println("startx: " + x + " starty: " + y + " endx: " + (x+width) + " endy: " + (y+height));
		if(mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
			//System.out.println(true);
			return true;
		}
		return false;
	}
	
	Sprite getBaseSprite() {
		return baseSprite;
	}
	
	Sprite getHighlightedSprite() {
		return highlightedSprite;
	}
	
	int getIndex() {
		return index;
	}
	
	int getDestination() {
		return destination;
	}
}