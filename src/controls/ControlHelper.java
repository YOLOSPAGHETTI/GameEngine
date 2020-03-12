package controls;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ControlHelper {
	private ArrayList<String> controls;
	private ArrayList<String> keyboardControls;
	private ArrayList<String> mouseControls;
	
	public ControlHelper(JPanel jp) {
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
        jp.addMouseListener(mle);
        jp.addMouseMotionListener(mmle);
        
        KeyEventThread ket = new KeyEventThread(this);
	}
	
	void eventOutput(String eventDescription, MouseEvent e) {        
        /*System.out.println(eventDescription
                + " (" + e.getX() + "," + e.getY() + ")"
                + " detected on "
                + e.getComponent().getClass().getName());*/
    }
	
	void checkActions(String keyBuffer) {
		String[] keyBufferArray = keyBuffer.split(":");
		String keyCommand = keyBufferArray[0];
		String statusStr = keyBufferArray[1];
		System.out.println(keyCommand);
		System.out.println(statusStr);
		if(statusStr.equals("1")) {
			
		}
	}
}
