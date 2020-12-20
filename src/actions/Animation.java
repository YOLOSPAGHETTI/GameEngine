package actions;
import java.util.ArrayList;

import ui.MobileSprite;

public class Animation {	
	private ArrayList<MobileSprite> sprites = new ArrayList<MobileSprite>();
	private MobileSprite currentSprite;
    private long duration;
    private long timePerSprite;
    private long spriteTime;
    private int spriteIndex;

    public Animation(long duration, ArrayList<MobileSprite> sprites) {
        this.duration = duration;
        this.sprites.addAll(sprites);
        calculateTimePerSprite();
    }
    
    public Animation(long duration) {
        this.duration = duration;
        this.currentSprite = new MobileSprite();
        sprites.add(currentSprite);
        calculateTimePerSprite();
    }
    
    public void calculateTimePerSprite() {
    	if(!sprites.isEmpty()) {
    		timePerSprite = duration/(long)sprites.size();
    	}
    }
    
    protected void setTimePerSprite(long time) {
    	timePerSprite = time;
    }

    public void runAnimation() {
        spriteIndex = 0;
        currentSprite = sprites.get(spriteIndex);
        spriteTime = 0;
    }
    
    public boolean checkNextSprite(long frameTime) {
    	spriteTime += frameTime;
    	// System.out.println(spriteTime);
    	if(spriteTime > timePerSprite) {
    		spriteIndex++;
    		if(spriteIndex < sprites.size()) {
    			currentSprite = sprites.get(spriteIndex);
    			spriteTime = 0;
    		}
    		else {
    			runAnimation();
    		}
    	}
    	return false;
    }

    public MobileSprite getCurrentSprite() {
        return currentSprite;
    }
    
    void setIndex(int index) {
       this.spriteIndex = index;
    }
    
    public void addSprite(MobileSprite sprite) {
    	sprites.add(sprite);
    	calculateTimePerSprite();
    }
    
    public void setSprites(ArrayList<MobileSprite> sprites) {
    	this.sprites.addAll(sprites);
        calculateTimePerSprite();
    }
}