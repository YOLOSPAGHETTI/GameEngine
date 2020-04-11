package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import game.Action;
import game.Entity;
import game.GameManager;
import ui.FrameController;

public class ControlHelper implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {	
	private FrameController fc;
	private ArrayList<Control> controls = new ArrayList<Control>();
	private int mouseX = -1;
	private int mouseY = -1;
	private int lastMouseButtonPressed = -1;
	
	public ControlHelper(FrameController fc) {
		this.fc = fc;
		this.controls.addAll(fc.getControls());
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseX = e.getY();
		for(Control control : controls) {
			Input input = control.getInput();
			if(input.getMouseInput() == MouseEvent.MOUSE_MOVED) {
				queueControlAction(control);
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		for(Control control : controls) {
			Input input = control.getInput();
			if(input.getMouseInput() == MouseEvent.MOUSE_DRAGGED) {
				queueControlAction(control);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for(Control control : controls) {
			Input input = control.getInput();
			if(input.getMouseInput() == MouseEvent.MOUSE_CLICKED && 
					input.getMouseButton() == e.getButton()) {
				queueControlAction(control);
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		for(Control control : controls) {
			Input input = control.getInput();
			if(input.getMouseInput() == MouseEvent.MOUSE_PRESSED && 
					input.getMouseButton() == e.getButton()) {
				queueControlAction(control);
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		for(Control control : controls) {
			if(control.shouldCancelOnRelease()) {
				Input input = control.getInput();
				if(lastMouseButtonPressed == input.getMouseButton()) {
					cancelControlAction(control);
				}
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for(Control control : controls) {
			Input input = control.getInput();
			if(input.getMouseInput() == MouseEvent.MOUSE_WHEEL) {
				queueControlAction(control);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("KeyTyped: " + e.paramString());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for(Control control : controls) {
			Character inputChar = control.getInput().getCharacterInput();
			if(inputChar != null && inputChar == e.getKeyChar()) {
				queueControlAction(control);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for(Control control : controls) {
			if(control.shouldCancelOnRelease()) {
				Input input = control.getInput();
				if(input.getCharacterInput() != null) {
					cancelControlAction(control);
				}
			}
		}
	}
	
	private void queueControlAction(Control control) {
		Action action = control.getAction();
		Entity entity = control.getEntity();
		entity.queueAction(action);
	}
	
	private void cancelControlAction(Control control) {
		Action action = control.getAction();
		Entity entity = control.getEntity();
		entity.cancelAction(action);
	}
	
	private int getMouseX() {
		return mouseX;
	}
	
	private int getMouseY() {
		return mouseY;
	}
}
