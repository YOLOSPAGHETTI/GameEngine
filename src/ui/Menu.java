package ui;

import java.util.ArrayList;

public class Menu extends ArrayList<MenuItem> {
	private int totalHeight;
	private MenuScrollBar scrollBar;
	private MenuItem itemHighlighted;
	private boolean scrollEdgeHighlighted = false;
	private boolean scrollBarHighlighted = false;
	
	void setTotalHeight(int totalHeight) {
		this.totalHeight = totalHeight;
	}
	
	void setScrollBar(MenuScrollBar scrollBar) {
		this.scrollBar = scrollBar;
	}
	
	MenuScrollBar getScrollBar() {
		return scrollBar;
	}
	
	void setHighlighted(boolean down) {
		int size = this.size();
		if(down) {
			if(itemHighlighted == null || itemHighlighted.getIndex() == size-1) {
				itemHighlighted = this.get(0);
				fixHighlightedOutOfFrame();
			}
			else {
				itemHighlighted = this.get(itemHighlighted.getIndex()+1);
				fixHighlightedOutOfFrame();
			}
		}
		else {
			if(itemHighlighted == null || itemHighlighted.getIndex() == 0) {
				itemHighlighted = this.get(size-1);
				fixHighlightedOutOfFrame();
			}
			else {
				itemHighlighted = this.get(itemHighlighted.getIndex()-1);
				fixHighlightedOutOfFrame();
			}
		}
	}
	
	void setHighlighted(int mouseX, int mouseY) {
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
		for(MenuItem menuItem : this) {
			if(menuItem.isMouseOver(mouseX, mouseY)) {
				itemHighlighted = menuItem;
				scrollEdgeHighlighted = false;
				scrollBarHighlighted = false;
				scrollBar.setSelected(false, -1);
				break;
			}
		}
	}
	
	MenuItem getHighlighted() {
		return itemHighlighted;
	}
	
	void nullifyHighlightedItem() {
		itemHighlighted = null;
	}
	
	int getSelectedItemDestination() {
		if(itemHighlighted != null) {
			return itemHighlighted.getDestination();
		}
		return -1;
	}
	
	boolean isScrollEdgeHighlighted() {
		return scrollEdgeHighlighted;
	}
	
	boolean isScrollBarHighlighted() {
		return scrollBarHighlighted;
	}
	
	boolean isScrollBarSelected() {
		return scrollBar.isSelected();
	}
	
	void setScrollBarSelected(boolean selected, int mouseY) {
		scrollBar.setSelected(selected, mouseY);
	}
	
	void moveScrollBar(int mouseY) {
		int distance = scrollBar.move(mouseY);
		double percentage = (double)distance/(double)scrollBar.getMaxDistance();
		//System.out.println(percentage);
		int heightDifference = totalHeight - ResourceLoader.frameHeight;
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
			relocateItems(offset);
		}
		else if(spriteY + spriteHeight > ResourceLoader.frameHeight)  {
			int offset = -spriteY - spriteHeight + ResourceLoader.frameHeight;
			//System.out.println("below: " + offset);
			//System.out.println("newLocation: " + (spriteY+offset) + " expectedLocation: " + (ResourceLoader.frameHeight - spriteHeight));
			relocateItems(offset);
		}
	}
	
	private void relocateItems(int offset) {
		for(MenuItem item : this) {
			Sprite sprite = item.getBaseSprite();
			int newLocation = sprite.getY()+offset;
			sprite.setYAbsolute(newLocation);
			sprite = item.getHighlightedSprite();
			sprite.setYAbsolute(newLocation);
		}
	}
}