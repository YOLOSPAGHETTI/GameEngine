package game;

import java.awt.EventQueue;

import sprites.FrameController;

public class GameManager {
	private static FrameController gameScreen;
	
	public static void main(String[] args) {
	
        EventQueue.invokeLater(() -> {
            gameScreen = new FrameController();
            gameScreen.setVisible(true);
        });
    }
}
