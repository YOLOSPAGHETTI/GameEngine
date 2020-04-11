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
    	super(img, 0, img.getHeight()*menuSize, z, FrameController.frameWidth, img.getHeight());
    	maxX = 0;
    	maxY = FrameController.frameHeight-height;
    }
    
    // Scroll Edge / Screen Edge Health Bar
    MobileSprite(BufferedImage img, boolean right, int z) {
    	super(img, 0, 0, z, img.getWidth(), FrameController.frameHeight);
    	if(right) {
    		x=FrameController.frameWidth-width;
    	}
    	else {
    		x=0;
    	}
    }
    
    // Scroll Bar
    MobileSprite(BufferedImage img, boolean right, int z, int height, int width) {
    	super(img, 0, 0, z, width, height);
    	maxX = FrameController.frameWidth-width;
    	maxY = FrameController.frameHeight-height;
    	if(right) {
    		x = maxX;
    	}
    	else {
    		x = 0;
    	}
    }
    
    MobileSprite(BufferedImage img, int x, int y, int z) {
    	super(img, x, y, z, img.getWidth(), img.getHeight());
	    maxX = FrameController.frameWidth-width;
		maxY = FrameController.frameHeight-height;
    }
    
    MobileSprite(BufferedImage img, int x, int y, int z, int offsetX, int offsetY) {
    	super(img, x+offsetX, y+offsetY, z, img.getWidth(), img.getHeight());
	    maxX = FrameController.frameWidth-width;
		maxY = FrameController.frameHeight-height;
    }
    
    MobileSprite(BufferedImage img, int x, int y, int z, int offsetX, int offsetY, int width, int height) {
    	super(img, x+offsetX, y+offsetY, z, width, height);
	    maxX = FrameController.frameWidth-width;
		maxY = FrameController.frameHeight-height;
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
		maxX = FrameController.frameWidth-width;
	}
	
	protected void setHeight(int height) {
		this.height = height;
		maxY = FrameController.frameHeight-height;
	}
}
