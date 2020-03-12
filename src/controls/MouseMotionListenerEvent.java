package controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

import javax.swing.*;

public class MouseMotionListenerEvent implements MouseMotionListener {
    
	private ControlHelper ch;
	
    public MouseMotionListenerEvent(ControlHelper ba) {
        this.ch = ba;
    }
    
    public void mouseMoved(MouseEvent e) {
        ch.eventOutput("Mouse moved", e);
    }
    
    public void mouseDragged(MouseEvent e) {
        ch.eventOutput("Mouse dragged", e);
    }
}