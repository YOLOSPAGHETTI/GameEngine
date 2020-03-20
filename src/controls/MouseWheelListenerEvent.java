package controls;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelListenerEvent implements MouseWheelListener  {
	private ControlHelper ch;
	public MouseWheelListenerEvent(ControlHelper ch) {
		this.ch = ch;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		ch.mouseWheelMoved(e);
	}

}
