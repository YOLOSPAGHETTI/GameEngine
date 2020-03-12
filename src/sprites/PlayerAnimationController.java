package sprites;

import java.awt.Image;
import java.util.ArrayList;

public class PlayerAnimationController extends AnimationController {
	
    public PlayerAnimationController() {
    }
    
    public void initialize() {
    	idleAnimation = ResourceLoader.playerIdleAnimation;
        currentAnimation = idleAnimation;
        runAnimation(currentAnimation);
    }
}
