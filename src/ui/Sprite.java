package ui;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {
	private int maxX;
	private int maxY;
	private BufferedImage img;
	private int width;
    private int height;
    private int x;
    private int y;
    private int z;
    
    // Menu Item
    protected Sprite(BufferedImage img, int menuSize) {
    	this.img = img;
    	width = ResourceLoader.frameWidth;
    	height = img.getHeight();
    	maxX = ResourceLoader.frameWidth-width;
    	maxY = ResourceLoader.frameHeight-height;
    	x = 0;
    	y = height * menuSize;
    }
    
    // Scroll Edge
    protected Sprite(BufferedImage img, boolean right) {
    	this.img = img;
    	width = img.getWidth();
    	height = ResourceLoader.frameHeight;
    	maxX = ResourceLoader.frameWidth-width;
    	maxY = ResourceLoader.frameHeight-height;
    	if(right) {
    		x=maxX;
    	}
    	else {
    		x=0;
    	}
    	y = 0;
    }
    
    // Scroll Bar
    protected Sprite(BufferedImage img, boolean right, int height, int width) {
    	this.img = img;
    	this.height = height;
    	this.width = width;
    	maxX = ResourceLoader.frameWidth-width;
    	maxY = ResourceLoader.frameHeight-height;
    	if(right) {
    		x = maxX;
    	}
    	else {
    		x = 0;
    	}
    	y = 0;
    }
    
    protected Sprite(BufferedImage img, int x, int y) {
    	this.img = img;
    	width = img.getWidth();
    	height = img.getHeight();
    	this.x = x;
    	this.y = y;
    	maxX = ResourceLoader.frameWidth-width;
    	maxY = ResourceLoader.frameHeight-height;
    }
    
    protected Sprite(BufferedImage img, int x, int y, int width, int height) {
    	this(img, x, y);
    	
    	this.width = width;
    	this.height = height;
    	maxX = ResourceLoader.frameWidth-width;
    	maxY = ResourceLoader.frameHeight-height;
    }
	
	protected Image getImage() {
		return img;
	}
	
	protected int getWidth() {
		return width;
	}
	
	protected void setWidth(int width) {
		this.width = width;
		maxX = ResourceLoader.frameWidth-width;
	}
	
	protected int getHeight() {
		return height;
	}
	
	protected void setHeight(int height) {
		this.height = height;
		maxY = ResourceLoader.frameHeight-height;
	}
	
	protected int getX() {
		return x;
	}
	
	protected void setX(int x) {
		this.x = fixX(x);
	}
	
	protected void setXAbsolute(int x) {
		this.x = x;
	}
	
	protected int getY() {
		return y;
	}
	
	protected void setY(int y) {
		this.y = fixY(y);
	}
	
	protected void setYAbsolute(int y) {
		this.y = y;
	}
	
	private int fixX(int x) {
		if(x < 0) {
			return 0;
		}
		else if(x > maxX) {
			return maxX;
		}
		return x;
	}
	
	private int fixY(int y) {
		if(y < 0) {
			return 0;
		}
		else if(y > maxY) {
			return maxY;
		}
		return y;
	}
}