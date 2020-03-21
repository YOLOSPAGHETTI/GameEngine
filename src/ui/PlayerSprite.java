package ui;

import java.awt.image.BufferedImage;

public class PlayerSprite extends Sprite {
	public PlayerSprite(BufferedImage img, int offsetX, int offsetY) {
		super(img, ResourceLoader.playerStartX+offsetX, ResourceLoader.playerStartY+offsetY, 
				ResourceLoader.entityLayerTop, ResourceLoader.playerWidth, ResourceLoader.playerHeight);
	}
}
