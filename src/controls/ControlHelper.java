package controls;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import game.Player;
import ui.FrameController;
import ui.MenuController;
import ui.ResourceLoader;

public class ControlHelper {
	private FrameController fc;
	private MenuController mc;
	private Player player;
	private KeyEventThread ket;
	
	private ArrayList<String> controls;
	private ArrayList<String> keyboardControls;
	private ArrayList<String> mouseControls;
	private int lastMouseButton;
	
	public ControlHelper(FrameController fc, MenuController mc, Player player) {
		this.fc = fc;
		this.mc = mc;
		this.player = player;
		controls = new PlayerControls();
		for(String control : controls) {
			if(control.contains("Mouse")) {
				mouseControls.add(control);
			}
			else {
				keyboardControls.add(control);
			}
		}
		
		MouseListenerEvent mle = new MouseListenerEvent(this);
        MouseMotionListenerEvent mmle = new MouseMotionListenerEvent(this);
        
        //Register for mouse events on panel.
        fc.addMouseListener(mle);
        fc.addMouseMotionListener(mmle);
        
        ket = new KeyEventThread(this);
        ket.start();
	}
	
	void mouseMoved(MouseEvent e) {
		int screen = fc.getScreen();
		if(ResourceLoader.screenIsMenu(screen)) {
			mc.highlightItemCheck(e.getX(), e.getY());
		}
	}
	
	void mouseDragged(MouseEvent e) {
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
	
	void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			int screen = fc.getScreen();
			if(ResourceLoader.screenIsMenu(screen)) {
				mc.checkHighlightedItem(e.getY());
			}
		}
	}
	
	void mousePressed(MouseEvent e) {
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
	
	void mouseReleased(MouseEvent e) {
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
	
	void mouseExited(MouseEvent e) {
		int screen = fc.getScreen();
		if(ResourceLoader.screenIsMenu(screen)) {
			mc.nullifyHighlightedItem();
		}
	}
	
	void mouseWheelMoved(MouseEvent e) {
		int screen = fc.getScreen();
		if(ResourceLoader.screenIsMenu(screen)) {
			mc.nullifyHighlightedItem();
		}
	}
	
	void checkActions(String keyBuffer) {
		String[] keyBufferArray = keyBuffer.split(":");
		String keyCommand = keyBufferArray[0];
		String statusStr = keyBufferArray[1];
		//System.out.println(keyCommand);
		//System.out.println(statusStr);
		if(statusStr.equals("1")) {
			int screen = fc.getScreen();
			if(ResourceLoader.screenIsMenu(screen)) {
				if(keyCommand.equals("Down")) {
					mc.highlightItemCheck(true);
				}
				else if(keyCommand.equals("Up")) {
					mc.highlightItemCheck(false);
				}
				else if(keyCommand.equals("Enter")) {
					mc.selectHighlightedItem();
				}
				else if(keyCommand.equals("Escape")) {
					mc.goBack();
				}
			}
		}
	}
}
