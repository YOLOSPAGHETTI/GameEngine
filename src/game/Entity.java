package game;

import java.util.ArrayList;

import actions.ActionController;
import ui.MobileSprite;
import ui.Sprite;

public class Entity {
	private ActionController controller;
	private ArrayList<Accessory> accessories = new ArrayList<Accessory>(); 
	
	public Entity(ActionController controller) {
		this.controller = controller;
	}
	
	public ActionController getController() {
		return controller;
	}
	
	public MobileSprite getSprite() {
		return controller.getCurrentSprite();
	}
	
	public void checkNextAction(long frameTime) {
		controller.checkNextAction(frameTime);
	}
	
	public ArrayList<Accessory> getAccessories() {		
		return accessories;
	}
	
	public ArrayList<Sprite> getAccessorySprites() {
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		for(Accessory accessory : accessories) {
			sprites.add(accessory.getSprite());
		}
		
		return sprites;
	}
	
	public void addAccessory(Accessory accessory) {
		accessories.add(accessory);
	}
	
	public void addAccessories(ArrayList<Accessory> accessories) {
		this.accessories.addAll(accessories);
	}
	
	public boolean isMouseOver(int mouseX, int mouseY) {
		return controller.getCurrentSprite().isMouseOver(mouseX, mouseY);
	}
	
	public void setX(int x) {
		MobileSprite sprite = (MobileSprite)getSprite();
		sprite.setX(x);
		
		for(Accessory accessory : accessories) {
			accessory.followEntity();
		}
	}
	
	public void setY(int y) {
		MobileSprite sprite = (MobileSprite)getSprite();
		sprite.setY(y);
		
		for(Accessory accessory : accessories) {
			accessory.followEntity();
		}
	}
}
