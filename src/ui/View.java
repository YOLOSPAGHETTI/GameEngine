package ui;

import java.util.ArrayList;

import controls.Control;
import game.Entity;
import ui.menu.Menu;

public class View {
	private final int id;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Control> controls = new ArrayList<Control>();
	
	public View(int id) {
		this.id = id;
	}
	
	public View(int id, ArrayList<Entity> entities, ArrayList<Control> controls) {
		this.id = id;
		this.entities = entities;
		this.controls = controls;
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void addControl(Control control) {
		controls.add(control);
	}
	
	public void setControls(ArrayList<Control> controls) {
		this.controls.addAll(controls);
	}
	
	public int getId() {
		return id;
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public ArrayList<Control> getControls() {
		return controls;
	}
}
