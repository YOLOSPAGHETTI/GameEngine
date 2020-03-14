package ui;

public class PlayerAnimationController extends AnimationController {
	
    public PlayerAnimationController() {
    }
    
    public void initialize() {
    	idleAnimation = ResourceLoader.playerIdleAnimation;
        currentAnimation = idleAnimation;
        runAnimation(currentAnimation);
    }
}
