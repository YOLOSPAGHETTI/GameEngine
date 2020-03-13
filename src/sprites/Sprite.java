package sprites;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private int maxX;
	private int maxY;
	private String fileSource;
	private BufferedImage img;
	private int width;
    private int height;
    private int x;
    private int y;
    private int z;
    
    // Menu Item
    public Sprite(String file, int menuSize) {
    	fileSource = file;
    	
    	loadImage();
    	
    	width = ResourceLoader.frameWidth;
    	maxX = ResourceLoader.frameWidth-width;
    	maxY = ResourceLoader.frameHeight-height;
    	x = 0;
    	y = height * menuSize;
    }
    
    // Scroll Edge
    public Sprite(String file, boolean right) {
    	fileSource = file;
    	
    	loadImage();
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
    public Sprite(String file, boolean right, int height, int width) {
    	fileSource = file;
    	
    	loadImage();
    	this.height = height;
    	this.width = width;
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
    
    public Sprite(String file, int x, int y) {
    	fileSource = file;
    	this.x = x;
    	this.y = y;
    	loadImage();
    	maxX = ResourceLoader.frameWidth-width;
    	maxY = ResourceLoader.frameHeight-height;
    }
    
    public Sprite(String file, int x, int y, int width, int height) {
    	this(file, x, y);
    	
    	this.width = width;
    	this.height = height;
    	maxX = ResourceLoader.frameWidth-width;
    	maxY = ResourceLoader.frameHeight-height;
    }
    
    public Sprite(String file, int x, int y, double scale) {
    	fileSource = file;
    	this.x = x;
    	this.y = y;
    	loadImage(scale);
    	maxX = ResourceLoader.frameWidth-width;
    	maxY = ResourceLoader.frameHeight-height;
    }
    
	private void loadImage() {
		try {
			img = ImageIO.read(new File(fileSource));
			if (width == 0) {
	        	width = img.getWidth();
	        }
	        if (height == 0) {
	        	height = img.getHeight();
	        }
		}
		catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	private void loadImage(double scale) {
		try {
			img = ImageIO.read(new File(fileSource));
			width = (int) Math.round(img.getWidth() * scale);
	        height = (int) Math.round(img.getHeight() * scale);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public String getFileSource() {
		return fileSource;
	}
	
	public Image getImage() {
		return img;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
		maxX = ResourceLoader.frameWidth-width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
		maxY = ResourceLoader.frameHeight-height;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = fixX(x);
	}
	
	public void setXAbsolute(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = fixY(y);
	}
	
	public void setYAbsolute(int y) {
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