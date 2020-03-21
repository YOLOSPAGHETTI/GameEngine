package game;

import ui.HealthBar;

public class Entity {
	private int hp;
	private HealthBar healthBar;
	private boolean isDead;
	ActionController controller;
	
	public Entity(int hp) {
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
	
	boolean isDead() {
		return isDead;
	}
	
	void setHealthBar(HealthBar healthBar) {
		this.healthBar = healthBar;
	}
	
	public HealthBar getHealthBar() {
		return healthBar;
	}
}
