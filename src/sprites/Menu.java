package sprites;

import java.util.ArrayList;

public class Menu extends ArrayList<MenuItem> {
	private int totalHeight;
	private MenuScrollBar scrollBar;
	private MenuItem itemHighlighted;
	private boolean scrollEdgeHighlighted = false;
	private boolean scrollBarHighlighted = false;
	private int location = 0;
	
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
			}
			else {
				itemHighlighted = this.get(itemHighlighted.getIndex()+1);
			}
		}
		else {
			if(itemHighlighted == null || itemHighlighted.getIndex() == 0) {
				itemHighlighted = this.get(size-1);
			}
			else {
				itemHighlighted = this.get(itemHighlighted.getIndex()-1);
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
		System.out.println(percentage);
		int heightDifference = totalHeight - ResourceLoader.frameHeight;
		int offset = (int)Math.round((double)location + (double)heightDifference*percentage);
		for(MenuItem item : this) {
			Sprite sprite = item.getBaseSprite();
			sprite.setYAbsolute(sprite.getY()+offset);
			sprite = item.getHighlightedSprite();
			sprite.setYAbsolute(sprite.getY()+offset);
		}
	}
}