package game;

import java.util.LinkedList;
import java.util.Queue;

import ui.MobileSprite;

public class ActionController {
	private AnimatedAction currentAction;
	private AnimatedAction idleAction;
	private Queue<AnimatedAction> actionQueue = new LinkedList<AnimatedAction>();

    public ActionController(AnimatedAction idleAction) {
    	this.idleAction = idleAction;
    	currentAction = idleAction;
    	
    	currentAction.runAnimation();
    }
    
    public boolean checkNextAction(long frameTime) {
    	if(currentAction.checkNextSprite(frameTime) || 
    			(!actionQueue.isEmpty() && currentAction.getInterruptLevel() == 0)) {
    		//currentAction.execute();
    		runNextAction();
    		return true;
    	}
    	return false;
    }
    
    void runNextAction() {
    	//System.out.println(currentAction);
    	
    	currentAction = actionQueue.poll();
    	
    	if(currentAction == null) {
        	currentAction = idleAction;
        }
        
        currentAction.execute();
    }

    public AnimatedAction getCurrentAction() {
    	return currentAction;
    }
    
    public MobileSprite getCurrentSprite() {
    	if(currentAction == null) {
    		return null;
    	}
        return currentAction.getCurrentSprite();
    }
    
    Queue<AnimatedAction> getActionQueue() {
    	return actionQueue;
    }
    
    void queue(AnimatedAction action) {
    	actionQueue.add(action);
    }
    
    void unqueue(AnimatedAction action) {
    	actionQueue.remove(action);
    }
    
    void unqueue() {
    	actionQueue.remove();
    }
    
    AnimatedAction getNextInQueue() {
    	return actionQueue.peek();
    }
    
    void cancelAllActions() {
    	actionQueue.clear();
    }
}
