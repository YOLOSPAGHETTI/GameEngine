package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;

public class Sprite extends Image {
	private String fileSource;
	private Image img;
	private int width;
    private int height;
    private int x;
    private int y;
    
    public Sprite(String file, int x, int y) {
    	fileSource = file;
    	this.x = x;
    	this.y = y;
    	loadImage(file);
    }
    
    public Sprite(String file, int x, int y, int width, int height) {
    	this(file, x, y);
    	
    	this.width = width;
    	this.height = height;
    }
    
    public Sprite(String file, int x, int y, double scale) {
    	fileSource = file;
    	this.x = x;
    	this.y = y;
    	loadImage(file, scale);
    }
    
	private void loadImage(String file) {
        ImageIcon ii = new ImageIcon(file);
        if (width == 0) {
        	width = ii.getIconWidth();
        }
        if (height == 0) {
        	height = ii.getIconHeight();
        }
        img = ii.getImage();
    }
	
	private void loadImage(String file, double scale) {
        ImageIcon ii = new ImageIcon(file);
        width = (int) Math.round(ii.getIconWidth() * scale);
        height = (int) Math.round(ii.getIconHeight() * scale);
        img = ii.getImage();
    }
	
	public String getFileSource() {
		return fileSource;
	}
	
	public Image getImage() {
		return img;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public static void main(String[] args) {
		//loadImage("src/img/germanic-knight.jpg", 0.2);
	}

	@Override
	public int getWidth(ImageObserver observer) {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight(ImageObserver observer) {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		// TODO Auto-generated method stub
		return null;
	}
}
