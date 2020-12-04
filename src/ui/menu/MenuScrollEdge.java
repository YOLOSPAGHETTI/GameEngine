package ui.menu;

import game.Accessory;
import ui.MobileSprite;
import ui.Sprite;

public class MenuScrollEdge extends Accessory {
	private MobileSprite baseSprite;
	private MobileSprite highlightedSprite;
	
	public MenuScrollEdge(MobileSprite baseSprite, MobileSprite highlightedSprite) {
		super(baseSprite);
		this.baseSprite = baseSprite;
		this.highlightedSprite = highlightedSprite;
		//baseSprite = new MobileSprite(fileSourceEdgeBase, true, z);
		//highlightedSprite = new MobileSprite(fileSourceEdgeHighlighted, true, z);
	}
	
	Sprite getBaseEdgeSprite() {
		return baseSprite;
	}
	
	Sprite getHighlightedEdgeSprite() {
		return highlightedSprite;
	}
}
