package ui.menu;

import java.awt.image.BufferedImage;

import ui.MobileSprite;

public class LinearMenu extends Menu {
	public LinearMenu(int id) {
		super(id);
	}
	
	public LinearMenu(int id, BufferedImage baseMenuItemImage, BufferedImage highlightedMenuItemImage, int menuLayer) {
		super(id, null, baseMenuItemImage, highlightedMenuItemImage, menuLayer);
	}
	
	public void addLinearMenuButton(String text, int destination) {
		MobileSprite baseMenuItemSprite = new MobileSprite(baseMenuItemImage, menuItems.size(), menuLayer);
		LinearMenuButton menuItem = new LinearMenuButton(text, destination, baseMenuItemSprite, 
				highlightedMenuItemImage, this, menuLayer);
		menuItems.add(menuItem);
		addAccessory(menuItem);
	}
}
