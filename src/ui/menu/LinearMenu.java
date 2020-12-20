package ui.menu;

import java.awt.image.BufferedImage;

import actions.ActionController;
import ui.MobileSprite;

public class LinearMenu extends Menu {
	public LinearMenu(int id, ActionController controller) {
		super(id, controller);
	}
	
	public LinearMenu(int id, ActionController controller, BufferedImage baseMenuItemImage, BufferedImage highlightedMenuItemImage, int menuLayer) {
		super(id, controller, baseMenuItemImage, highlightedMenuItemImage, menuLayer);
	}
	
	public void addLinearMenuButton(String text, int destination) {
		MobileSprite baseMenuItemSprite = new MobileSprite(baseMenuItemImage, menuItems.size(), menuLayer);
		MobileSprite highlightedMenuItemSprite = new MobileSprite(highlightedMenuItemImage, menuItems.size(), menuLayer);
		LinearMenuButton menuItem = new LinearMenuButton(text, destination, baseMenuItemSprite, 
				highlightedMenuItemSprite, this, menuLayer);
		menuItems.add(menuItem);
		
		//MobileSprite menuSprite = getSprite();
		//menuSprite.setHeight(menuSprite.getHeight() + menuItem.getSprite().getHeight());
		addAccessory(menuItem);
	}
}
