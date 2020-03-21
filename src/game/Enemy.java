package game;

import ui.HealthBar;
import ui.ResourceLoader;
import ui.Sprite;

public class Enemy extends Entity {
	private static int baseHealth = 15;
	private int position;
	
	protected Enemy(ActionController controller, int position) {
    	super(baseHealth);
        this.controller = controller;
        this.position = position;
        Sprite baseSprite = controller.getCurrentSprite();
        HealthBar healthBar = new HealthBar(baseHealth, ResourceLoader.healthBarPositive, ResourceLoader.healthBarNegative,
        		baseSprite.getX(), baseSprite.getY(), baseSprite.getWidth());
        setHealthBar(healthBar);
    }
	
	public ActionController getActionController() {
		return controller;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
}
