package ui.menu;

import java.awt.image.BufferedImage;

import ui.MobileSprite;

public class LinearMenuButton extends MenuItem {
	private String text;
	private MobileSprite highlightedSprite;
	private int destination;
	
	public LinearMenuButton(String text, int destination, MobileSprite baseSprite, 
			BufferedImage highlightedImage, Menu menu, int z) {
		super(baseSprite, menu);
		highlightedSprite = new MobileSprite(highlightedImage, menu.getMenuItems().size(), z);
		this.text = text;
		this.destination = destination;
	}
	
	public LinearMenuButton(String text, int destination, MobileSprite baseSprite, 
			BufferedImage highlightedImage,  Menu menu, int x, int y, int z) {
		super(baseSprite, menu);
		highlightedSprite = new MobileSprite(highlightedImage, x, y, z, 0, 0);
		this.destination = destination;
	}
	
	String getText() {
		return text;
	}
	
	MobileSprite getHighlightedSprite() {
		return highlightedSprite;
	}
	
	int getDestination() {
		return destination;
	}
}
