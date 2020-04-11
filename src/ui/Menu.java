package ui;

import java.util.ArrayList;

public class Menu extends ArrayList<MenuItem> {
	private int totalHeight;
	private MenuScrollBar scrollBar;
	private MenuItem itemHighlighted;
	private boolean scrollEdgeHighlighted = false;
	private boolean scrollBarHighlighted = false;
	private int parentId = -1;
	
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
		if(scrollBar != null) {
			return scrollBar.isSelected();
		}
		return false;
	}
	
	void setScrollBarSelected(boolean selected, int mouseY) {
		if(scrollBar != null) {
			scrollBar.setSelected(selected, mouseY);
		}
	}
	
	void moveScrollBar(int mouseY) {
		int distance = scrollBar.move(mouseY);
		double percentage = (double)distance/(double)scrollBar.getMaxDistance();
		//System.out.println(percentage);
		int heightDifference = totalHeight - FrameController.frameHeight;
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
		else if(spriteY + spriteHeight > FrameController.frameHeight)  {
			int offset = -spriteY - spriteHeight + FrameController.frameHeight;
			//System.out.println("below: " + offset);
			//System.out.println("newLocation: " + (spriteY+offset) + " expectedLocation: " + (ResourceLoader.frameHeight - spriteHeight));
			relateScrollBar(offset);
			relocateItems(offset);
		}
	}
	
	private void relocateItems(int offset) {
		for(MenuItem item : this) {
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
				offset = -spriteY + FrameController.frameHeight - totalHeight;
			}
			int newLocation = spriteY+offset;
			//System.out.println("newLocation: " + newLocation);
			sprite.setYAbsolute(newLocation);
			sprite = item.getHighlightedSprite();
			sprite.setYAbsolute(newLocation);
		}
	}
	
	private void relateScrollBar(int offset) {
		int heightDifference = totalHeight - FrameController.frameHeight;
		double percentage = (double)offset/(double)heightDifference;
		int distance = (int)Math.round(percentage*(double)scrollBar.getMaxDistance());
		scrollBar.relocate(distance);
	}
	
	void setParentId(int id) {
		parentId = id;
	}
	
	int getParentId() {
		return parentId;
	}
}