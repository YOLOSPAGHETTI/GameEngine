package game;

import java.util.LinkedList;
import java.util.Queue;

import ui.ResourceLoader;
import ui.Sprite;

public class ActionController {
    Action currentAction;
    private Action idleAction;
    private Queue<Action> actionQueue = new LinkedList<Action>();

    public ActionController(Action idleAction) {
    	this.idleAction = idleAction;
    	currentAction = idleAction;
    	
    	currentAction.runAnimation();
    }
    
    public void checkNextAnimation(long frameTime) {
    	//System.out.println("queue empty: " + actionQueue.isEmpty());
    	//System.out.println("interrupt level: " + currentAction.getInterruptLevel());
    	if(currentAction.checkNextSprite(frameTime) || (!actionQueue.isEmpty() && currentAction.getInterruptLevel() == 0)) {
    		currentAction.dealDamage();
    		runNextAnimation();
    	}
    }
    
    private void runNextAnimation() {
    	currentAction = actionQueue.poll();
        if(currentAction == null) {
        	currentAction = idleAction;
        }
        
        currentAction.runAnimation();
    }

    public Action getCurrentAction() {
    	return currentAction;
    }
    
    public Sprite getCurrentSprite() {
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
    
    void cancelBlockActions() {
    	actionQueue.remove(ResourceLoader.playerBlockLeft);
    	actionQueue.remove(ResourceLoader.playerBlockRight);
    	actionQueue.remove(ResourceLoader.playerBlockMid);
    	
    	if(ResourceLoader.actionIsBlock(currentAction)) {
    		runNextAnimation();
    	}
    }
}
