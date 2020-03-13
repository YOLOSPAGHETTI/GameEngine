package sprites;

import java.awt.Dimension;

import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;

import sprites.Menu;
import sprites.MenuItem;

public final class ResourceLoader {
	private static final String workingDir = System.getProperty("user.dir");
	private static final String pathSeparator = File.separator;
	
	// Screen Ids
	public static final int startMenuScreen = 0;
	public static final int inGameScreen = 1;
	public static final int upgradesMenuScreen = 2;
	public static final int settingsMenuScreen = 3;
	
	// Screen Size
	public static int frameWidth;
	public static int frameHeight;
	public static final String bgFile = "";
	
	// Menus
	private static final int minScrollBarHeight = 20;
	private static String menuItemBaseFile = workingDir + pathSeparator + "src/img/menu/menuitem.jpg";
	private static String menuItemHighlightedFile = workingDir + pathSeparator + "src/img/menu/highlightedmenuitem.jpg";
	private static String scrollEdgeBaseFile = workingDir + pathSeparator + "src/img/menu/scrolledge.jpg";
	private static String scrollEdgeHighlightedFile = workingDir + pathSeparator + "src/img/menu/highlightedscrolledge.jpg";
	private static String scrollBarBaseFile = workingDir + pathSeparator + "src/img/menu/scrollbar.jpg";
	private static String scrollBarSelectedFile = workingDir + pathSeparator + "src/img/menu/selectedscrollbar.jpg";
	
	// Player data
	private static int playerWidth;
	private static int playerHeight;
	private static int playerStartX;
	private static int playerStartY;
	private static String playerStartFile = workingDir + pathSeparator + "src/img/player/germanic-knight.jpg";
	private static Sprite playerStartSprite;
	private static Sprite playerNextSprite;
	
	public static final PlayerAnimationController pac = new PlayerAnimationController();
	private static final long playerIdleTime = 1000;
    private static final long playerCleaveTime = 500;
    private static final long playerStabTime = 800;
    private static final long playerBlockTime = 100;
    private static final long playerStunTime = 250;
    public static Animation playerCleaveLeftAnimation;
    public static Animation playerCleaveRightAnimation;
    public static Animation playerStabLeftAnimation;
    public static Animation playerStabMidAnimation;
    public static Animation playerStabRightAnimation;
    public static Animation playerBlockLeftAnimation;
    public static Animation playerBlockMidAnimation;
    public static Animation playerBlockRightAnimation;
    public static Animation playerStunnedAnimation;
    public static Animation playerIdleAnimation;

    public static void useNativeResolution() {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	changeResolution(screenSize.width, screenSize.height);
    }
    
    public static void changeResolution(int width, int height) {
    	frameWidth = width;
    	frameHeight = height;
    	
    	playerWidth = width/10;
    	playerHeight = height/7;
    	playerStartX = width/2 - playerWidth/2;
    	playerStartY = height - playerHeight - playerHeight/2;
    	
    	playerStartSprite = new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight);
    	playerNextSprite = new Sprite(playerStartFile, playerStartX+10, playerStartY, playerWidth, playerHeight);
    }
    
    public static boolean screenIsMenu(int screen) {
    	if(screen == inGameScreen) {
    		return false;
    	}
    	return true;
    }
    
    public static HashMap<Integer,Menu> setupMenus() {
    	HashMap<Integer,Menu> menus = new HashMap<Integer,Menu>();
    	Menu startMenu = new Menu();
    	MenuItem menuItem = new MenuItem("Play", inGameScreen, menuItemBaseFile, menuItemHighlightedFile, startMenu.size());
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
    	menuItem = new MenuItem("test", -1, menuItemBaseFile, menuItemHighlightedFile, upgradesMenu.size());
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
    	
    	return menus;
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
    
    public static void setupAnimations() {
    	playerIdleAnimation = new Animation(pac, playerIdleTime);
    	playerIdleAnimation.add(playerStartSprite);
    	playerIdleAnimation.add(playerNextSprite);
    	playerIdleAnimation.calculateTimePerSprite();
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
 