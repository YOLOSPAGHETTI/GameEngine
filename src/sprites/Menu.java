package sprites;

import java.util.ArrayList;

public class Menu extends ArrayList<MenuItem> {
	public int getLastHighlighted() {
		for(MenuItem item : this) {
			if(item.getHighlighted()) {
				return item.getId();
			}
		}
		return -1;
	}
}
