package ui;
import java.util.ArrayList;

public class Animation extends ArrayList<MobileSprite> {
<<<<<<< HEAD
	private static final long serialVersionUID = 1L;
	public static final int UNINTERRUPTIBLE = 0;
	public static final int INTERRUPTIBLE = 1;
	
=======
>>>>>>> branch 'master' of https://github.com/YOLOSPAGHETTI/GameEngine.git
    private long duration;
    private int interruptLevel;
    private long timePerSprite;
    private long spriteTime;
    private MobileSprite currentSprite;
    private int index;

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
       this.index = index;
    }
    
    public int getInterruptLevel() {
		return interruptLevel;
	}
}