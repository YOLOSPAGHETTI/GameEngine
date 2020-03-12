package controls;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class KeyboardListener implements NativeKeyListener {

	private HashMap<Integer, KeyPress> keyPressBuffer = new HashMap<Integer, KeyPress>();
	private Integer writeBufferCount = 1;
	private Integer readBufferCount = 1;
	
	public void nativeKeyPressed(NativeKeyEvent e) {

		keyPressBuffer.remove(writeBufferCount);
		keyPressBuffer.put(writeBufferCount, new KeyPress(e.getKeyCode(), 1));
		writeBufferCount++;
		
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
			GlobalScreen.unregisterNativeHook();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public String readKeyBuffer()
	{
		String keyVal = "-1";
		if (keyPressBuffer.containsKey(readBufferCount))
		{
		keyVal = NativeKeyEvent.getKeyText(keyPressBuffer.get(readBufferCount).getKey()) + ":" + keyPressBuffer.get(readBufferCount).pressOrRelease();
		keyPressBuffer.remove(readBufferCount);
		//System.out.println("at buffer [" + readBufferCount + "]");
		readBufferCount++;
		}
		return keyVal;
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
	
		keyPressBuffer.remove(writeBufferCount);
		keyPressBuffer.put(writeBufferCount, new KeyPress(e.getKeyCode(), 0));
		writeBufferCount++;

	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		//System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void turnOffLogger()
	{
		// Clear previous logging configurations.
		LogManager.getLogManager().reset();

		// Get the logger for "org.jnativehook" and set the level to off.
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
	}
	
}