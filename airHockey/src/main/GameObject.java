package main;

import java.awt.Graphics;

public abstract class GameObject { //abstract class for game entities 

	protected int x, y;
	protected ID id;
	protected double velX, velY, accX, accY;
	protected int mass;
	
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

	public double getVelX() {
		return velX;
	}

	public void setVelX(long velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(long velY) {
		this.velY = velY;
	}

	public double getAccX() {
		return accX;
	}

	public void setAccX(long accX) {
		this.accX = accX;
	}

	public double getAccY() {
		return accY;
	}

	public void setAccY(long accY) {
		this.accY = accY;
	}
	
}
