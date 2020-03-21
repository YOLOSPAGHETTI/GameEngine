package ui;

import java.awt.image.BufferedImage;

public class MenuItem {
	private String text;
	private Sprite baseSprite;
	private Sprite highlightedSprite;
	private int index;
	private int destination;
	
	public MenuItem(String text, int destination, BufferedImage baseImage, BufferedImage highlightedImage, int menuSize) {
		baseSprite = new Sprite(baseImage, menuSize, ResourceLoader.uiLayerMid);
		highlightedSprite = new Sprite(highlightedImage, menuSize, ResourceLoader.uiLayerMid);
		this.text = text;
		this.destination = destination;
		index = menuSize;
	}
	
	public MenuItem(String text, int destination, BufferedImage baseImage, BufferedImage highlightedImage, int menuSize, int x, int y) {
		baseSprite = new Sprite(baseImage, x, y, ResourceLoader.uiLayerMid);
		highlightedSprite = new Sprite(highlightedImage, x, y, ResourceLoader.uiLayerMid);
		this.destination = destination;
		index = menuSize;
	}
	
	String getText() {
		return text;
	}
	
	boolean isMouseOver(int mouseX, int mouseY) {
		int x = baseSprite.getX();
		int y = baseSprite.getY();
		int width = baseSprite.getWidth();
		int height = baseSprite.getHeight();
		//System.out.println("Destination: " + destination);
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
