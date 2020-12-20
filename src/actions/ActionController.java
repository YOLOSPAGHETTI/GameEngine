package actions;

import java.util.LinkedList;
import java.util.Queue;

import controls.ControlState;
import ui.MobileSprite;

public class ActionController {
	private Action currentAction;
	private Action idleAction;
	private Queue<Action> actionQueue = new LinkedList<Action>();

	public ActionController() {
    	currentAction = new Action();
    	idleAction = new Action();
    }
	
    public ActionController(Action idleAction) {
    	setIdleAction(idleAction);
    }
    
    public boolean checkNextAction(long frameTime) {
    	if(currentAction != null) {
    		if(currentAction.hasAnimation()) {
    			Animation animation = currentAction.getAnimation();
    			if(animation.checkNextSprite(frameTime)) {
    				currentAction.animationComplete();
    				runNextAction();
    	    		return true;
    			}
    		}
    		if(!actionQueue.isEmpty() && currentAction.getInterruptLevel() == 0) {
	    		runNextAction();
	    		return true;
	    	}
    	}
    	return false;
    }
    
    public void runNextAction() {
    	//System.out.println("Current action complete: " + currentAction);
    	
    	currentAction = actionQueue.poll();
    	
    	if(currentAction == null) {
        	currentAction = idleAction;
        }
        
    	//System.out.println("Running new action: " + currentAction);
    	if(currentAction != null) {
    		currentAction.run();
    	}
    }

    public Action getCurrentAction() {
    	return currentAction;
    }
    
    public MobileSprite getCurrentSprite() {
    	if(currentAction == null || !currentAction.hasAnimation()) {
    		//System.out.println("No active sprite for this controller: " + this + " : Returning a blank sprite");
    		return new MobileSprite();
    	}
        return currentAction.getAnimation().getCurrentSprite();
    }
    
    Queue<Action> getActionQueue() {
    	return actionQueue;
    }
    
    public void queue(Action action, ControlState controlState) {
    	//System.out.println("Add action to queue: " + action);
    	action.setControlState(controlState);
    	actionQueue.add(action);
    }
    
    public void unqueue(Action action) {
    	actionQueue.remove(action);
    }
    
    public void unqueue() {
    	actionQueue.remove();
    }
    
    Action getNextInQueue() {
    	return actionQueue.peek();
    }
    
    void cancelAllActions() {
    	actionQueue.clear();
    }
    
    public void setIdleAction(Action action) {
    	idleAction = action;
    	
		currentAction = idleAction;
    	if(currentAction.hasAnimation()) {
    		Animation animation = currentAction.getAnimation();
    		animation.runAnimation();
    	}
	}
}
