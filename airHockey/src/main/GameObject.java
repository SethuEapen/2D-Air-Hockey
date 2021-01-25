package main;

import java.awt.Graphics;

public abstract class GameObject { //abstract class for game entities 

	protected int x, y;
	protected ID id;
	protected int velX, velY, accX, accY, mass;
	
	public GameObject (int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x; 
	}
	public int getY() {
		return y;
	}
	public void setID(ID id) {
		this.id = id;
	}
	public ID getID() {
		return id; 
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getAccX() {
		return accX;
	}

	public void setAccX(int accX) {
		this.accX = accX;
	}

	public int getAccY() {
		return accY;
	}

	public void setAccY(int accY) {
		this.accY = accY;
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}
	
}
