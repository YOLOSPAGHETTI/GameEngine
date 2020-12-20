package game;

import ui.MobileSprite;
import ui.Sprite;

public class Accessory {
	private Entity entity;
	private String text = "";
	private boolean anchored;
	private MobileSprite sprite;
	
	private int offsetX;
	private int offsetY;
	
	public Accessory(MobileSprite sprite, Entity entity, String text) {
		this(sprite, entity);
		this.text = text;
	}
	
	public Accessory(MobileSprite sprite, Entity entity) {
		this.entity = entity;
		this.anchored = true;
		this.sprite = sprite;
		
		offsetX = sprite.getX() - entity.getSprite().getX();
		offsetY = sprite.getY() - entity.getSprite().getY();
	}
	
	public Accessory(MobileSprite sprite) {
		this.anchored = false;
		this.sprite = sprite;
	}
	
	public Sprite getSprite() {
		followEntity();
		return sprite;
	}
	
	public void setSprite(MobileSprite sprite) {
		this.sprite = sprite;
	}
	
	protected void followEntity() {
		if(anchored) {
			int entityX = entity.getSprite().getX();
			int entityY = entity.getSprite().getY();
			sprite.setX(entityX + offsetX);
			sprite.setY(entityY + offsetY);
		}
	}
	
	public boolean isMouseOver(int mouseX, int mouseY) {
		followEntity();
		return sprite.isMouseOver(mouseX, mouseY);
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
