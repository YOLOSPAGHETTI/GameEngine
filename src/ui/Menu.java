package ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import controls.Control;
import game.Entity;

public class Menu extends View {
	private static final long serialVersionUID = 1L;
	private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	private int totalHeight;
	private MenuScrollBar scrollBar;
	private MenuItem itemHighlighted;
	private boolean scrollEdgeHighlighted = false;
	private boolean scrollBarHighlighted = false;
	private int parentId = -1;
	
	private BufferedImage baseMenuItemImage;
	private BufferedImage highlightedMenuItemImage;
	private int menuLayer;
	
	public Menu(int id) {
		super(id, new ArrayList<Entity>(), new ArrayList<Control>());
	}
	
	public Menu(int id, BufferedImage baseMenuItemImage, BufferedImage highlightedMenuItemImage, int menuLayer) {
		super(id, new ArrayList<Entity>(), new ArrayList<Control>());
		
		this.baseMenuItemImage = baseMenuItemImage;
		this.highlightedMenuItemImage = highlightedMenuItemImage;
		this.menuLayer = menuLayer;
	}
	
	public Menu(int id, ArrayList<Entity> entities, ArrayList<Control> controls) {
		super(id, entities, controls);
	}
	
	public Menu(int id, ArrayList<Entity> entities, ArrayList<Control> controls, 
			ArrayList<MenuItem> menuItems) {
		super(id, entities, controls);
		this.menuItems.addAll(menuItems);
	}
	
	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
		addEntity(menuItem);
	}
	
	public void addMenuItem(String text, int destination) {
		MenuItem menuItem = new MenuItem(text, destination, baseMenuItemImage, highlightedMenuItemImage, 
				menuItems.size(), menuLayer);
		menuItems.add(menuItem);
	}
	
	public void addMenuItems(ArrayList<MenuItem> menuItems) {
		menuItems.addAll(menuItems);
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
    		
    		MenuScrollBar scrollBar = new MenuScrollBar(scrollEdgeBaseImage, scrollBarBaseImage, scrollEdgeHighlightedImage, 
    				scrollBarSelectedImage, true, layer, scrollBarHeight);
    		this.scrollBar = scrollBar;
    		
    		int offset = scrollBar.getBaseEdgeSprite().getWidth();
    		for(MenuItem menuItem : menuItems) {
        		Sprite sprite = menuItem.getBaseSprite();
        		sprite.setWidth(sprite.getWidth() - offset);
        		sprite = menuItem.getHighlightedSprite();
        		sprite.setWidth(sprite.getWidth() - offset);
        	}
    	}
    }
	
	MenuScrollBar getScrollBar() {
		return scrollBar;
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
			if(scrollBar.isMouseOverEdge(mouseX, mouseY)) {
				nullifyHighlightedItem();
				scrollEdgeHighlighted = true;
				if(scrollBar.isMouseOverBar(mouseX, mouseY)) {
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
	
	public int getSelectedItemDestination() {
		if(itemHighlighted != null) {
			return itemHighlighted.getDestination();
		}
		return -1;
	}
	
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
			sprite = item.getHighlightedSprite();
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