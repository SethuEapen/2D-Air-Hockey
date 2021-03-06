package main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler { //steps through all the game objects and updates them individually
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		//PlayerMovement pm = new PlayerMovement();
		//pm.getCoords();
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

}
