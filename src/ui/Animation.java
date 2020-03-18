package ui;
import java.util.ArrayList;

public class Animation extends ArrayList<Sprite> {
    private long duration;
    private int interruptLevel;
    private long timePerSprite;
    private long spriteTime;
    private Sprite currentSprite;
    private int index;

    protected Animation(long duration, int interruptLevel) {
        this.duration = duration;
        this.interruptLevel = interruptLevel;
    }
    
    protected void calculateTimePerSprite() {
    	if(!this.isEmpty()) {
    		timePerSprite = duration/(long)this.size();
    	}
    }
    
    protected void setTimePerSprite(long time) {
    	timePerSprite = time;
    }

    public void runAnimation() {
        index = 0;
        currentSprite = this.get(index);
        spriteTime = 0;
    }
    
    public boolean checkNextSprite(long frameTime) {
    	spriteTime += frameTime;
    	// System.out.println(spriteTime);
    	if(spriteTime > timePerSprite) {
    		index++;
    		if(index < this.size()) {
    			currentSprite = this.get(index);
    			spriteTime = 0;
    		}
    		else {
    			runAnimation();
    			if(interruptLevel != 0) {
    				return true;
    			}
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
    
    public int getInterruptLevel() {
		return interruptLevel;
	}
}