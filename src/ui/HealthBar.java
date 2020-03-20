package ui;

import java.awt.image.BufferedImage;

public class HealthBar {
	private int maxSize;
	private int currentSize;
	private Sprite positiveBarSprite;
	private Sprite negativeBarSprite;
	private int maxLength;
	private boolean player;
	
	// Player
	public HealthBar(int size, BufferedImage positiveBar, BufferedImage negativeBar) {
		maxSize = size;
		currentSize = size;
		positiveBarSprite = new Sprite(positiveBar, true, ResourceLoader.midLayer);
		negativeBarSprite = new Sprite(negativeBar, true, ResourceLoader.bottomLayer);
		maxLength = positiveBarSprite.getHeight();
		player = true;
	}
	
	// Enemy
	public HealthBar(int size, BufferedImage positiveBar, BufferedImage negativeBar, int x, int y) {
		positiveBarSprite = new Sprite(positiveBar, x, y, positiveBar.getWidth(), 10);
		negativeBarSprite = new Sprite(negativeBar, x, y, positiveBar.getWidth(), 10);
		
		maxLength = positiveBarSprite.getWidth();
		player = false;
	}
	
	Sprite getPositiveBarSprite() {
		return positiveBarSprite;
	}
	
	Sprite getNegativeBarSprite() {
		return negativeBarSprite;
	}
	
	int getSize() {
		return currentSize;
	}
	
	public void setSize(int size) {
		this.currentSize = size;
		//System.out.println("currentSize " + currentSize);
		//System.out.println("maxSize " + maxSize);
		double percentage = (double)currentSize/(double)maxSize;
		//System.out.println("percentage " + percentage);
		
		if(player) {
			int newHeight = (int)Math.round((double)maxLength*percentage);
			int newY = maxLength-newHeight;
			
			positiveBarSprite.setHeight(newHeight);
			positiveBarSprite.setY(newY);
		}
		else {
			int newWidth = (int)Math.round((double)maxLength*percentage);
			positiveBarSprite.setY(newWidth);
		}
	}
}
