package ui;

import java.awt.image.BufferedImage;

public class MobileSprite extends Sprite {
	private int maxX;
	private int maxY;
	private int baseX;
	private int baseY;
	private int offsetX;
	private int offsetY; 
	
	// Menu Item
    MobileSprite(BufferedImage img, int menuSize, int z) {
    	super(img, 0, img.getHeight()*menuSize, z, ViewManager.frameWidth, img.getHeight());
    	maxX = 0;
    	maxY = ViewManager.frameHeight-height;
    }
    
    // Scroll Edge / Screen Edge Health Bar
    MobileSprite(BufferedImage img, boolean right, int z) {
    	super(img, 0, 0, z, img.getWidth(), ViewManager.frameHeight);
    	if(right) {
    		x=ViewManager.frameWidth-width;
    	}
    	else {
    		x=0;
    	}
    }
    
    // Scroll Bar
    MobileSprite(BufferedImage img, boolean right, int z, int height, int width) {
    	super(img, 0, 0, z, width, height);
    	maxX = ViewManager.frameWidth-width;
    	maxY = ViewManager.frameHeight-height;
    	if(right) {
    		x = maxX;
    	}
    	else {
    		x = 0;
    	}
    }
    
    MobileSprite(BufferedImage img, int x, int y, int z) {
    	super(img, x, y, z, img.getWidth(), img.getHeight());
	    maxX = ViewManager.frameWidth-width;
		maxY = ViewManager.frameHeight-height;
    }
    
    MobileSprite(BufferedImage img, int x, int y, int z, int offsetX, int offsetY) {
    	super(img, x+offsetX, y+offsetY, z, img.getWidth(), img.getHeight());
	    maxX = ViewManager.frameWidth-width;
		maxY = ViewManager.frameHeight-height;
    }
    
    MobileSprite(BufferedImage img, int x, int y, int z, int offsetX, int offsetY, int width, int height) {
    	super(img, x+offsetX, y+offsetY, z, width, height);
	    maxX = ViewManager.frameWidth-width;
		maxY = ViewManager.frameHeight-height;
    }
    
    protected void setX(int x) {
		this.x = fixX(x);
	}
	
	protected void setXAbsolute(int x) {
		this.x = x;
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
	
	protected void setWidth(int width) {
		this.width = width;
		maxX = ViewManager.frameWidth-width;
	}
	
	protected void setHeight(int height) {
		this.height = height;
		maxY = ViewManager.frameHeight-height;
	}
}
