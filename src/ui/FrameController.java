package ui;

import javax.swing.JFrame;

import controls.ControlHelper;
import game.Player;

public class FrameController extends JFrame {
	
	private int screen;
	private ControlHelper ch;
	private MenuController mc;
	private PlayerAnimationController pac;
	private FrameBuilder fb;
	
	public FrameController(Player player) {
		ResourceLoader.replacePathSeparators();
		ResourceLoader.useNativeResolution();
		screen = ResourceLoader.startMenuScreen;
		mc = new MenuController(this);
		ResourceLoader.setupAnimations();
		pac = player.getAnimationController();
    	pac.initialize();
		ch = new ControlHelper(this, mc, player);
        initUI();
    }
    
    private void initUI() {
    	fb = new FrameBuilder(this, mc, pac);
        add(fb);         
        
        setResizable(false);
        pack();
        
        setTitle("Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public int getScreen() {
    	return screen;
    }
    
    public void setScreen(int screen) {
    	this.screen = screen;
    }
}