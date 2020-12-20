package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import actions.Action;
import actions.ActionController;
import ui.ViewManager;

public class ControlHelper implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	private ArrayList<Control> controls;
	private int mouseX = -1;
	private int mouseY = -1;
	private int lastMouseButtonPressed = -1;
	private boolean mouseButtonDown = false;
	private ArrayList<Integer> keysDown = new ArrayList<Integer>();
	
	public ControlHelper() {
		controls = ViewManager.getControls();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		//System.out.println("Mouse Moved: mouseX: " + mouseX + ", mouseY: " + mouseY);
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
		mouseButtonDown = true;
		lastMouseButtonPressed = e.getButton();
		//System.out.println("Mouse pressed: " + mouseButtonDown);
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
		mouseButtonDown = false;
		//System.out.println("Mouse released: " + mouseButtonDown);
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
		for(Control control : controls) {
			Input input = control.getInput();
			if(input.getMouseInput() == MouseEvent.MOUSE_ENTERED) {
				queueControlAction(control);
			}
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		for(Control control : controls) {
			Input input = control.getInput();
			if(input.getMouseInput() == MouseEvent.MOUSE_EXITED) {
				queueControlAction(control);
			}
		}
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
		keysDown.add(e.getExtendedKeyCode());
		for(Control control : controls) {
			ArrayList<Integer> inputKeys = control.getInput().getKeyInputs();
			if(!inputKeys.isEmpty()) {
				int inputsPressed = 0;
				for(Integer inputKey : inputKeys) {
					if(keysDown.contains(inputKey)) {
						inputsPressed++;
					}
				}
				if(inputsPressed == inputKeys.size()) {
					queueControlAction(control);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysDown.remove((Integer)e.getExtendedKeyCode());
		for(Control control : controls) {
			if(control.shouldCancelOnRelease()) {
				ArrayList<Integer> inputKeys = control.getInput().getKeyInputs();
				if(!inputKeys.isEmpty()) {
					if(inputKeys.contains(e.getExtendedKeyCode())) {
						cancelControlAction(control);
					}
				}
			}
		}
	}
	
	private void queueControlAction(Control control) {
		Action action = control.getAction();
		ActionController controller = control.getController();
		ControlState state = new ControlState(mouseX, mouseY, mouseButtonDown, lastMouseButtonPressed, keysDown);
		controller.queue(action, state);
	}
	
	private void cancelControlAction(Control control) {
		Action action = control.getAction();
		ActionController controller = control.getController();
		controller.unqueue(action);
	}
	
	public void setControls(ArrayList<Control> controls) {
		this.controls = controls;
	}
}
