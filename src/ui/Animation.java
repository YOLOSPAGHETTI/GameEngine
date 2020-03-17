package ui;
import java.util.ArrayList;

public class Animation extends ArrayList<Sprite> {
    private long duration;
    private long timePerSprite;
    private long spriteTime;
    private Sprite currentSprite;
    private int index;

    protected Animation(long duration) {
        this.duration = duration;
    }
    
    protected void calculateTimePerSprite() {
    	if(!this.isEmpty()) {
    		timePerSprite = duration/(long)this.size();
    	}
    }

    protected void runAnimation() {
        index = 0;
        currentSprite = this.get(index);
    }
    
    protected boolean checkNextSprite(long frameTime) {
    	spriteTime += frameTime;
    	// System.out.println(spriteTime);
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

    protected Sprite getCurrentSprite() {
        return currentSprite;
    }
    
    void setIndex(int index) {
       this.index = index;
    }
}