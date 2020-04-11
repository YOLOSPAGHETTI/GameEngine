package game;

import ui.HealthBar;
import ui.MobileSprite;

public class Entity {
	private ActionController controller;
	private int hp;
	private HealthBar healthBar;
	private boolean isDead;
	
	public Entity(ActionController controller) {
		this.controller = controller;
	}
	
	public Entity(ActionController controller, int hp) {
		this.controller = controller;
		this.hp = hp;
	}
	
	int getHp() {
		return hp;
	}
	
	public void takeDamage(int damage) {
		hp -= damage;
		if(hp < 0) {
			hp = 0;
		}
		healthBar.setSize(hp);
		if(hp == 0) {
			isDead = true;
		}
	}
	
	void heal(int health) {
		hp += health;
		healthBar.setSize(hp);
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public void setHealthBar(HealthBar healthBar) {
		this.healthBar = healthBar;
	}
	
	public HealthBar getHealthBar() {
		return healthBar;
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
	
	public void queueAction(Action action) {
		controller.queue(action);
	}
	
	public void cancelAction(Action action) {
		controller.unqueue(action);
		if(controller.getCurrentAction() == action) {
			controller.runNextAction();
		}
	}
}
