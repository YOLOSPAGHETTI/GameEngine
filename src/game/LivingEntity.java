package game;

import actions.ActionController;
import ui.HealthBar;

public class LivingEntity extends Entity {
	private int hp;
	private HealthBar healthBar;
	private boolean isDead;
	
	public LivingEntity(ActionController controller, int hp) {
		super(controller);
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
		if(hasHealthBar()) {
			healthBar.setSize(hp);
		}
		if(hp == 0) {
			isDead = true;
		}
	}
	
	void heal(int health) {
		hp += health;
		if(hasHealthBar()) {
			healthBar.setSize(hp);
		}
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public boolean hasHealthBar() {
		return healthBar != null;
	}
	
	public void setHealthBar(HealthBar healthBar) {
		this.healthBar = healthBar;
	}
	
	public HealthBar getHealthBar() {
		return healthBar;
	}
}
