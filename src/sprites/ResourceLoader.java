package sprites;

public class ResourceLoader {
	public static final int frameWidth = 1000;
	public static final int frameHeight = 750;
	public static final String bgFile = "";
	
	private static final int playerWidth = 100;
	private static final int playerHeight = 100;
	private static final int playerStartX = frameWidth/2 - 100;
	private static final int playerStartY = frameHeight - 100;
	private static final String playerStartFile = "src/img/player/germanic-knight.jpg";
	private static final Sprite playerStartSprite = new Sprite(playerStartFile, playerStartX, playerStartY, playerWidth, playerHeight);
	private static final Sprite playerNextSprite = new Sprite(playerStartFile, playerStartX+10, playerStartY, playerWidth, playerHeight);
	
	public static final PlayerAnimationController pac = new PlayerAnimationController();
	private static final long playerIdleTime = 1000;
    private static final long playerCleaveTime = 500;
    private static final long playerStabTime = 800;
    private static final long playerBlockTime = 100;
    private static final long playerStunTime = 250;
    public Animation playerCleaveLeftAnimation;
    public Animation playerCleaveRightAnimation;
    public Animation playerStabLeftAnimation;
    public Animation playerStabMidAnimation;
    public Animation playerStabRightAnimation;
    public Animation playerBlockLeftAnimation;
    public Animation playerBlockMidAnimation;
    public Animation playerBlockRightAnimation;
    public Animation playerStunnedAnimation;
    public static Animation playerIdleAnimation;
 
    public static void setupAnimations() {
    	playerIdleAnimation = new Animation(pac, playerIdleTime);
    	playerIdleAnimation.add(playerStartSprite);
    	playerIdleAnimation.add(playerNextSprite);
    	playerIdleAnimation.calculateTimePerSprite();
    }
}
 