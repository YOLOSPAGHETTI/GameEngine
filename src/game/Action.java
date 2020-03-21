package game;

import java.util.ArrayList;

import ui.Animation;
import ui.ResourceLoader;

public class Action extends Animation {
	private int damageMin;
	private int damageMax;
	private ArrayList<Integer> targets = new ArrayList<Integer>();
	private ArrayList<StatusEffect> effects = new ArrayList<StatusEffect>();
	
	public Action(int damageMin, int damageMax, ArrayList<Integer> targets, ArrayList<StatusEffect> effects, int interruptLevel, long duration) {
		super(duration, interruptLevel);
		this.damageMin = damageMin;
		this.damageMax = damageMax;
		this.targets.addAll(targets);
		this.effects.addAll(effects);
	}
	
	void dealDamage() {
		for(int target : targets) {
			if(ResourceLoader.targetIsEnemy(target)) {
				Enemy enemy = GameManager.getEnemyAtPosition(target);
				enemy.takeDamage(getAttackDamage());
			}
		}
	}
	
	private int getAttackDamage() {
		int difference = damageMax - damageMin;
		
		return damageMin + (int)Math.round(Math.random()*(double)difference);
	}
}
