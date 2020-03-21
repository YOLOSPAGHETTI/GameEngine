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

import game.ActionController;
import game.Enemy;
import game.Player;
import ui.MenuItem;

public class FrameBuilder extends JPanel
		implements Runnable {
	private FrameController fc;
    private int width;
    private int height;
    private BufferedImage bgImage;
    
    private Thread animator;
    private final int frameTime = 10;
    
    private MenuController mc;
    private Player player;
    private ActionController pac;
    private ArrayList<Enemy> enemies;

    public FrameBuilder(FrameController fc, MenuController mc, Player player, ArrayList<Enemy> enemies) {
    	this.fc = fc;
    	this.mc = mc;
    	this.player = player;
    	this.enemies = enemies;
    	this.pac = player.getActionController();
    	
    	bgImage = ResourceLoader.bgImage;
    	
        width = ResourceLoader.frameWidth;
        height = ResourceLoader.frameHeight;
    	
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
    	int screen = fc.getScreen();
    	//System.out.println(screen);
    	if(ResourceLoader.screenIsMenu(screen)) {
    		Menu menu = mc.getMenus().get(screen);
    		
    		if(menu != null) {
    			drawMenu(g, menu);
    		}
    	}
    	else if(screen == ResourceLoader.inGameScreen) {
    		drawGame(g);
        }
    	Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawMenu(Graphics g, Menu menu) {
		for(int i=0; i<=ResourceLoader.uiLayerTop; i++) {
    		for(MenuItem menuItem : menu) {
            	if(menuItem.equals(menu.getHighlighted())) {
            		addSprite(g, menuItem.getHighlightedSprite(), menuItem.getText(), i);
            	}
            	else {
            		addSprite(g, menuItem.getBaseSprite(), menuItem.getText(), i);
            	}
            }
    		MenuScrollBar menuScrollBar = menu.getScrollBar();
    		if(menuScrollBar != null) {
    			if(menu.isScrollEdgeHighlighted()) {
            		addSprite(g, menuScrollBar.getHighlightedEdgeSprite(), i);
            		if(menu.isScrollBarSelected()) {
            			addSprite(g, menuScrollBar.getSelectedBarSprite(), i);
            		}
            		else {
            			addSprite(g, menuScrollBar.getBaseBarSprite(), i);
            		}
            	}
            	else {
            		addSprite(g, menuScrollBar.getBaseEdgeSprite(), i);
            		addSprite(g, menuScrollBar.getBaseBarSprite(), i);
            	}
    		}
		}
    }
    
    private void drawGame(Graphics g) {
    	for(int i=0; i<=ResourceLoader.uiLayerTop; i++) {
	    	if(bgImage != null && i == ResourceLoader.bgLayer) {
	    		System.out.println("draw bg");
				g.drawImage(bgImage, 0, 0, null);
			}
	    	
	        Sprite playerSprite = pac.getCurrentSprite();
	        HealthBar playerHealthBar = player.getHealthBar();
	        addSprite(g, playerSprite, i);
	        addSprite(g, playerHealthBar.getPositiveBarSprite(), String.valueOf(playerHealthBar.getSize()), i);
	        addSprite(g, playerHealthBar.getNegativeBarSprite(), String.valueOf(playerHealthBar.getSize()), i);
	        
	        for(Enemy enemy : enemies) {
	        	ActionController eac = enemy.getActionController();
	        	Sprite enemySprite = eac.getCurrentSprite();
	        	HealthBar enemyHealthBar = enemy.getHealthBar();
	        	
	        	addSprite(g, enemySprite, i);
	 	        addSprite(g, enemyHealthBar.getPositiveBarSprite(), i);
	 	        addSprite(g, enemyHealthBar.getNegativeBarSprite(), i);
	        }
    	}
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
            
            pac.checkNextAnimation(sleep);

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