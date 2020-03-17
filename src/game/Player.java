package game;

import java.util.LinkedList;
import java.util.Queue;

import ui.PlayerAnimationController;
import ui.ResourceLoader;

public class Player extends Entity {
    private PlayerAnimationController pac;

    private int mouseStartX = -1;
    private int mouseStartY = -1;
    private boolean mouseDown;
    private Queue<Action> actionQueue = new LinkedList<Action>();

    protected Player(PlayerAnimationController pac) {
        this.pac = pac;
    }

    /*private boolean canAct() {
        return ps.currentAnimation == ps.idleAnimation;
    }*/

    public void setDragStartCoordinates(int mouseX, int mouseY) {
    	if(mouseStartX == -1) {
    		if(ResourceLoader.actionIsBlock(actionQueue.peek())) {
    			actionQueue.remove();
    		}
    		mouseStartX = mouseX;
    		mouseStartY = mouseY;
    	}
    }
    
    public PlayerAnimationController getAnimationController() {
    	return pac;
    }

    public void determineAttack(int mouseX, int mouseY) {
        //System.out.println("DownTime: "+event.getDownTime());
        //System.out.println("EventTime: "+event.getEventTime());
        //System.out.println("Pressure: "+event.getPressure());
    	if(mouseStartX != -1) {
	        float differenceX = mouseStartX-mouseX;
	        float differenceY = mouseStartY-mouseY;
	        float distance = (float)Math.sqrt(differenceX*differenceX+differenceY*differenceY);
	
	        //System.out.println("distance: "+distance);
	
	        if(distance > 100) {
	            float directionX = differenceX/distance;
	            float directionY = differenceY/distance;
	            //System.out.println("directionX: "+directionX);
	            //System.out.println("directionY: "+directionY);
	
	            if(directionY>=0.5) {
	                if(directionX>=-0.9 && directionX<-0.3) {
	                	//System.out.println("stab right");
	                    actionQueue.add(ResourceLoader.playerStabRight);
	                }
	                else if(directionX>=-0.3 && directionX<=0.3) {
	                	//System.out.println("stab mid");
	                	actionQueue.add(ResourceLoader.playerStabMid);
	                }
	                else if(directionX>0.3 && directionX<=0.9) {
	                	//System.out.println("stab left");
	                	actionQueue.add(ResourceLoader.playerStabLeft);
	                }
	            }
	            else if(directionX>=0.5) {
	            	//System.out.println("cleave left");
	            	actionQueue.add(ResourceLoader.playerCleaveLeft);
	            }
	            else if(-directionX>=0.5) {
	            	//System.out.println("cleave right");
	            	actionQueue.add(ResourceLoader.playerCleaveRight);
	            }
	        }
	        mouseStartX = -1;
	        mouseStartY = -1;
    	}
    	mouseDown = false;
    }
    
    public void block(int mouseX) {
    	int frameWidth = ResourceLoader.frameWidth;
    	if(mouseX < frameWidth/3) {
    		actionQueue.add(ResourceLoader.playerBlockLeft);
    	}
    	else if(mouseX >= frameWidth/3 && mouseX <= (2*frameWidth)/3) {
    		actionQueue.add(ResourceLoader.playerBlockMid);
    	}
    	else {
    		actionQueue.add(ResourceLoader.playerBlockRight);
    	}
    	mouseDown = true;
    }
}