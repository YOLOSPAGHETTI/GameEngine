package ui;

import java.awt.image.BufferedImage;

public class MenuScrollBar {
	private boolean right;
	private MobileSprite edgeBaseSprite;
	private MobileSprite edgeHighlightedSprite;
	private MobileSprite barBaseSprite;
	private MobileSprite barSelectedSprite;
	private boolean selected;
	private int selectedY;
	private int maxDistance;
	
	public MenuScrollBar(BufferedImage fileSourceEdgeBase, BufferedImage fileSourceBarBase, BufferedImage fileSourceEdgeHighlighted, 
			BufferedImage fileSourceBarSelected, boolean right, int z, int height) {
		edgeBaseSprite = new MobileSprite(fileSourceEdgeBase, right, z);
		edgeHighlightedSprite = new MobileSprite(fileSourceEdgeHighlighted, right, z);
		barBaseSprite = new MobileSprite(fileSourceBarBase, right, z, height, edgeBaseSprite.getWidth());
		barSelectedSprite = new MobileSprite(fileSourceBarSelected, right, z, height, edgeBaseSprite.getWidth());
		
		this.right = right;
		
		maxDistance = FrameController.frameHeight - height;
	}
	
	Sprite getBaseBarSprite() {
		return barBaseSprite;
	}
	
	Sprite getSelectedBarSprite() {
		return barSelectedSprite;
	}
	
	Sprite getBaseEdgeSprite() {
		return edgeBaseSprite;
	}
	
	Sprite getHighlightedEdgeSprite() {
		return edgeHighlightedSprite;
	}
	
	boolean getRight() {
		return right;
	}
	
	boolean isMouseOverEdge(int mouseX, int mouseY) {
		return isMouseOver(mouseX, mouseY, edgeBaseSprite.getX(), edgeBaseSprite.getY(), edgeBaseSprite.getWidth(), edgeBaseSprite.getHeight());
	}
	
	boolean isMouseOverBar(int mouseX, int mouseY) {
		return isMouseOver(mouseX, mouseY, barBaseSprite.getX(), barBaseSprite.getY(), barBaseSprite.getWidth(), barBaseSprite.getHeight());
	}
	
	private boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
		//System.out.println("mouseX: " + mouseX + " mouseY: " + mouseY);
		//System.out.println("startx: " + x + " starty: " + y + " endx: " + (x+width) + " endy: " + (y+height));
		if(mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
			//System.out.println(true);
			return true;
		}
		return false;
	}
	
	void setSelected(boolean selected, int mouseY) {
		this.selected = selected;
		selectedY = mouseY - barBaseSprite.getY();
	}
	
	boolean isSelected() {
		return selected;
	}
	
	int getSelectedY() {
		return selectedY;
	}
	
	int move(int y) {
		if(selectedY > -1) {
			int oldY = barBaseSprite.getY();
			int newY = y - selectedY;
			barBaseSprite.setY(newY);
			barSelectedSprite.setY(newY);
			return oldY - barBaseSprite.getY();
		}
		else {
			int oldY = barBaseSprite.getY();
			int newY = y - barBaseSprite.getHeight()/2;
			barBaseSprite.setY(newY);
			barSelectedSprite.setY(newY);
			return oldY - barBaseSprite.getY();
		}
	}
	
	void relocate(int distance) {
		barBaseSprite.setY(barBaseSprite.getY()-distance);
	}
	
	int getMaxDistance() {
		return maxDistance;
	}
}
