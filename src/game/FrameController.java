package game;

import java.awt.EventQueue;

import javax.swing.JFrame;

import sprites.ResourceLoader;

public class FrameController extends JFrame {
		
	public FrameController() {
        initUI();
    }
    
    private void initUI() {
        add(new FrameBuilder());         
        
        setResizable(false);
        pack();
        
        setTitle("Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new FrameController();
            ex.setVisible(true);
        });
    }
}