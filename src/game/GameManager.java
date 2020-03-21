package game;

import java.awt.EventQueue;
import java.util.ArrayList;

import ui.FrameController;
import ui.ResourceLoader;

final class GameManager {
	private static FrameController gameScreen;
	private static int nextLevel = 1;
	private static ArrayList<Integer> enemyTypes = new ArrayList<Integer>();
	private static Player player;
	private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public static void main(String[] args) {
		ResourceLoader.replacePathSeparators();
		ResourceLoader.useNativeResolution();
		ActionController pac = new ActionController(ResourceLoader.playerIdle);
		player = new Player(pac);
		
		for(int i=1; i<=9; i++) {
		//for(int i=1; i<=enemyTypes.size(); i++) {
			ResourceLoader.loadEnemy(i, 0);
			// ResourceLoader.loadEnemy(i, enemyTypes.get(i));
			ActionController eac = new ActionController(ResourceLoader.enemyIdle);
			enemies.add(new Enemy(eac, i));
		}
		
        EventQueue.invokeLater(() -> {
            gameScreen = new FrameController(player, enemies);
            gameScreen.setVisible(true);
        });
    }
	
	static Player getPlayer() {
		return player;
	}
	
	static Enemy getEnemyAtPosition(int position) {
		for(Enemy enemy : enemies) {
			if(enemy.getPosition() == position) {
				return enemy;
			}
		}
		return null;
	}
}
