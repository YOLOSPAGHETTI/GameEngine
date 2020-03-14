package game;

import java.awt.EventQueue;

import ui.FrameController;
import ui.PlayerAnimationController;
import ui.ResourceLoader;

public class GameManager {
	private static FrameController gameScreen;
	private static Player player;
	
	public static void main(String[] args) {
		PlayerAnimationController pac = ResourceLoader.pac;
		player = new Player(pac);
		
        EventQueue.invokeLater(() -> {
            gameScreen = new FrameController(player);
            gameScreen.setVisible(true);
        });
    }
}
