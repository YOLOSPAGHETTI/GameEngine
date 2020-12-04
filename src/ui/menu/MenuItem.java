package ui.menu;

import game.Accessory;
import ui.MobileSprite;

public class MenuItem extends Accessory {
	private int index;
	private MobileSprite baseSprite;
	
	public MenuItem(MobileSprite baseSprite, Menu menu) {
		super(baseSprite, menu);
		this.baseSprite = baseSprite;
		index = menu.getMenuItems().size();
	}
	
	MobileSprite getBaseSprite() {
		return baseSprite;
	}
	
	int getIndex() {
		return index;
	}
}
