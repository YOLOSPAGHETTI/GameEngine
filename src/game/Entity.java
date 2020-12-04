package game;

import java.util.ArrayList;

import actions.ActionController;
import ui.Sprite;

public class Entity {
	private ActionController controller;
	private ArrayList<Accessory> accessories; 
	
	public Entity(ActionController controller) {
		this.controller = controller;
	}
	
	public ActionController getController() {
		return controller;
	}
	
	public Sprite getSprite() {
		return controller.getCurrentSprite();
	}
	
	public void checkNextAction(long frameTime) {
		controller.checkNextAction(frameTime);
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
}
