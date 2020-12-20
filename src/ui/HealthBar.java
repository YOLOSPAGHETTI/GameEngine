package ui;

import java.awt.image.BufferedImage;

import game.Accessory;
import game.LivingEntity;

public class HealthBar {
	public static final int overheadType = 0;
	public static final int screenEdgeType = 1;
	
	private int maxSize;
	private int currentSize;
	private MobileSprite positiveBarSprite;
	private MobileSprite negativeBarSprite;
	private int maxLength;
	private int type;
	
	private Accessory positiveBarAccessory;
	private Accessory negativeBarAccessory;
	
	// Screen Edge Type
	public HealthBar(int size, BufferedImage positiveBar, BufferedImage negativeBar, int z) {
		maxSize = size;
		currentSize = size;
		positiveBarSprite = new MobileSprite(positiveBar, true, z);
		negativeBarSprite = new MobileSprite(negativeBar, true, z-1);
		maxLength = positiveBarSprite.getHeight();
		type = screenEdgeType;
	}
	
	// Overhead Type
	public HealthBar(int size, BufferedImage positiveBar, BufferedImage negativeBar, int x, int y, int z, int offsetX, int offsetY, int width, int height) {
		maxSize = size;
		currentSize = size;
		positiveBarSprite = new MobileSprite(positiveBar, x, y, z, offsetX, offsetY, width, height);
		negativeBarSprite = new MobileSprite(negativeBar, x, y, z-1, offsetX, offsetY, width, height);
		
		maxLength = positiveBarSprite.getWidth();
		type = overheadType;
	}
	
	public void attach(LivingEntity entity) {
		if(type == overheadType) {
			positiveBarAccessory = new Accessory(positiveBarSprite, entity);
			negativeBarAccessory = new Accessory(negativeBarSprite, entity);
		}
		else {
			positiveBarAccessory = new Accessory(positiveBarSprite);
			negativeBarAccessory = new Accessory(negativeBarSprite);
		}
		
		entity.addAccessory(positiveBarAccessory);
		entity.addAccessory(negativeBarAccessory);
		
		entity.setHealthBar(this);
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
