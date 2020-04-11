package ui;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {
	private BufferedImage img;
	int width;
    int height;
    int x;
    int y;
    private int z;
    
    protected Sprite(BufferedImage img, int x, int y, int z) {
    	this.img = img;
    	width = img.getWidth();
    	height = img.getHeight();
    	this.x = x;
    	this.y = y;
    	this.z = z; 	
    }
    
    protected Sprite(BufferedImage img, int x, int y, int z, int width, int height) {
    	this.img = img;
    	this.width = width;
    	this.height = height;
    	this.x = x;
    	this.y = y;
    	this.z = z;
    }
	
	protected Image getImage() {
		return img;
	}
	
	public int getWidth() {
		return width;
	}
	
	protected void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	protected void setHeight(int height) {
		this.height = height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
}