package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Entity;

public class FrameBuilder extends JPanel
		implements Runnable {
	private static final long serialVersionUID = 1L;
	private int maxLayer;
    private int width;
    private int height;
    private BufferedImage bgImage;
    
    private Thread animator;
    private final int frameTime = 10;

    public FrameBuilder(int maxLayer) {
    	this.maxLayer = maxLayer;
    	
        width = ViewManager.frameWidth;
        height = ViewManager.frameHeight;
    	
        buildFrame();
    }
    
    private void buildFrame() {
    	setBackground(Color.green);
        setPreferredSize(new Dimension(width, height));
    }
    
    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawFrame(g);
    }
    
    private void drawFrame(Graphics g) {
    	ArrayList<Entity> entities = ViewManager.getEntities();
    	System.out.println(entities);
    	//System.out.println(screen);
    	for(int i=1; i<=maxLayer; i++) {
	    	for(Entity entity : entities) {
	    		Sprite sprite = entity.getSprite();
	    		addSprite(g, sprite, i);
	    		ArrayList<Sprite> accessorySprites = entity.getAccessorySprites();
	    		for(Sprite accessorySprite : accessorySprites) {
	    			addSprite(g, accessorySprite, i);
	    		}
	    	}
    	}
    	Toolkit.getDefaultToolkit().sync();
    }

    private void addSprite(Graphics g, Sprite sprite, int currentLayer) {
    	int spriteX = sprite.getX();
    	int spriteY = sprite.getY();
    	int spriteZ = sprite.getZ();
    	int spriteWidth = sprite.getWidth();
    	int spriteHeight = sprite.getHeight();
    	//System.out.println("currentLayer: " + currentLayer);
    	//System.out.println("x: " + spriteX + " y: " + spriteY + " z: " + spriteZ);
    	//System.out.println("width: " + spriteWidth + " height: " + spriteHeight);
    	if(currentLayer == spriteZ) {
    		g.drawImage(sprite.getImage(), spriteX, spriteY, spriteWidth, spriteHeight, null);
    	}
	}
    
    private void addSprite(Graphics g, Sprite sprite, String text, int currentLayer) {
    	addSprite(g, sprite, currentLayer);
    	
    	int spriteX = sprite.getX();
    	int spriteY = sprite.getY();
    	int spriteWidth = sprite.getWidth();
    	int spriteHeight = sprite.getHeight();
    	
    	if(!text.equals("")) {
		    g.setFont(g.getFont().deriveFont(30f));
		    FontMetrics fm = g.getFontMetrics();
		    int textWidth = fm.stringWidth(text);
		    //int textHeight = fm.getHeight();
		    
		    g.drawString(text, spriteX + spriteWidth/2 - textWidth/2, spriteY + spriteHeight/2);
    	}
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = frameTime - timeDiff;
            
            ArrayList<Entity> entities = ViewManager.getEntities();
        	//System.out.println(screen);
        	for(int i=1; i<=maxLayer; i++) {
    	    	for(Entity entity : entities) {
    	    		if(entity != null) {
    	    			entity.checkNextAction(sleep);
    	    		}
    	    	}
        	}

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                
                JOptionPane.showMessageDialog(this, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }
}