package game;

import java.util.ArrayList;

import ui.Animation;

public class Action extends Animation {
	private int damageMin;
	private int damageMax;
	private int[] targets;
	private ArrayList<StatusEffect> effects;
	
	public Action(int damageMin, int damageMax, int[] targets, ArrayList<StatusEffect> effects, long duration) {
		super(duration);
		this.damageMin = damageMin;
		this.damageMax = damageMax;
		this.targets = targets;
		this.effects = effects;
	}
	
	private int getAttackDamage() {
		int difference = damageMax - damageMin;
		
		return damageMin + (int)Math.round(Math.random()*(double)difference);
	}
}
