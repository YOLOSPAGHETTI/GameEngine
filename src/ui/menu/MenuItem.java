package ui.menu;

import game.Accessory;
import ui.MobileSprite;

public class MenuItem extends Accessory {
	private int index;
	private MobileSprite baseSprite;
	private MobileSprite highlightedSprite;
	
	public MenuItem(MobileSprite baseSprite, Menu menu) {
		super(baseSprite, menu);
		this.baseSprite = baseSprite;
		index = menu.getMenuItems().size();
	}
	
	public MenuItem(MobileSprite baseSprite, MobileSprite highlightedSprite, Menu menu) {
		super(baseSprite, menu);
		this.baseSprite = baseSprite;
		this.highlightedSprite = highlightedSprite;
		index = menu.getMenuItems().size();
	}
	
	public MenuItem(MobileSprite baseSprite, MobileSprite highlightedSprite, Menu menu, String text) {
		super(baseSprite, menu, text);
		this.baseSprite = baseSprite;
		this.highlightedSprite = highlightedSprite;
		index = menu.getMenuItems().size();
	}
	
	MobileSprite getBaseSprite() {
		return baseSprite;
	}
	
	MobileSprite getHighlightedSprite() {
		return highlightedSprite;
	}
	
	void highlight(boolean highlight) {
		if(highlight && highlightedSprite != null) {
			setSprite(highlightedSprite);
		}
		else {
			setSprite(baseSprite);
		}
	}
	
	int getIndex() {
		return index;
	}
}
