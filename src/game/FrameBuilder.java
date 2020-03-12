package game;

import sprites.Animation;
import sprites.EnemyAnimationController;
import sprites.PlayerAnimationController;
import sprites.ResourceLoader;
import sprites.Sprite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controls.ControlHelper;

public class FrameBuilder extends JPanel
		implements Runnable {
    private int width;
    private int height;
    private String bgFile;
    private Image frame;
    
    private Thread animator;
    private final int frameTime = 15;
    
    private PlayerAnimationController pac;
    private ControlHelper ch;

    public FrameBuilder() {
    	ResourceLoader.useNativeResolution();
        width = ResourceLoader.frameWidth;
        height = ResourceLoader.frameHeight;
        bgFile = ResourceLoader.bgFile;
    	
    	ResourceLoader.setupAnimations();
    	pac = ResourceLoader.pac;
    	pac.initialize();
    	
    	ch = new ControlHelper(this);
        
        build();
    }
    
    private void build() {
    	setBackground(Color.green);
        setPreferredSize(new Dimension(width, height));
        
      // pac = ResourceLoader.pac;
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
    	ImageIcon ii = new ImageIcon(bgFile);
        g.drawImage(ii.getImage(), 0, 0, null);
        Sprite playerSprite = pac.getCurrentSprite();
        addSprite(g, playerSprite);
        Toolkit.getDefaultToolkit().sync();
    }

    private void addEnemySprites(ArrayList<EnemyAnimationController> esArray) {
        /*int enemyStart1X = maxX/3 - esArray.get(0).maxX/2;
        int enemyStart2X = maxX/2 - esArray.get(1).maxX/2;
        int enemyStart3X = (2*maxX)/3 - esArray.get(2).maxX/2;
        int enemyStartY = (3*maxY)/7 - esArray.get(0).maxY/2;
        int enemyYDiff = esArray.get(0).maxY;*/
    }

    private void addSprite(Graphics g, Sprite sprite) {
    	g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), sprite.getHeight(null), sprite.getWidth(null), null);
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