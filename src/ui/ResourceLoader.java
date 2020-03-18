package ui;

import java.awt.Dimension;

import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import game.Action;
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
	protected static final String bgFile = "";
	
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
	private static int playerWidth;
	private static int playerHeight;
	private static int playerStartX;
	private static int playerStartY;
	private static String playerStartFile = workingDir + pathSeparator + "src/img/player/germanic-knight.jpg";
	
	// Player animations
	private static final long playerIdleTime = 1000;
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

    public static void useNativeResolution() {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	changeResolution(screenSize.width, screenSize.height);
    }
    
    protected static void changeResolution(int width, int height) {
    	System.out.println("changeRes");
    	
    	frameWidth = width;
    	frameHeight = height;
    	
    	playerWidth = width/10;
    	playerHeight = height/7;
    	playerStartX = width/2 - playerWidth/2;
    	playerStartY = height - playerHeight - playerHeight/2;
    	
    	setupMenus();
    	setupActions();
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
    	Menu startMenu = new Menu();
    	MenuItem menuItem = new MenuItem("Play", inGameScreen, menuItemBaseFile, menuItemHighlightedFile, startMenu.size());
    	startMenu.add(menuItem);
    	menuItem = new MenuItem("Load Saves", loadSavesMenuScreen, menuItemBaseFile, menuItemHighlightedFile, startMenu.size());
    	startMenu.add(menuItem);
    	menuItem = new MenuItem("Upgrades", upgradesMenuScreen, menuItemBaseFile, menuItemHighlightedFile, startMenu.size());
    	startMenu.add(menuItem);
    	menuItem = new MenuItem("Settings", settingsMenuScreen, menuItemBaseFile, menuItemHighlightedFile, startMenu.size());
    	startMenu.add(menuItem);
    	menuItem = new MenuItem("Exit", -1, menuItemBaseFile, menuItemHighlightedFile, startMenu.size());
    	startMenu.add(menuItem);
    	checkAddScrollBar(startMenu);

    	menus.put(startMenuScreen, startMenu);
    	
    	Menu upgradesMenu = new Menu();
    	upgradesMenu.setParentId(startMenuScreen);
    	menuItem = new MenuItem("Back", startMenuScreen, menuItemBaseFile, menuItemHighlightedFile, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("test1", -1, menuItemBaseFile, menuItemHighlightedFile, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("test2", -1, menuItemBaseFile, menuItemHighlightedFile, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("test", -1, menuItemBaseFile, menuItemHighlightedFile, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("test", -1, menuItemBaseFile, menuItemHighlightedFile, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("test4", -1, menuItemBaseFile, menuItemHighlightedFile, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("test", -1, menuItemBaseFile, menuItemHighlightedFile, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	menuItem = new MenuItem("test6", -1, menuItemBaseFile, menuItemHighlightedFile, upgradesMenu.size());
    	upgradesMenu.add(menuItem);
    	checkAddScrollBar(upgradesMenu);

    	menus.put(upgradesMenuScreen, upgradesMenu);
    	
    	Menu settingsMenu = new Menu();
    	settingsMenu.setParentId(startMenuScreen);
    	menuItem = new MenuItem("Back", startMenuScreen, menuItemBaseFile, menuItemHighlightedFile, settingsMenu.size());
    	settingsMenu.add(menuItem);
    	menuItem = new MenuItem("Video", videoSettingsMenuScreen, menuItemBaseFile, menuItemHighlightedFile, settingsMenu.size());
    	settingsMenu.add(menuItem);
    	menuItem = new MenuItem("Sound", soundSettingsMenuScreen, menuItemBaseFile, menuItemHighlightedFile, settingsMenu.size());
    	settingsMenu.add(menuItem);
    	menuItem = new MenuItem("Controls", controlsSettingsMenuScreen, menuItemBaseFile, menuItemHighlightedFile, settingsMenu.size());
    	settingsMenu.add(menuItem);
    	checkAddScrollBar(settingsMenu);

    	menus.put(settingsMenuScreen, settingsMenu);
    	System.out.println("menus done");
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
    		MenuScrollBar scrollBar = new MenuScrollBar(scrollEdgeBaseFile, scrollBarBaseFile, scrollEdgeHighlightedFile, 
    				scrollBarSelectedFile, true, scrollBarHeight);
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
    	int[] targets = {-1};
    	ArrayList<StatusEffect> effects = new ArrayList<StatusEffect>();
    	playerIdle = new Action(0, 0, targets, effects, 0, playerIdleTime);
    	playerIdle.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerIdle.add(new Sprite(playerStartFile, playerStartX+20, playerStartY, playerWidth, playerHeight));
    	playerIdle.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerIdle.add(new Sprite(playerStartFile, playerStartX-20, playerStartY, playerWidth, playerHeight));
    	playerIdle.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerIdle.calculateTimePerSprite();
    	System.out.println("idle done");
    	
    	playerCleaveLeft = new Action(0, 0, targets, effects, 1, playerCleaveTime);
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX+20, playerStartY+20, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX+40, playerStartY+40, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX+60, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX+40, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX+20, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX-20, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX-40, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX-60, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX-40, playerStartY+40, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX-20, playerStartY+20, playerWidth, playerHeight));
    	playerCleaveLeft.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerCleaveLeft.calculateTimePerSprite();
    	
    	playerCleaveRight = new Action(0, 0, targets, effects, 1, playerCleaveTime);
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX-20, playerStartY+20, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX-40, playerStartY+40, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX-60, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX-40, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX-20, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX+20, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX+40, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX+60, playerStartY+60, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX+40, playerStartY+40, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX+20, playerStartY+20, playerWidth, playerHeight));
    	playerCleaveRight.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerCleaveRight.calculateTimePerSprite();
    	System.out.println("cleave done");
    	
    	playerStabLeft = new Action(0, 0, targets, effects, 2, playerStabTime);
    	playerStabLeft.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerStabLeft.add(new Sprite(playerStartFile, playerStartX+20, playerStartY-20, playerWidth, playerHeight));
    	playerStabLeft.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerStabLeft.add(new Sprite(playerStartFile, playerStartX-20, playerStartY+20, playerWidth, playerHeight));
    	playerStabLeft.add(new Sprite(playerStartFile, playerStartX-40, playerStartY+40, playerWidth, playerHeight));
    	playerStabLeft.add(new Sprite(playerStartFile, playerStartX-60, playerStartY+60, playerWidth, playerHeight));
    	playerStabLeft.add(new Sprite(playerStartFile, playerStartX-40, playerStartY+40, playerWidth, playerHeight));
    	playerStabLeft.add(new Sprite(playerStartFile, playerStartX-20, playerStartY+20, playerWidth, playerHeight));
    	playerStabLeft.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerStabLeft.calculateTimePerSprite();
    	
    	playerStabRight = new Action(0, 0, targets, effects, 2, playerStabTime);
    	playerStabRight.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerStabRight.add(new Sprite(playerStartFile, playerStartX-20, playerStartY-20, playerWidth, playerHeight));
    	playerStabRight.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerStabRight.add(new Sprite(playerStartFile, playerStartX+20, playerStartY+20, playerWidth, playerHeight));
    	playerStabRight.add(new Sprite(playerStartFile, playerStartX+40, playerStartY+40, playerWidth, playerHeight));
    	playerStabRight.add(new Sprite(playerStartFile, playerStartX+60, playerStartY+60, playerWidth, playerHeight));
    	playerStabRight.add(new Sprite(playerStartFile, playerStartX+40, playerStartY+40, playerWidth, playerHeight));
    	playerStabRight.add(new Sprite(playerStartFile, playerStartX+20, playerStartY+20, playerWidth, playerHeight));
    	playerStabRight.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerStabRight.calculateTimePerSprite();
    	
    	playerStabMid = new Action(0, 0, targets, effects, 2, playerStabTime);
    	playerStabMid.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerStabMid.add(new Sprite(playerStartFile, playerStartX, playerStartY-20, playerWidth, playerHeight));
    	playerStabMid.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerStabMid.add(new Sprite(playerStartFile, playerStartX, playerStartY+20, playerWidth, playerHeight));
    	playerStabMid.add(new Sprite(playerStartFile, playerStartX, playerStartY+40, playerWidth, playerHeight));
    	playerStabMid.add(new Sprite(playerStartFile, playerStartX, playerStartY+60, playerWidth, playerHeight));
    	playerStabMid.add(new Sprite(playerStartFile, playerStartX, playerStartY+40, playerWidth, playerHeight));
    	playerStabMid.add(new Sprite(playerStartFile, playerStartX, playerStartY+20, playerWidth, playerHeight));
    	playerStabMid.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerStabMid.calculateTimePerSprite();
    	
    	System.out.println("stab done");
    	playerBlockLeft = new Action(0, 0, targets, effects, 0, playerBlockTime);
    	playerBlockLeft.add(new Sprite(playerStartFile, playerStartX-20, playerStartY+20, playerWidth, playerHeight));
    	playerBlockLeft.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerBlockLeft.calculateTimePerSprite();
    	
    	playerBlockRight = new Action(0, 0, targets, effects, 0, playerBlockTime);
    	playerBlockRight.add(new Sprite(playerStartFile, playerStartX+20, playerStartY+20, playerWidth, playerHeight));
    	playerBlockRight.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerBlockRight.calculateTimePerSprite();
    	
    	playerBlockMid = new Action(0, 0, targets, effects, 0, playerBlockTime);
    	playerBlockMid.add(new Sprite(playerStartFile, playerStartX, playerStartY+20, playerWidth, playerHeight));
    	playerBlockMid.add(new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight));
    	playerBlockMid.calculateTimePerSprite();
    	
    	System.out.println("actions done");
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
    	}
    }
}
 