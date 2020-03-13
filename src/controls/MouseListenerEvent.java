package controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerEvent implements MouseListener {
	private ControlHelper ch;
	public MouseListenerEvent(ControlHelper ch) {
		this.ch = ch;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		ch.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//ch.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		ch.mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ch.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ch.mouseReleased(e);
	}
}
