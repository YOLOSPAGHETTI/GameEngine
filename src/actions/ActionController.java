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
    	currentAction = null;
    	idleAction = null;
    }
	
    public ActionController(Action idleAction) {
    	this.idleAction = idleAction;
    	currentAction = idleAction;
    	
    	currentAction.runAnimation();
    }
    
    public boolean checkNextAction(long frameTime) {
    	if(currentAction.checkNextSprite(frameTime) || 
    			(!actionQueue.isEmpty() && currentAction.getInterruptLevel() == 0)) {
    		runNextAction();
    		return true;
    	}
    	return false;
    }
    
    public void runNextAction() {
    	//System.out.println(currentAction);
    	
    	currentAction = actionQueue.poll();
    	
    	if(currentAction == null) {
        	currentAction = idleAction;
        }
        
    	if(currentAction != null) {
    		currentAction.execute();
    	}
    }

    public Action getCurrentAction() {
    	return currentAction;
    }
    
    public MobileSprite getCurrentSprite() {
    	if(currentAction == null) {
    		return null;
    	}
        return currentAction.getCurrentSprite();
    }
    
    Queue<Action> getActionQueue() {
    	return actionQueue;
    }
    
    public void queue(Action action, ControlState controlState) {
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
}
