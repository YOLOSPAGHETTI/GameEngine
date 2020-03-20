package game;

import ui.HealthBar;
import ui.ResourceLoader;
import ui.Sprite;

public class Player extends Entity {
    private ActionController controller;

    private int mouseStartX = -1;
    private int mouseStartY = -1;

    protected Player(ActionController controller) {
        this.controller = controller;
        setHp(ResourceLoader.baseHealth);
        HealthBar healthBar = new HealthBar(ResourceLoader.baseHealth, ResourceLoader.healthBarPositive, ResourceLoader.healthBarNegative);
        setHealthBar(healthBar);
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
    
    public ActionController getActionController() {
    	return controller;
    }

    public void determineAttack(int mouseX, int mouseY) {
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
    	Sprite sprite = controller.getCurrentSprite();
    	int spriteX = sprite.getX();
    	int spriteWidth = sprite.getWidth();
    	if(mouseX < spriteX) {
    		//System.out.println("block left");
    		controller.queue(ResourceLoader.playerBlockLeft);
    	}
    	else if(mouseX >= spriteX && mouseX <= spriteX+spriteWidth) {
    		//System.out.println("block mid");
    		controller.queue(ResourceLoader.playerBlockMid);
    	}
    	else {
    		//System.out.println("block right");
    		controller.queue(ResourceLoader.playerBlockRight);
    	}
    }
}