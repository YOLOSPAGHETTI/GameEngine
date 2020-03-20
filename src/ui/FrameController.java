package ui;

import javax.swing.JFrame;

import controls.ControlHelper;
import game.ActionController;
import game.Player;

public class FrameController extends JFrame {
	
	private int screen;
	private FrameBuilder fb;
	
	public FrameController(Player player) {
		screen = ResourceLoader.startMenuScreen;
		MenuController mc = new MenuController(this);
		ControlHelper ch = new ControlHelper(this, mc, player);
		fb = new FrameBuilder(this, mc, player);
		
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
    
    public int getScreen() {
    	return screen;
    }
    
    public void setScreen(int screen) {
    	this.screen = screen;
    }
}