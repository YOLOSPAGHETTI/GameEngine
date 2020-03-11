package sprites;

import java.util.ArrayList;

public class AnimationController {
    ArrayList<ArrayList<Sprite>> sprites;
    Animation idleAnimation;
    Animation currentAnimation;
    Animation nextAnimation;

    public boolean runAnimation(Animation animation) {
        if(currentAnimation == idleAnimation) {
            animation.runAnimation();
            currentAnimation = animation;
            if(currentAnimation != idleAnimation) {
                //System.out.println("Current: "+currentAnimation);
            }
            return true;
        }
        else {
            nextAnimation = animation;
            return false;
        }
    }
    
    public void checkNextAnimation(long frameTime) {
    	if(currentAnimation.checkNextSprite(frameTime)) {
    		currentAnimation = idleAnimation;
    		currentAnimation.setIndex(0);
    		runAnimation(currentAnimation);
    	}
    }

    public void onAnimationStop() {
        if(currentAnimation != idleAnimation) {
            //System.out.println("Stop: "+currentAnimation);
        }
        if (nextAnimation == null) {
            currentAnimation = idleAnimation;
            runAnimation(idleAnimation);
        } else {
            runAnimation(nextAnimation);
            nextAnimation = null;
        }
    }

    public Animation getCurrentAnimation() {
    	return currentAnimation;
    }
    
    public Sprite getCurrentSprite() {
        return currentAnimation.getCurrentSprite();
    }
}
