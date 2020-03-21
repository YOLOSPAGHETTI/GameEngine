package ui;

import java.awt.image.BufferedImage;

public class HealthBar {
	public static final int overheadType = 0;
	public static final int screenEdgeType = 1;
	
	private int maxSize;
	private int currentSize;
	private Sprite positiveBarSprite;
	private Sprite negativeBarSprite;
	private int maxLength;
	private int type;
	
	// Player
	public HealthBar(int size, BufferedImage positiveBar, BufferedImage negativeBar) {
		maxSize = size;
		currentSize = size;
		positiveBarSprite = new Sprite(positiveBar, true, ResourceLoader.uiLayerTop);
		negativeBarSprite = new Sprite(negativeBar, true, ResourceLoader.uiLayerMid);
		maxLength = positiveBarSprite.getHeight();
		type = screenEdgeType;
	}
	
	// Enemy
	public HealthBar(int size, BufferedImage positiveBar, BufferedImage negativeBar, int x, int y, int width) {
		maxSize = size;
		currentSize = size;
		positiveBarSprite = new Sprite(positiveBar, x, y, ResourceLoader.uiLayerTop, width, 10);
		negativeBarSprite = new Sprite(negativeBar, x, y, ResourceLoader.uiLayerMid, width, 10);
		
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
}
