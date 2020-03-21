package ui;

import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import game.Action;
import game.Enemy;
import game.StatusEffect;
import ui.Menu;
import ui.MenuItem;

public final class ResourceLoader {
	private static final String workingDir = System.getProperty("user.dir");
	private static final String pathSeparator = File.separator;
	
	// Screen Ids
	protected static final int startMenuScreen = 0;
	protected static final int inGameScreen = 1;
	protected static final int inGamePauseMenuScreen = 2;
	protected static final int loadSavesMenuScreen = 3;
	protected static final int upgradesMenuScreen = 4;
	protected static final int settingsMenuScreen = 5;
	protected static final int videoSettingsMenuScreen = 6;
	protected static final int soundSettingsMenuScreen = 7;
	protected static final int controlsSettingsMenuScreen = 8;
	
	// Screen Size
	public static int frameWidth;
	public static int frameHeight;
	private static final String bgFile = "";
	public static BufferedImage bgImage;
	
	// Layers
	public static final int bgLayer = 0;
	public static final int entityLayerBottom = 1;
	public static final int entityLayerMid = 2;
	public static final int entityLayerTop = 3;
	public static final int uiLayerBottom = 4;
	public static final int uiLayerMid = 5;
	public static final int uiLayerTop = 6;
	
	// Menus
	protected static HashMap<Integer,Menu> menus = new HashMap<Integer,Menu>();
	private static final int minScrollBarHeight = 20;
	private static String menuItemBaseFile = workingDir + pathSeparator + "src/img/menu/menuitem.jpg";
	private static String menuItemHighlightedFile = workingDir + pathSeparator + "src/img/menu/highlightedmenuitem.jpg";
	private static String scrollEdgeBaseFile = workingDir + pathSeparator + "src/img/menu/scrolledge.jpg";
	private static String scrollEdgeHighlightedFile = workingDir + pathSeparator + "src/img/menu/highlightedscrolledge.jpg";
	private static String scrollBarBaseFile = workingDir + pathSeparator + "src/img/menu/scrollbar.jpg";
	private static String scrollBarSelectedFile = workingDir + pathSeparator + "src/img/menu/selectedscrollbar.jpg";
	
	// Targets
	public static final int player = 0;
	public static final int enemy1 = 1;
	public static final int enemy2 = 2;
	public static final int enemy3 = 3;
	
	// Player data
	static int playerWidth;
	static int playerHeight;
	static int playerStartX;
	static int playerStartY;
	private static String playerStartFile = workingDir + pathSeparator + "src/img/player/germanic-knight.jpg";
	
	// Player animations
	private static final long idleTime = 1000;
    private static final long playerCleaveTime = 500;
    private static final long playerStabTime = 800;
    private static final long playerBlockTime = 1000;
    private static final long playerStunTime = 250;
    public static Action playerCleaveLeft;
    public static Action playerCleaveRight;
    public static Action playerStabLeft;
    public static Action playerStabMid;
    public static Action playerStabRight;
    public static Action playerBlockLeft;
    public static Action playerBlockMid;
    public static Action playerBlockRight;
    public static Action playerStunned;
    public static Action playerIdle;
    
    public static final int baseCleaveDamage = 7;
    public static final int baseStabDamage = 15;
    public static final int baseShieldDamageBlock = 10;
    public static final int baseArmorDamageBlock = 0;
    
    // Enemies
    private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private static int enemyWidth;
	private static int enemyHeight;
	private static int enemy1StartX;
	private static int enemy2StartX;
	private static int enemy3StartX;
	private static int enemyStartY;
	private static int enemyVerticalSpacing;
    private static String enemyStartFile = workingDir + pathSeparator + "src/img/enemies/evil-knight.jpg";
    public static Action enemyIdle;
    public static Action enemyAttack;
    
    // Health Bars
 	private static String healthBarPositiveFile = workingDir + pathSeparator + "src/img/player/player_health_bar_positive.jpg";
 	private static String healthBarNegativeFile = workingDir + pathSeparator + "src/img/player/player_health_bar_negative.jpg";
 	public static BufferedImage healthBarPositive;
 	public static BufferedImage healthBarNegative;

    public static void useNativeResolution() {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	changeResolution(screenSize.width, screenSize.height);
    }
    
    protected static void changeResolution(int width, int height) {
    	//System.out.println("changeRes");
    	
    	frameWidth = width;
    	frameHeight = height;
    	
    	playerWidth = width/10;
    	playerHeight = height/7;
    	playerStartX = width/2 - playerWidth/2;
    	playerStartY = height - playerHeight - playerHeight/2;
    	
    	enemyWidth = width/12;
    	enemyHeight = height/7;
    	enemy1StartX = width/4;
    	enemy2StartX = width/2 - enemyWidth/2;
    	enemy3StartX = (2*width)/3;
    	enemyStartY = height - playerHeight - playerHeight/2 - (height*3)/14;
    	enemyVerticalSpacing = height/14;
    	
    	// bgImage = loadImage(bgFile);
    	
    	setupMenus();
    	setupActions();
    	
    	healthBarPositive = loadImage(healthBarPositiveFile);
    	healthBarNegative = loadImage(healthBarNegativeFile);
    }
    
    public static boolean screenIsMenu(int screen) {
    	if(screen == inGameScreen) {
    		return false;
    	}
    	return true;
    }
    
    public static boolean actionIsBlock(Action action) {
    	if(action == playerBlockLeft || action == playerBlockRight || action == playerBlockMid) {
    		return true;
    	}
    	return false;
    }
    
    private static void setupMenus() {
    	BufferedImage menuItemBaseImage = loadImage(menuItemBaseFile);
    	BufferedImage menuItemHighlightedImage = loadImage(menuItemHighlightedFile);
    	
    	Menu startMenu = new Menu();
    	MenuItem menuItem = new MenuItem("Play", inGameScreen, menuItemBaseImage, menuItemHighlightedImage, startMenu.size());
    	startMenu.add(menuItem);
    	menuItem = new MenuItem("Load Saves", loadSavesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, startMenu.size());
    	startMenu.add(menuItem);
    	menuItem = new MenuItem("Upgrades", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, startMenu.size());
    	startMenu.add(menuItem);
    	menuItem = new MenuItem("Settings", settingsMenuScreen, menuItemBaseImage, menuItemHighlightedImage, startMenu.size());
    	startMenu.add(menuItem);
    	menuItem = new MenuItem("Exit", -1, menuItemBaseImage, menuItemHighlightedImage, startMenu.size());
    	startMenu.add(menuItem);
    	checkAddScrollBar(startMenu);

    	menus.put(startMenuScreen, startMenu);
    	
    	Menu upgradesMenu = new Menu();
    	upgradesMenu.setParentId(startMenuScreen);
    	menuItem = new MenuItem("Back", startMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("Health", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("Cleave Damage", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("Stab Damage", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("Shield Damage Block", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("Armor Damage Block", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("Bleed Damage", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("Crit Chance", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("Multiplier Crit", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("Accuracy Chance", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("Stab Stun Chance", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("Lifesteal", upgradesMenuScreen, menuItemBaseImage, menuItemHighlightedImage, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	checkAddScrollBar(upgradesMenu);

    	menus.put(upgradesMenuScreen, upgradesMenu);
    	
    	Menu settingsMenu = new Menu();
    	settingsMenu.setParentId(startMenuScreen);
    	menuItem = new MenuItem("Back", startMenuScreen, menuItemBaseImage, menuItemHighlightedImage, settingsMenu.size());
    	settingsMenu.add(menuItem);
    	menuItem = new MenuItem("Video", videoSettingsMenuScreen, menuItemBaseImage, menuItemHighlightedImage, settingsMenu.size());
    	settingsMenu.add(menuItem);
    	menuItem = new MenuItem("Sound", soundSettingsMenuScreen, menuItemBaseImage, menuItemHighlightedImage, settingsMenu.size());
    	settingsMenu.add(menuItem);
    	menuItem = new MenuItem("Controls", controlsSettingsMenuScreen, menuItemBaseImage, menuItemHighlightedImage, settingsMenu.size());
    	settingsMenu.add(menuItem);
    	checkAddScrollBar(settingsMenu);

    	menus.put(settingsMenuScreen, settingsMenu);
    	//System.out.println("menus done");
    }
    
    private static void checkAddScrollBar(Menu menu) {
    	int scrollBarHeight = -1;
    	int totalMenuHeight = 0;
    	for(MenuItem menuItem : menu) {
    		totalMenuHeight += menuItem.getBaseSprite().getHeight();
    	}
    	menu.setTotalHeight(totalMenuHeight);
    	if(totalMenuHeight > frameHeight) {
    		// System.out.println(frameHeight);
    		// System.out.println(totalMenuHeight);
    		scrollBarHeight = (int)Math.round(((double)frameHeight/(double)totalMenuHeight)*(double)frameHeight);
    		// System.out.println(scrollBarHeight);
    		if(scrollBarHeight < minScrollBarHeight) {
    			scrollBarHeight = minScrollBarHeight;
    		}
    	}
    	if(scrollBarHeight != -1) {
    		BufferedImage scrollEdgeBaseImage = loadImage(scrollEdgeBaseFile);
        	BufferedImage scrollBarBaseImage = loadImage(scrollBarBaseFile);
        	BufferedImage scrollEdgeHighlightedImage = loadImage(scrollEdgeHighlightedFile);
        	BufferedImage scrollBarSelectedImage = loadImage(scrollBarSelectedFile);
    		
    		MenuScrollBar scrollBar = new MenuScrollBar(scrollEdgeBaseImage, scrollBarBaseImage, scrollEdgeHighlightedImage, 
    				scrollBarSelectedImage, true, scrollBarHeight);
    		menu.setScrollBar(scrollBar);
    		
    		int offset = scrollBar.getBaseEdgeSprite().getWidth();
    		for(MenuItem menuItem : menu) {
        		Sprite sprite = menuItem.getBaseSprite();
        		sprite.setWidth(sprite.getWidth() - offset);
        		sprite = menuItem.getHighlightedSprite();
        		sprite.setWidth(sprite.getWidth() - offset);
        	}
    	}
    }
    
    private static void setupActions() {
    	BufferedImage playerStartImage = loadImage(playerStartFile);
    	
    	ArrayList<Integer> targets = new ArrayList<Integer>();
    	ArrayList<StatusEffect> effects = new ArrayList<StatusEffect>();
    	playerIdle = new Action(0, 0, targets, effects, 0, idleTime);
    	playerIdle.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerIdle.add(new PlayerSprite(playerStartImage, 20, 0));
    	playerIdle.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerIdle.add(new PlayerSprite(playerStartImage, -20, 0));
    	playerIdle.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerIdle.calculateTimePerSprite();
    	//System.out.println("idle done");
    	
    	targets.add(enemy3);
    	targets.add(enemy2);
    	targets.add(enemy1);
    	playerCleaveLeft = new Action(baseCleaveDamage, baseCleaveDamage, targets, effects, 1, playerCleaveTime);
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, 20, 20));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, 40, 40));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, 60, 60));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, 40, 60));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, 20, 60));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, 0, 60));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, -20, 60));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, -40, 60));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, -60, 60));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, -40, 40));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, -20, 20));
    	playerCleaveLeft.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerCleaveLeft.calculateTimePerSprite();
    	
    	targets.clear();
    	targets.add(enemy1);
    	targets.add(enemy2);
    	targets.add(enemy3);
    	playerCleaveRight = new Action(baseCleaveDamage, baseCleaveDamage, targets, effects, 1, playerCleaveTime);
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, -20, 20));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, -40, 40));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, -60, 60));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, -40, 60));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, -20, 60));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, 0, 60));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, 20, 60));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, 40, 60));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, 60, 60));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, 40, 40));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, 20, 20));
    	playerCleaveRight.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerCleaveRight.calculateTimePerSprite();
    	//System.out.println("cleave done");
    	
    	targets.clear();
    	targets.add(enemy1);
    	playerStabLeft = new Action(baseStabDamage, baseStabDamage, targets, effects, 2, playerStabTime);
    	playerStabLeft.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerStabLeft.add(new PlayerSprite(playerStartImage, 20, -20));
    	playerStabLeft.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerStabLeft.add(new PlayerSprite(playerStartImage, -20, 20));
    	playerStabLeft.add(new PlayerSprite(playerStartImage, -40, 40));
    	playerStabLeft.add(new PlayerSprite(playerStartImage, -60, 60));
    	playerStabLeft.add(new PlayerSprite(playerStartImage, -40, 40));
    	playerStabLeft.add(new PlayerSprite(playerStartImage, -20, 20));
    	playerStabLeft.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerStabLeft.calculateTimePerSprite();
    	
    	targets.clear();
    	targets.add(enemy3);
    	playerStabRight = new Action(baseStabDamage, baseStabDamage, targets, effects, 2, playerStabTime);
    	playerStabRight.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerStabRight.add(new PlayerSprite(playerStartImage, -20, -20));
    	playerStabRight.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerStabRight.add(new PlayerSprite(playerStartImage, 20, 20));
    	playerStabRight.add(new PlayerSprite(playerStartImage, 40, 40));
    	playerStabRight.add(new PlayerSprite(playerStartImage, 60, 60));
    	playerStabRight.add(new PlayerSprite(playerStartImage, 40, 40));
    	playerStabRight.add(new PlayerSprite(playerStartImage, 20, 20));
    	playerStabRight.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerStabRight.calculateTimePerSprite();
    	
    	targets.clear();
    	targets.add(enemy2);
    	playerStabMid = new Action(baseStabDamage, baseStabDamage, targets, effects, 2, playerStabTime);
    	playerStabMid.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerStabMid.add(new PlayerSprite(playerStartImage, 0, -20));
    	playerStabMid.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerStabMid.add(new PlayerSprite(playerStartImage, 0, 20));
    	playerStabMid.add(new PlayerSprite(playerStartImage, 0, 40));
    	playerStabMid.add(new PlayerSprite(playerStartImage, 0, 60));
    	playerStabMid.add(new PlayerSprite(playerStartImage, 0, 40));
    	playerStabMid.add(new PlayerSprite(playerStartImage, 0, 20));
    	playerStabMid.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerStabMid.calculateTimePerSprite();
    	//System.out.println("stab done");
    	
    	targets.clear();
    	playerBlockLeft = new Action(0, 0, targets, effects, 0, playerBlockTime);
    	playerBlockLeft.add(new PlayerSprite(playerStartImage, -20, +20));
    	playerBlockLeft.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerBlockLeft.calculateTimePerSprite();
    	
    	playerBlockRight = new Action(0, 0, targets, effects, 0, playerBlockTime);
    	playerBlockRight.add(new PlayerSprite(playerStartImage, 20, 20));
    	playerBlockRight.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerBlockRight.calculateTimePerSprite();
    	
    	playerBlockMid = new Action(0, 0, targets, effects, 0, playerBlockTime);
    	playerBlockMid.add(new PlayerSprite(playerStartImage, 0, 20));
    	playerBlockMid.add(new PlayerSprite(playerStartImage, 0, 0));
    	playerBlockMid.calculateTimePerSprite();
    	//System.out.println("actions done");
    }
    
    public static void loadEnemy(int position, int type) {
    	BufferedImage enemyStartImage = loadImage(enemyStartFile);
    	ArrayList<Integer> targets = new ArrayList<Integer>();
    	ArrayList<StatusEffect> effects = new ArrayList<StatusEffect>();
    	
    	enemyIdle = new Action(0, 0, targets, effects, 0, 1000);
    	enemyIdle.add(new Sprite(enemyStartImage, calculateEnemyX(position), calculateEnemyY(position), 
    			entityLayerMid, enemyWidth, enemyHeight));
    	enemyIdle.calculateTimePerSprite();
    }
    
    private static int calculateEnemyX(int position) {
    	if(position%3 == 0) {
    		return enemy3StartX;
    	}
    	else if(position%2 == 0) {
    		return enemy2StartX;
    	}
    	return enemy1StartX;
    }
    
    private static int calculateEnemyY(int position) {
    	int row = (position-1)/3;
    	return enemyStartY - row * (enemyVerticalSpacing + enemyHeight);
    }
    
    public static boolean targetIsEnemy(int target) {
    	if(target != player) {
    		return true;
    	}
    	return false;
    }
    
    private static BufferedImage loadImage(String fileSource) {
    	BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileSource));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return img;
    }
	
	private static BufferedImage loadImage(String fileSource, double scale) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileSource));
			//width = (int) Math.round(img.getWidth() * scale);
	        //height = (int) Math.round(img.getHeight() * scale);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return img;
    }
    
    public static void replacePathSeparators() {
    	if(!pathSeparator.equals("/")) {
    		menuItemBaseFile = menuItemBaseFile.replace("/", pathSeparator);
    		menuItemHighlightedFile = menuItemHighlightedFile.replace("/", pathSeparator);
    		scrollEdgeBaseFile = scrollEdgeBaseFile.replace("/", pathSeparator);
    		scrollEdgeHighlightedFile = scrollEdgeHighlightedFile.replace("/", pathSeparator);
    		scrollBarBaseFile =scrollBarBaseFile.replace("/", pathSeparator);
    		scrollBarSelectedFile = scrollBarSelectedFile.replace("/", pathSeparator);
    		
    		playerStartFile = playerStartFile.replace("/", pathSeparator);
    		
    		enemyStartFile = enemyStartFile.replace("/", pathSeparator);
    		
    		healthBarPositiveFile = healthBarPositiveFile.replace("/", pathSeparator);
    		healthBarNegativeFile = healthBarNegativeFile.replace("/", pathSeparator);
    	}
    }
}
 