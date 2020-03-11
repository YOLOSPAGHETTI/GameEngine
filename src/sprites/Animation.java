package sprites;
import java.util.ArrayList;

public class Animation extends ArrayList<Sprite> {
    private AnimationController controller;
    private long duration;
    private long timePerSprite;
    private long spriteTime;
    private Sprite currentSprite;
    private int index;

    Animation(AnimationController controller, long duration) {
        this.controller = controller;
        this.duration = duration;
    }
    
    public void calculateTimePerSprite() {
    	if(!this.isEmpty()) {
    		timePerSprite = duration/(long)this.size();
    	}
    }

    public void runAnimation() {
        index = 0;
        currentSprite = this.get(index);
    }
    
    public boolean checkNextSprite(long frameTime) {
    	spriteTime += frameTime;
    	System.out.println(spriteTime);
    	if(spriteTime > timePerSprite) {
    		index++;
    		if(index < this.size()) {
    			currentSprite = this.get(index);
    			spriteTime = 0;
    		}
    		else {
    			spriteTime = 0;
    			return true;
    		}
    	}
    	return false;
    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }
    
    void setIndex(int index) {
       this.index = index;
    }
}