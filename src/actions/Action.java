package actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import controls.ControlState;

public class Action extends Animation {
	private static final long serialVersionUID = 1L;
	private Method action;
	private ControlState controlState;
	private ActionController ac;
	
	public Action(Class<?> calling, ActionController ac, String methodName) {
		super(0, 0);
		this.ac = ac;
		try {
			action = calling.getDeclaredMethod(methodName, ControlState.class);
			System.out.println(action);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Action(long duration, int interruptLevel, Class<?> calling, String methodName) {
		super(duration, interruptLevel);
		try {
			action = calling.getDeclaredMethod(methodName, ControlState.class);
			System.out.println(action);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ControlState getControlState() {
		return controlState;
	}
	
	public void setControlState(ControlState controlState) {
		this.controlState = controlState;
	}
	
	public void execute() {
		try {
			action.invoke(ac, controlState);
		}
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}