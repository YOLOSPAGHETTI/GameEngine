package ui;

public class PlayerAnimationController extends AnimationController {
	
    public PlayerAnimationController() {
    }
    
    public void initialize() {
    	idleAnimation = ResourceLoader.playerIdle;
        currentAnimation = idleAnimation;
        runAnimation(currentAnimation);
    }
}
