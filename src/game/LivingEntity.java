package game;

import ui.HealthBar;

public class LivingEntity extends Entity {
	private int health;
	private HealthBar healthBar;
	
	public LivingEntity(int health) {
		this.health = health;
	}
	
	public void setHealthBar(HealthBar healthBar) {
		this.healthBar = healthBar;
	}
	
	public HealthBar getHealthBar() {
		return healthBar;
	}
	
	public boolean isDead() {
		return health <= 0;
	}
}
