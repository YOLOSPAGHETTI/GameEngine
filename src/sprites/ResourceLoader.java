package sprites;

import java.awt.Dimension;

import java.awt.Toolkit;

public class ResourceLoader {
	public static int frameWidth;
	public static int frameHeight;
	public static final String bgFile = "";
	
	private static int playerWidth;
	private static int playerHeight;
	private static int playerStartX;
	private static int playerStartY;
	private static final String playerStartFile = "src/img/player/germanic-knight.jpg";
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
    
    public static void setupAnimations() {
    	playerIdleAnimation = new Animation(pac, playerIdleTime);
    	playerIdleAnimation.add(playerStartSprite);
    	playerIdleAnimation.add(playerNextSprite);
    	playerIdleAnimation.calculateTimePerSprite();
    }
}
 