package ui.menu;

import game.Accessory;
import ui.MobileSprite;
import ui.Sprite;
import ui.ViewManager;

public class MenuScrollBar extends Accessory {
	private MobileSprite baseSprite;
	private MobileSprite selectedSprite;
	private boolean selected;
	private int selectedY;
	private int maxDistance;
	
	public MenuScrollBar(MobileSprite baseSprite, MobileSprite selectedSprite, int height) {
		super(baseSprite);
		this.baseSprite = baseSprite;
		this.selectedSprite = selectedSprite;
		
		maxDistance = ViewManager.frameHeight - height;
	}
	
	Sprite getBaseBarSprite() {
		return baseSprite;
	}
	
	Sprite getSelectedBarSprite() {
		return selectedSprite;
	}
	
	void setSelected(boolean selected, int mouseY) {
		this.selected = selected;
		selectedY = mouseY - baseSprite.getY();
		select(selected);
	}
	
	boolean isSelected() {
		return selected;
	}
	
	int getSelectedY() {
		return selectedY;
	}
	
	int move(int y) {
		if(selectedY > -1) {
			int oldY = baseSprite.getY();
			int newY = y - selectedY;
			baseSprite.setY(newY);
			selectedSprite.setY(newY);
			return oldY - baseSprite.getY();
		}
		else {
			int oldY = baseSprite.getY();
			int newY = y - baseSprite.getHeight()/2;
			baseSprite.setY(newY);
			selectedSprite.setY(newY);
			return oldY - baseSprite.getY();
		}
	}
	
	void relocate(int distance) {
		baseSprite.setY(baseSprite.getY()-distance);
	}
	
	int getMaxDistance() {
		return maxDistance;
	}
	
	void select(boolean select) {
		if(select && selectedSprite != null) {
			setSprite(selectedSprite);
		}
		else {
			setSprite(baseSprite);
		}
	}
}
