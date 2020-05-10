package ui;

import java.awt.image.BufferedImage;

import actions.ActionController;
import game.Entity;

public class MenuItem extends Entity {
	private String text;
	private MobileSprite baseSprite;
	private MobileSprite highlightedSprite;
	private int index;
	private int destination;
	
	public MenuItem(String text, int destination, BufferedImage baseImage, 
			BufferedImage highlightedImage, int menuSize, ActionController controller, int z) {
		super(controller);
		baseSprite = new MobileSprite(baseImage, menuSize, z);
		highlightedSprite = new MobileSprite(highlightedImage, menuSize, z);
		this.text = text;
		this.destination = destination;
		index = menuSize;
	}
	
	public MenuItem(String text, int destination, BufferedImage baseImage, 
			BufferedImage highlightedImage, int menuSize, ActionController controller, int x, int y, int z) {
		super(controller);
		baseSprite = new MobileSprite(baseImage, x, y, z, 0, 0);
		highlightedSprite = new MobileSprite(highlightedImage, x, y, z, 0, 0);
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
	
	MobileSprite getBaseSprite() {
		return baseSprite;
	}
	
	MobileSprite getHighlightedSprite() {
		return highlightedSprite;
	}
	
	int getIndex() {
		return index;
	}
	
	int getDestination() {
		return destination;
	}
}
