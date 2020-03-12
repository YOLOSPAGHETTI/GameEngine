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
		ch.eventOutput("Mouse clicked", e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		ch.eventOutput("Mouse entered", e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		ch.eventOutput("Mouse exited", e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ch.eventOutput("Mouse pressed", e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ch.eventOutput("Mouse released", e);
	}
}
