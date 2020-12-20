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
    
    protected Sprite() {
    	img = ImageLoader.getBlankImage();
    	width = ViewManager.frameWidth;
    	height = ViewManager.frameHeight;
    	x = 0;
    	y = 0;
    }
    
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
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
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
	
	public void overlapWith(Sprite sprite) {
		x = sprite.getX();
		y = sprite.getY();
		width = sprite.getWidth();
		height = sprite.getHeight();
	}
	
	public boolean isMouseOver(int mouseX, int mouseY) {
		//System.out.println("mouseX: " + mouseX + " mouseY: " + mouseY);
		//System.out.println("startx: " + x + " starty: " + y + " endx: " + (x+width) + " endy: " + (y+height));
		if(mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
			//System.out.println(true);
			return true;
		}
		return false;
	}
}