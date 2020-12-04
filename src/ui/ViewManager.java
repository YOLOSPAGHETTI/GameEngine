package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import controls.Control;
import controls.ControlHelper;
import game.Entity;

public class ViewManager extends JFrame {
	private static final long serialVersionUID = 1L;
	// Screen Size
	public static int frameWidth;
	public static int frameHeight;
	private int maxLayer;
	
	private static HashMap<Integer, View> views = new HashMap<Integer, View>();
	private static View currentView;
	
	private FrameBuilder fb;
	
	public ViewManager(int maxLayer) {
		this.maxLayer = maxLayer;
    }
    
    public void initUI() {   	
    	ControlHelper ch = new ControlHelper();
		//Register for mouse events on panel.
        addMouseListener(ch);
        addMouseMotionListener(ch);
        addMouseWheelListener(ch);
        addKeyListener(ch);
    	
        useNativeResolution();
        
    	fb = new FrameBuilder(maxLayer);
    	add(fb);
    	
    	setUndecorated(true);
    	setResizable(false);
    	setExtendedState(JFrame.MAXIMIZED_BOTH);
        
		setVisible(true);
        pack();
        
        setTitle("Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
	public static void addView(View view) {
		views.put(view.getId(), view);
	}
	
	public static Object getView() {
		return currentView;
	}
	
	public static int getViewId() {
		return currentView.getId();
	}
	
	public static void setView(int id) {
		//System.out.println(id);
		currentView = views.get(id);
	}
	
	public static ArrayList<Control> getControls() {
		//System.out.println(currentView);
		return currentView.getControls();
	}
	
	public static ArrayList<Entity> getEntities() {
		return currentView.getEntities();
	}
	
	public static void useNativeResolution() {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	changeResolution(screenSize.width, screenSize.height);
    }
    
    public static void changeResolution(int width, int height) {
    	//System.out.println("changeRes");
    	
    	frameWidth = width;
    	frameHeight = height;
    }
}