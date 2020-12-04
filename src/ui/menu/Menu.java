package ui.menu;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import actions.ActionController;
import controls.Control;
import game.Accessory;
import game.Entity;
import ui.MobileSprite;
import ui.Sprite;
import ui.ViewManager;

public class Menu extends Entity {
	private static final long serialVersionUID = 1L;
	private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	private int totalHeight;
	private MenuScrollEdge scrollEdge;
	private MenuScrollBar scrollBar;
	private MenuItem itemHighlighted;
	private boolean scrollEdgeHighlighted = false;
	private boolean scrollBarHighlighted = false;
	private int parentId = -1;
	
	private BufferedImage baseMenuItemImage;
	private BufferedImage highlightedMenuItemImage;
	private int menuLayer;
	
	public Menu(int id, ActionController controller) {
		super(controller);
	}
	
	public Menu(int id, ActionController controller, BufferedImage baseMenuItemImage, BufferedImage highlightedMenuItemImage, int menuLayer) {
		super(controller);
		
		this.baseMenuItemImage = baseMenuItemImage;
		this.highlightedMenuItemImage = highlightedMenuItemImage;
		this.menuLayer = menuLayer;
	}
	
	public Menu(int id, ActionController controller, ArrayList<MenuItem> menuItems) {
		super(controller);
		this.menuItems.addAll(menuItems);
	}
	
	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
		addAccessory(menuItem);
	}
	
	public void addMenuItem() {
		MobileSprite baseMenuItemSprite = new MobileSprite(baseMenuItemImage, menuItems.size(), menuLayer);
		MenuItem menuItem = new MenuItem(baseMenuItemSprite, this);
		menuItems.add(menuItem);
		addAccessory(menuItem);
	}
	
	public void addMenuItem(int x, int y) {
		MobileSprite baseMenuItemSprite = new MobileSprite(baseMenuItemImage, x, y, menuLayer, 0, 0);
		MenuItem menuItem = new MenuItem(baseMenuItemSprite, this);
		menuItems.add(menuItem);
		addAccessory(menuItem);
	}
	
	public void addMenuItems(ArrayList<MenuItem> menuItems) {
		menuItems.addAll(menuItems);
		for(Accessory menuItem : menuItems) {
			addAccessory(menuItem);
		}
	}
	
	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	
	public void setBaseMenuItemImage(BufferedImage image) {
		baseMenuItemImage = image;
	}
	
	public void setHighlightedMenuItemImage(BufferedImage image) {
		highlightedMenuItemImage = image;
	}
	
	public void setMenuLayer(int z) {
		menuLayer = z;
	}
	
	public void addScrollBar(BufferedImage scrollEdgeBaseImage, BufferedImage scrollBarBaseImage, 
			BufferedImage scrollEdgeHighlightedImage, BufferedImage scrollBarSelectedImage, int layer, int minScrollBarHeight) {		
    	int scrollBarHeight = -1;
    	int totalMenuHeight = 0;
    	for(MenuItem menuItem : menuItems) {
    		totalMenuHeight += menuItem.getBaseSprite().getHeight();
    	}
    	totalHeight = totalMenuHeight;
    	int frameHeight = ViewManager.frameHeight;
    	if(totalMenuHeight > frameHeight) {
    		// System.out.println(frameHeight);
    		// System.out.println(totalMenuHeight);
    		scrollBarHeight = (int)Math.round(((double)frameHeight/(double)totalMenuHeight)*(double)frameHeight);
    		// System.out.println(scrollBarHeight);
    		if(scrollBarHeight < minScrollBarHeight) {
    			scrollBarHeight = minScrollBarHeight;
    		}
    	}
    	if(scrollBarHeight != -1) {
    		MobileSprite baseScrollEdgeSprite = new MobileSprite(scrollEdgeBaseImage, true, layer);
    		MobileSprite highlightedScrollEdgeSprite = new MobileSprite(scrollEdgeHighlightedImage, true, layer);
    		scrollEdge = new MenuScrollEdge(baseScrollEdgeSprite, highlightedScrollEdgeSprite);
    		
    		MobileSprite baseScrollBarSprite = new MobileSprite(scrollBarBaseImage, true, layer, scrollBarHeight, baseScrollEdgeSprite.getWidth());
    		MobileSprite selectedScrollBarSprite = new MobileSprite(scrollBarSelectedImage, true, layer, scrollBarHeight, baseScrollEdgeSprite.getWidth());
    		scrollBar = new MenuScrollBar(baseScrollBarSprite, selectedScrollBarSprite, scrollBarHeight);
    		
    		int offset = baseScrollEdgeSprite.getWidth();
    		for(MenuItem menuItem : menuItems) {
        		Sprite sprite = menuItem.getSprite();
        		sprite.setWidth(sprite.getWidth() - offset);
        	}
    	}
    }
	
	MenuScrollBar getScrollBar() {
		return scrollBar;
	}
	
	MenuScrollEdge getScrollEdge() {
		return scrollEdge;
	}
	
	public void setHighlighted(boolean down) {
		int size = menuItems.size();
		if(down) {
			if(itemHighlighted == null || itemHighlighted.getIndex() == size-1) {
				itemHighlighted = menuItems.get(0);
				fixHighlightedOutOfFrame();
			}
			else {
				itemHighlighted = menuItems.get(itemHighlighted.getIndex()+1);
				fixHighlightedOutOfFrame();
			}
		}
		else {
			if(itemHighlighted == null || itemHighlighted.getIndex() == 0) {
				itemHighlighted = menuItems.get(size-1);
				fixHighlightedOutOfFrame();
			}
			else {
				itemHighlighted = menuItems.get(itemHighlighted.getIndex()-1);
				fixHighlightedOutOfFrame();
			}
		}
	}
	
	public void setHighlighted(int mouseX, int mouseY) {
		if(scrollBar != null) {
			if(scrollEdge.isMouseOver(mouseX, mouseY)) {
				nullifyHighlightedItem();
				scrollEdgeHighlighted = true;
				if(scrollBar.isMouseOver(mouseX, mouseY)) {
					scrollBarHighlighted = true;
				}
				else {
					scrollBarHighlighted = false;
				}
				return;
			}
			else {
				scrollEdgeHighlighted = false;
			}
		}
		for(MenuItem menuItem : menuItems) {
			if(menuItem.isMouseOver(mouseX, mouseY)) {
				itemHighlighted = menuItem;
				scrollEdgeHighlighted = false;
				scrollBarHighlighted = false;
				if(scrollBar != null) {
					scrollBar.setSelected(false, -1);
				}
				break;
			}
		}
	}
	
	MenuItem getHighlighted() {
		return itemHighlighted;
	}
	
	public void nullifyHighlightedItem() {
		itemHighlighted = null;
	}
	
	/*public int getSelectedItemDestination() {
		if(itemHighlighted != null) {
			return itemHighlighted.getDestination();
		}
		return -1;
	}*/
	
	public boolean isScrollEdgeHighlighted() {
		return scrollEdgeHighlighted;
	}
	
	public boolean isScrollBarHighlighted() {
		return scrollBarHighlighted;
	}
	
	boolean isScrollBarSelected() {
		if(scrollBar != null) {
			return scrollBar.isSelected();
		}
		return false;
	}
	
	public void setScrollBarSelected(boolean selected, int mouseY) {
		if(scrollBar != null) {
			scrollBar.setSelected(selected, mouseY);
		}
	}
	
	public void moveScrollBar(int mouseY) {
		int distance = scrollBar.move(mouseY);
		double percentage = (double)distance/(double)scrollBar.getMaxDistance();
		//System.out.println(percentage);
		int heightDifference = totalHeight - ViewManager.frameHeight;
		int offset = (int)Math.round((double)heightDifference*percentage);
		//System.out.println(offset);
		relocateItems(offset);
	}
	
	private void fixHighlightedOutOfFrame() {
		Sprite sprite = itemHighlighted.getBaseSprite();
		int spriteY = sprite.getY();
		int spriteHeight = sprite.getHeight();
		//System.out.println("spriteY: " + spriteY + " frameHeight: " + ResourceLoader.frameHeight + " spriteHeight: " + spriteHeight);
		if(spriteY < 0) {
			int offset = -spriteY;
			//System.out.println("above: " + offset);
			//System.out.println("newLocation: " + (spriteY+offset) + " expectedLocation: 0");
			relateScrollBar(offset);
			relocateItems(offset);
		}
		else if(spriteY + spriteHeight > ViewManager.frameHeight)  {
			int offset = -spriteY - spriteHeight + ViewManager.frameHeight;
			//System.out.println("below: " + offset);
			//System.out.println("newLocation: " + (spriteY+offset) + " expectedLocation: " + (ResourceLoader.frameHeight - spriteHeight));
			relateScrollBar(offset);
			relocateItems(offset);
		}
	}
	
	private void relocateItems(int offset) {
		for(MenuItem item : menuItems) {
			MobileSprite sprite = item.getBaseSprite();
			int index = item.getIndex();
			int spriteY = sprite.getY();
			Sprite scrollBarSprite = scrollBar.getBaseBarSprite();
			if(scrollBarSprite.getY() == 0 && index == 0) {
				//System.out.println("spriteY: " + spriteY);
				offset = -spriteY;
			}
			else if(scrollBarSprite.getY() == scrollBar.getMaxDistance() && index == 0) {
				//System.out.println("spriteY: " + spriteY + " totalHeight: " + totalHeight + " frameH: " + ResourceLoader.frameHeight);
				offset = -spriteY + ViewManager.frameHeight - totalHeight;
			}
			int newLocation = spriteY+offset;
			//System.out.println("newLocation: " + newLocation);
			sprite.setYAbsolute(newLocation);
		}
	}
	
	private void relateScrollBar(int offset) {
		int heightDifference = totalHeight - ViewManager.frameHeight;
		double percentage = (double)offset/(double)heightDifference;
		int distance = (int)Math.round(percentage*(double)scrollBar.getMaxDistance());
		scrollBar.relocate(distance);
	}
	
	void setParentId(int id) {
		parentId = id;
	}
	
	public int getParentId() {
		return parentId;
	}
}