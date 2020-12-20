package ui;

import java.awt.image.BufferedImage;

public class MobileSprite extends Sprite {
	private int maxX;
	private int maxY;
	private boolean forceOnScreen = false;
	
	public MobileSprite() {
    	super();
    	maxX = 0;
    	maxY = ViewManager.frameHeight;
    }
	
	// Menu Item
    public MobileSprite(BufferedImage img, int menuSize, int z) {
    	super(img, 0, img.getHeight()*menuSize, z, ViewManager.frameWidth, img.getHeight());
    	maxX = 0;
    	maxY = ViewManager.frameHeight-height;
    }
    
    // Scroll Edge / Screen Edge Health Bar
    public MobileSprite(BufferedImage img, boolean right, int z) {
    	super(img, 0, 0, z, img.getWidth(), ViewManager.frameHeight);
    	if(right) {
    		x=ViewManager.frameWidth-width;
    	}
    	else {
    		x=0;
    	}
    	forceOnScreen = true;
    }
    
    // Scroll Bar
    public MobileSprite(BufferedImage img, boolean right, int z, int height, int width) {
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
    
    public MobileSprite(BufferedImage img, int x, int y, int z) {
    	super(img, x, y, z, img.getWidth(), img.getHeight());
	    maxX = ViewManager.frameWidth-width;
		maxY = ViewManager.frameHeight-height;
    }
    
    public MobileSprite(BufferedImage img, int x, int y, int z, int offsetX, int offsetY) {
    	super(img, x+offsetX, y+offsetY, z, img.getWidth(), img.getHeight());
	    maxX = ViewManager.frameWidth-width;
		maxY = ViewManager.frameHeight-height;
    }
    
    public MobileSprite(BufferedImage img, int x, int y, int z, int offsetX, int offsetY, int width, int height) {
    	super(img, x+offsetX, y+offsetY, z, width, height);
	    maxX = ViewManager.frameWidth-width;
		maxY = ViewManager.frameHeight-height;
    }
    
    public void setX(int x) {
    	if(forceOnScreen) {
    		this.x = x;
    	}
    	else {
    		this.x = fixX(x);
    	}
	}
	
	public void setY(int y) {
		if(forceOnScreen) {
			this.y = y;
		}
		else {
			this.y = fixY(y);
		}
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
	
	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		maxX = ViewManager.frameWidth-width;
	}
	
	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		maxY = ViewManager.frameHeight-height;
	}
}
