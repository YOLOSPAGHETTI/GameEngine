package controls;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import sprites.FrameController;
import sprites.MenuController;
import sprites.PlayerAnimationController;
import sprites.ResourceLoader;

public class ControlHelper {
	private FrameController fc;
	private MenuController mc;
	private PlayerAnimationController pac;
	
	private ArrayList<String> controls;
	private ArrayList<String> keyboardControls;
	private ArrayList<String> mouseControls;
	
	public ControlHelper(FrameController fc, MenuController mc, PlayerAnimationController pac) {
		this.fc = fc;
		this.mc = mc;
		this.pac = pac;
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
        
        KeyEventThread ket = new KeyEventThread(this);
        ket.start();
	}
	
	void mouseMoved(MouseEvent e) {
		int screen = fc.getScreen();
		if(ResourceLoader.screenIsMenu(screen)) {
			mc.highlightItemCheck(e.getX(), e.getY());
		}
	}
	
	void mouseDragged(MouseEvent e) {
		int screen = fc.getScreen();
		if(ResourceLoader.screenIsMenu(screen)) {
			mc.checkHighlightedItem(e.getY());
		}
	}
	
	void mouseClicked(MouseEvent e) {
		int screen = fc.getScreen();
		if(ResourceLoader.screenIsMenu(screen)) {
			mc.checkHighlightedItem(e.getY());
		}
	}
	
	void mousePressed(MouseEvent e) {
		int screen = fc.getScreen();
		if(ResourceLoader.screenIsMenu(screen)) {
			mc.selectScrollBar(true, e.getY());
		}
	}
	
	void mouseReleased(MouseEvent e) {
		int screen = fc.getScreen();
		if(ResourceLoader.screenIsMenu(screen)) {
			mc.selectScrollBar(false, -1);
		}
	}
	
	void mouseExited(MouseEvent e) {
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
			}
		}
	}
}
