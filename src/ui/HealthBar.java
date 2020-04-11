package ui;

import java.awt.image.BufferedImage;

public class HealthBar {
	public static final int overheadType = 0;
	public static final int screenEdgeType = 1;
	
	private int maxSize;
	private int currentSize;
	private MobileSprite positiveBarSprite;
	private MobileSprite negativeBarSprite;
	private int maxLength;
	private int type;
	
	// Player
	public HealthBar(int size, BufferedImage positiveBar, BufferedImage negativeBar, int z) {
		maxSize = size;
		currentSize = size;
		positiveBarSprite = new MobileSprite(positiveBar, true, z);
		negativeBarSprite = new MobileSprite(negativeBar, true, z);
		maxLength = positiveBarSprite.getHeight();
		type = screenEdgeType;
	}
	
	// Enemy
	public HealthBar(int size, BufferedImage positiveBar, BufferedImage negativeBar, int x, int y, int z, int width, int height) {
		maxSize = size;
		currentSize = size;
		positiveBarSprite = new MobileSprite(positiveBar, x, y, z, 0, 0, width, height);
		negativeBarSprite = new MobileSprite(negativeBar, x, y, z, 0, 0, width, height);
		
		maxLength = positiveBarSprite.getWidth();
		type = overheadType;
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
		
		if(type == screenEdgeType) {
			int newHeight = (int)Math.round((double)maxLength*percentage);
			int newY = maxLength-newHeight;
			
			positiveBarSprite.setHeight(newHeight);
			positiveBarSprite.setY(newY);
		}
		else {
			int newWidth = (int)Math.round((double)maxLength*percentage);
			positiveBarSprite.setWidth(newWidth);
		}
	}
	
	public void reposition(int x, int y) {
		positiveBarSprite.setX(x);
		positiveBarSprite.setY(y);
		
		negativeBarSprite.setX(x);
		negativeBarSprite.setY(y);
	}
}
