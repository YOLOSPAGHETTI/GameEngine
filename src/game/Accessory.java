package game;

import ui.MobileSprite;
import ui.Sprite;

public class Accessory {
	private Entity entity;
	private boolean anchored;
	private MobileSprite sprite;
	
	private int offsetX;
	private int offsetY;
	
	public Accessory(MobileSprite sprite, Entity entity) {
		this.entity = entity;
		this.anchored = true;
		
		offsetX = sprite.getX() - entity.getSprite().getX();
		offsetX = sprite.getY() - entity.getSprite().getY();
	}
	
	public Accessory(MobileSprite sprite) {
		this.anchored = false;
	}
	
	public Sprite getSprite() {
		followEntity();
		return sprite;
	}
	
	public void setSprite(MobileSprite sprite) {
		this.sprite = sprite;
	}
	
	private void followEntity() {
		if(anchored) {
			int entityX = entity.getSprite().getX();
			int entityY = entity.getSprite().getX();
			sprite.setX(entityX + offsetX);
			sprite.setY(entityY + offsetY);
		}
	}
	
	public boolean isMouseOver(int mouseX, int mouseY) {
		followEntity();
		return sprite.isMouseOver(mouseX, mouseY);
	}
}
