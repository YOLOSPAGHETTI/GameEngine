package ui.menu;

import ui.MobileSprite;

public class LinearMenuButton extends MenuItem {
	private int destination;
	
	public LinearMenuButton(String text, int destination, MobileSprite baseSprite, 
			MobileSprite highlightedSprite, Menu menu, int z) {
		super(baseSprite, highlightedSprite, menu, text);
		this.destination = destination;
	}
	
	public LinearMenuButton(String text, int destination, MobileSprite baseSprite, 
			MobileSprite highlightedSprite,  Menu menu) {
		super(baseSprite, highlightedSprite, menu);
		this.destination = destination;
	}
	
	int getDestination() {
		return destination;
	}
}
