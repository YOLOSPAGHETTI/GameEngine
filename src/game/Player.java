package game;

import ui.ResourceLoader;

public class Player extends Entity {
    private ActionController controller;

    private int mouseStartX = -1;
    private int mouseStartY = -1;

    protected Player(ActionController controller) {
        this.controller = controller;
    }

    /*private boolean canAct() {
        return ps.currentAnimation == ps.idleAnimation;
    }*/

    public void setDragStartCoordinates(int mouseX, int mouseY) {
    	if(mouseStartX == -1) {
    		mouseStartX = mouseX;
    		mouseStartY = mouseY;
    	}
    }
    
    public ActionController getAnimationController() {
    	return controller;
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
	        	if(ResourceLoader.actionIsBlock(controller.getNextInQueue())) {
	    			controller.unqueue();
	    		}
	            float directionX = differenceX/distance;
	            float directionY = differenceY/distance;
	            //System.out.println("directionX: "+directionX);
	            //System.out.println("directionY: "+directionY);
	
	            if(directionY>=0.5) {
	                if(directionX>=-0.9 && directionX<-0.3) {
	                	//System.out.println("stab right");
	                	controller.queue(ResourceLoader.playerStabRight);
	                }
	                else if(directionX>=-0.3 && directionX<=0.3) {
	                	//System.out.println("stab mid");
	                	controller.queue(ResourceLoader.playerStabMid);
	                }
	                else if(directionX>0.3 && directionX<=0.9) {
	                	//System.out.println("stab left");
	                	controller.queue(ResourceLoader.playerStabLeft);
	                }
	            }
	            else if(directionX>=0.5) {
	            	//System.out.println("cleave left");
	            	controller.queue(ResourceLoader.playerCleaveLeft);
	            }
	            else if(-directionX>=0.5) {
	            	//System.out.println("cleave right");
	            	controller.queue(ResourceLoader.playerCleaveRight);
	            }
	        }
	        mouseStartX = -1;
	        mouseStartY = -1;
    	}
    	controller.cancelBlockActions();
    }
    
    public void block(int mouseX) {
    	int frameWidth = ResourceLoader.frameWidth;
    	if(mouseX < frameWidth/3) {
    		controller.queue(ResourceLoader.playerBlockLeft);
    	}
    	else if(mouseX >= frameWidth/3 && mouseX <= (2*frameWidth)/3) {
    		controller.queue(ResourceLoader.playerBlockMid);
    	}
    	else {
    		controller.queue(ResourceLoader.playerBlockRight);
    	}
    }
}