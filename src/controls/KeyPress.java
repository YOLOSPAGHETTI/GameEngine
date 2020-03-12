package controls;
public class KeyPress {

	private int Key;
	private int pressOrRelease; // 1 for press 0 for release
	
	public KeyPress(int pKey, int pPressOrRelease)
	{
		Key = pKey;
		pressOrRelease = pPressOrRelease;
	}
	
	public int getKey()
	{
		return Key;
	}
	
	public int pressOrRelease()
	{
		return pressOrRelease;
	}
	
}
