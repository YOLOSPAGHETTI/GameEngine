package ui;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import controls.Control;
import controls.ControlHelper;
import game.Entity;
import game.GameManager;

public class FrameController extends JFrame {
	private static final long serialVersionUID = 1L;
	// Screen Size
	public static int frameWidth;
	public static int frameHeight;
	
	private static HashMap<Integer, View> views = new HashMap<Integer, View>();
	private static View currentView;
	
	private FrameBuilder fb;
	
	public FrameController(GameManager gm, int maxLayer) {
		ControlHelper ch = new ControlHelper(this);
		//Register for mouse events on panel.
        addMouseListener(ch);
        addMouseMotionListener(ch);
        addMouseWheelListener(ch);
        addKeyListener(ch);
		
		fb = new FrameBuilder(this, maxLayer);
		
        initUI();
    }
    
    private void initUI() {
    	setUndecorated(true);
    	setExtendedState(JFrame.MAXIMIZED_BOTH);
    	
        add(fb);
        
        setResizable(false);
        pack();
        
        setTitle("Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
	public void addView(View mode) {
		views.put(mode.getId(), mode);
	}
	
	public View getView() {
		return currentView;
	}
	
	public int getViewId() {
		return currentView.getId();
	}
	
	public void setView(int id) {
		currentView = views.get(id);
	}
	
	public ArrayList<Control> getControls() {
		return currentView.getControls();
	}
	
	public ArrayList<Entity> getEntities() {
		return currentView.getEntities();
	}
}