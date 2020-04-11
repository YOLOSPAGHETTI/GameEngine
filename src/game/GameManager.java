package game;

import java.awt.EventQueue;

import ui.FrameController;

public class GameManager {
	private static FrameController gameScreen;
	
	public GameManager(int maxLayer) {
		EventQueue.invokeLater(() -> {
            gameScreen = new FrameController(this, maxLayer);
            gameScreen.setVisible(true);
        });
	}
}
