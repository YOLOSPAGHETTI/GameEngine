package game;

public class Entity {
	private int hp;
	private boolean isDead;
	
	public int getHp() {
		return hp;
	}
	
	public void takeDamage(int damage) {
		hp -= damage;
		if(hp <= 0) {
			isDead = true;
		}
	}
	
	public void heal(int health) {
		hp += health;
	}
	
	public boolean isDead() {
		return isDead;
	}
}
