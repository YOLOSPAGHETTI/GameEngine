package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import game.Player;
import ui.FrameController;
import ui.MenuController;
import ui.ResourceLoader;

public class ControlHelper implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	private FrameController fc;
	private MenuController mc;
	private Player player;
	
	private PlayerControls controls;
	private int lastMouseButton;
	
	public ControlHelper(FrameController fc, MenuController mc, Player player) {
		this.fc = fc;
		this.mc = mc;
		this.player = player;
		controls = new PlayerControls();
        
        //Register for mouse events on panel.
        fc.addMouseListener(this);
        fc.addMouseMotionListener(this);
        fc.addMouseWheelListener(this);
        fc.addKeyListener(this);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		int screen = fc.getScreen();
		if(ResourceLoader.screenIsMenu(screen)) {
			mc.highlightItemCheck(e.getX(), e.getY());
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(lastMouseButton == MouseEvent.BUTTON1) {
			int screen = fc.getScreen();
			if(ResourceLoader.screenIsMenu(screen)) {
				mc.checkHighlightedItem(e.getY());
			}
			else {
				player.setDragStartCoordinates(e.getX(), e.getY());
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			int screen = fc.getScreen();
			if(ResourceLoader.screenIsMenu(screen)) {
				mc.checkHighlightedItem(e.getY());
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		lastMouseButton = e.getButton();
		if(e.getButton() == MouseEvent.BUTTON1) {
			int screen = fc.getScreen();
			if(ResourceLoader.screenIsMenu(screen)) {
				mc.selectScrollBar(true, e.getY());
			}
			else {
				player.block(e.getX());
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			int screen = fc.getScreen();
			if(ResourceLoader.screenIsMenu(screen)) {
				mc.selectScrollBar(false, -1);
			}
			else {
				player.determineAttack(e.getX(), e.getY());
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		int screen = fc.getScreen();
		if(ResourceLoader.screenIsMenu(screen)) {
			mc.nullifyHighlightedItem();
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int screen = fc.getScreen();
		if(ResourceLoader.screenIsMenu(screen)) {
			//mc.nullifyHighlightedItem();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("KeyTyped: " + e.paramString());	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("KeyPressed: " + e.paramString());
		
		int screen = fc.getScreen();
		int keyCommand = e.getKeyCode();
		if(ResourceLoader.screenIsMenu(screen)) {
			if(keyCommand == KeyEvent.VK_DOWN) {
				mc.highlightItemCheck(true);
			}
			else if(keyCommand == KeyEvent.VK_UP) {
				mc.highlightItemCheck(false);
			}
			else if(keyCommand == KeyEvent.VK_ENTER) {
				mc.selectHighlightedItem();
			}
			else if(keyCommand == KeyEvent.VK_ESCAPE) {
				mc.goBack();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("KeyReleased: " + e.paramString());
		
	}
}
