package controls;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jnativehook.GlobalScreen;


public class KeyEventThread extends Thread  {
	private KeyboardListener myKeys = new KeyboardListener();
	Thread t;
	public boolean running;
	private String keyBuffer;
	private ControlHelper ch;
	
	public KeyEventThread(ControlHelper ch) {
		this.ch = ch;
		running = true;
		start();
	}
	
	public void start()
	{
	    t = new Thread(this);
	    t.start();
	    try {
	    	myKeys.turnOffLogger();
	        GlobalScreen.registerNativeHook();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(myKeys);
	}
	
	public void run()
	{
		try {
			while (running)
			{
				Thread.sleep(10);
				keyBuffer = myKeys.readKeyBuffer();
				if (!keyBuffer.equals("-1"))
				{
					//System.out.println(keyBuffer);
					ch.checkActions(keyBuffer);
					//System.out.println(firing);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception in KeyCapture Thread");
			e.printStackTrace();
		}      
	}
}
