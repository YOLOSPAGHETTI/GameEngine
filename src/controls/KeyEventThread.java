package controls;

import org.jnativehook.GlobalScreen;

public class KeyEventThread extends Thread  {
	private KeyboardListener myKeys = new KeyboardListener();
	Thread t;
	private boolean running;
	private String keyBuffer;
	private ControlHelper ch;
	
	public KeyEventThread(ControlHelper ch) {
		this.ch = ch;
	}
	
	public void start()
	{
		running = true;
		
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
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception in KeyCapture Thread");
			e.printStackTrace();
		}      
	}
	
	public void setRunning(boolean toRun) {
		running = toRun;
	}
	
	public boolean getRunning() {
		return running;
	}
}
