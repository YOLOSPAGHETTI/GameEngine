package game;

import java.util.LinkedList;
import java.util.Queue;

import ui.MobileSprite;
import ui.Sprite;

public class ActionController {
	private Action currentAction;
	private Action idleAction;
	private Action deathAction;
	private Queue<Action> actionQueue = new LinkedList<Action>();

    public ActionController(Action idleAction, Action deathAction) {
    	this.idleAction = idleAction;
    	this.deathAction = deathAction;
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
    	if(currentAction == deathAction) {
    		currentAction = null;
    		return;
    	}
    	
    	currentAction = actionQueue.poll();
    	
    	if(currentAction == null) {
        	currentAction = idleAction;
        }
        
        currentAction.execute();
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
    
    void queue(Action action) {
    	actionQueue.add(action);
    }
    
    void unqueue(Action action) {
    	actionQueue.remove(action);
    }
    
    void unqueue() {
    	actionQueue.remove();
    }
    
    Action getNextInQueue() {
    	return actionQueue.peek();
    }
    
    void cancelAllActions() {
    	actionQueue.clear();
    }
    
    public void startDeathAction() {
    	//System.out.println(deathAction);
    	cancelAllActions();
    	currentAction = deathAction;
    	currentAction.runAnimation();
    }
}
