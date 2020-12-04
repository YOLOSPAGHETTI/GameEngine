package actions;
import java.util.ArrayList;

import ui.MobileSprite;

public class Animation extends ArrayList<MobileSprite> {
	private static final long serialVersionUID = 1L;
	public static final int UNINTERRUPTIBLE = 0;
	public static final int INTERRUPTIBLE = 1;
	
    private long duration;
    private int interruptLevel;
    private long timePerSprite;
    private long spriteTime;
    private MobileSprite currentSprite;
    private int animationIndex;

    protected Animation(long duration, int interruptLevel) {
        this.duration = duration;
        this.interruptLevel = interruptLevel;
    }
    
    public void calculateTimePerSprite() {
    	if(!this.isEmpty()) {
    		timePerSprite = duration/(long)this.size();
    	}
    }
    
    protected void setTimePerSprite(long time) {
    	timePerSprite = time;
    }

    public void runAnimation() {
        animationIndex = 0;
        currentSprite = this.get(animationIndex);
        spriteTime = 0;
    }
    
    public boolean checkNextSprite(long frameTime) {
    	spriteTime += frameTime;
    	// System.out.println(spriteTime);
    	if(spriteTime > timePerSprite) {
    		animationIndex++;
    		if(animationIndex < this.size()) {
    			currentSprite = this.get(animationIndex);
    			spriteTime = 0;
    		}
    		else {
    			runAnimation();
    			if(interruptLevel != INTERRUPTIBLE) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

    public MobileSprite getCurrentSprite() {
        return currentSprite;
    }
    
    void setIndex(int index) {
       this.animationIndex = index;
    }
    
    public int getInterruptLevel() {
		return interruptLevel;
	}
}