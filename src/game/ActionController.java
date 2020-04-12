package game;

import java.util.LinkedList;
import java.util.Queue;

import ui.MobileSprite;
<<<<<<< HEAD
=======
import ui.Sprite;
>>>>>>> branch 'master' of https://github.com/YOLOSPAGHETTI/GameEngine.git

public class ActionController {
<<<<<<< HEAD
	private AnimatedAction currentAction;
	private AnimatedAction idleAction;
	private Queue<AnimatedAction> actionQueue = new LinkedList<AnimatedAction>();
=======
	private Action currentAction;
	private Action idleAction;
	private Action deathAction;
	private Queue<Action> actionQueue = new LinkedList<Action>();
>>>>>>> branch 'master' of https://github.com/YOLOSPAGHETTI/GameEngine.git

<<<<<<< HEAD
    public ActionController(AnimatedAction idleAction) {
=======
    public ActionController(Action idleAction, Action deathAction) {
>>>>>>> branch 'master' of https://github.com/YOLOSPAGHETTI/GameEngine.git
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
<<<<<<< HEAD
=======
    	if(currentAction == deathAction) {
    		currentAction = null;
    		return;
    	}
>>>>>>> branch 'master' of https://github.com/YOLOSPAGHETTI/GameEngine.git
    	
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
<<<<<<< HEAD
=======
    }
    
    public void startDeathAction() {
    	//System.out.println(deathAction);
    	cancelAllActions();
    	currentAction = deathAction;
    	currentAction.runAnimation();
>>>>>>> branch 'master' of https://github.com/YOLOSPAGHETTI/GameEngine.git
    }
}
