package ui.menu;

import game.Accessory;
import ui.MobileSprite;
import ui.Sprite;

public class MenuScrollEdge extends Accessory {
	private MobileSprite baseSprite;
	private MobileSprite highlightedSprite;
	
	public MenuScrollEdge(MobileSprite baseSprite, MobileSprite highlightedSprite, Menu menu) {
		super(baseSprite);
		this.baseSprite = baseSprite;
		this.highlightedSprite = highlightedSprite;
	}
	
	Sprite getBaseEdgeSprite() {
		return baseSprite;
	}
	
	Sprite getHighlightedEdgeSprite() {
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
}
