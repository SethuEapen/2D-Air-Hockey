package main;

import java.awt.Color;
import java.awt.Graphics;

public class AI extends GameObject {

	Handler handler;

	long lastTime = System.nanoTime();
	long currentTime;
	double timePassed;
	int lastx = 0, lasty = 0;
	int lastvelx = 0, lastvely = 0;
	int speed = 8;
	
	int count = 0;
    
	public AI(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		diameter = 60;
		mass = 5;
	}
	
	@Override
	public void tick() {
		//calculating location
		clampCords();
				
		//calculating velocity
		currentTime = System.nanoTime();
		timePassed = (currentTime - lastTime)/Game.update;
		velX = (x - lastx)/timePassed;
		velY = (y - lasty)/timePassed;
		
		//calculating acceleration
		accX = (velX - lastvelx)/timePassed;
		accY = (velY - lastvely)/timePassed;
		
		//collisions
		collisions();
		
		//resetting
		lastx = x;
		lasty = y;
		lastTime = System.nanoTime();
	}
	
	private void clampCords() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Puck) {
				AIMove(tempObject);
			}
		}
		
		
		int radius = diameter / 2;

		if(x <= radius) {
			x = radius;
		}
		else if(x >= (Game.WIDTH-radius)) {
			x = Game.WIDTH - radius;
		}
		if(y <= radius) {
			y = Game.HEIGHT/2 + radius;
		}
		else if(y >= Game.HEIGHT/2 - radius) {
			y =  Game.HEIGHT/2 - radius;
		}		
		
	}
	
	public void AIMove(GameObject tempObject) {
		double puckToGoalX = tempObject.x - Game.WIDTH/2;
		double puckToGoalY = Game.HEIGHT - tempObject.y;
		double slope = puckToGoalY / puckToGoalX;
		
		//x = (int) (x + (slope * speed));
		//y = (int) (y + (1/slope * speed));
		
	
		if(tempObject.x > x) {
			x = x + speed;
		}
		else if(tempObject.x < x) {
			x = x - speed;
		}
		if(tempObject.y > y) {
			y = y + speed;
		}
		else if(tempObject.y < y) {
			y = y - speed;
		}
	}
	
	private void collisions() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Puck) {
				if(intersection(tempObject)) {
					onCollision(tempObject);
				}
			}
		}
	}
	
	private void onCollision(GameObject tempObject) {
		tempObject.velX = calcCompnt(mass, tempObject.mass, velX, tempObject.velX);
		tempObject.velY = calcCompnt(mass, tempObject.mass, velY, tempObject.velY);
		antiClip(tempObject);
	}
	
	private void antiClip(GameObject tempObject) {
		int buffer = 1;
		int radius = diameter/2;
		int tempRadius = tempObject.diameter/2;
		int distance = radius + tempRadius + buffer;
		int difx = tempObject.x - x;
		int dify = tempObject.y - y;
		//System.out.println(distance + ", " + difx + ", " + dify);
		double angle = Math.atan2(dify, difx);
		int newX = (int) (Math.cos(angle) * distance) + x;
		int newY = (int) (Math.sin(angle) * distance) + y;
		
		if(newX <= 0 || newX >= Game.WIDTH || newY <= 0 || newY >= Game.HEIGHT) {
			reset();
		}
		else {
			tempObject.x = newX;
			tempObject.y = newY;
		}
		
	}
	
	public void reset() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.AI) {
				tempObject.x = Game.WIDTH/2; 
				tempObject.y = 100;
				tempObject.velX = 0;
				tempObject.velY = 0;
				tempObject.accX = 0;
				tempObject.accY = 0;
			}
			if(tempObject.getID() == ID.Puck) {
				tempObject.x = Game.WIDTH/2; 
				tempObject.y = Game.HEIGHT/2;
				tempObject.velX = 0;
				tempObject.velY = 0;
				tempObject.accX = 0;
				tempObject.accY = 0;
			}
		}
	}
	
	private double calcCompnt(double m1, double m2, double v1, double v2) {
		double compnt;
		
		double top = 2*(m1 * v1) - (m1 * v2) + (m2 * v2);
		double bottom = m1 + m2;
		
		compnt = top / bottom;
		
		return compnt;
	}
	
	private boolean intersection(GameObject tempObject) {
		int tempx = tempObject.x;
		int tempy = tempObject.y;
		
		int radius = diameter/2;
		int tempRadius = tempObject.diameter/2;
		
		int difX = tempx - x;
		int difY = tempy - y;
		int distance = radius + tempRadius;
		
		int hypo = (int) (Math.sqrt(Math.pow(difX, 2) + Math.pow(difY, 2)));
		
		if(distance >= hypo) {
			return true;
		}
		
		return false;
	}

	@Override
	public void render(Graphics g) {
		int radius = diameter / 2;

		int adjustedx = x - radius;
		int adjustedy = y - radius;
		
		g.setColor(Color.RED);
		g.fillOval(adjustedx, adjustedy, diameter, diameter);
	}

}
