package main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject { //puck

    PlayerMovementListener pml;
    Handler handler;

	long lastTime = System.nanoTime();
	long currentTime;
	double timePassed;
	int lastx = 0, lasty = 0;
	int lastvelx = 0, lastvely = 0;
	
	int count = 0;
    
	public Player(int x, int y, ID id, Handler handler) {
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

		
		//reporting values
		
		//if(count == 1) {
		//System.out.println("Vel:" + velX);
		//System.out.println("Vel: " + velY);
		//System.out.println("Acc:" + accX);
		//System.out.println("Acc: " + accY);
			//count = 0;
		//}
		//count++;
		
		//collisions
		collisions();
		
		//resetting
		lastx = x;
		lasty = y;
		lastTime = System.nanoTime();
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
		//tempObject.velX = calcCompnt(mass, tempObject.mass, velX, tempObject.velX);
		//tempObject.velY = calcCompnt(mass, tempObject.mass, velY, tempObject.velY);
		calcCompnt(tempObject);
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
	
	private void calcCompnt(GameObject tempObject) {
		//double compnt;
		
		/* use this code if you want to use conservation of momentum but if you want it to work better just take advantage of the 
		 * vague terms of the units and just use the much better proportional method below
		double top = 2*(m1 * v1) - (m1 * v2) + (m2 * v2);
		double bottom = m1 + m2;
		
		compnt = top / bottom;
		*/
		double temp = Math.pow(velX, 2) + Math.pow(velY, 2);
		double playerVel = Math.sqrt(temp) * mass/tempObject.mass;
		temp = Math.pow(tempObject.velX, 2) + Math.pow(tempObject.velY, 2);
		double puckVel = Math.sqrt(temp);
		
		double totalVel = playerVel + puckVel;
		
		int difx = tempObject.x - x;
		int dify = tempObject.y - y;
		double angle = Math.atan2(dify, difx);
		tempObject.velX = (int) (Math.cos(angle) * totalVel);
		tempObject.velY = (int) (Math.sin(angle) * totalVel);
		
		//return compnt;
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
		
		if(count == 50) {
			//System.out.println("Tempx: " + tempx + ", Tempy: " + tempy + ", Distance: " + distance + ", difX: " + difX + ", difY: " + difY + ", Hypo: " + hypo);		
			count = 0;
		}
		count++;
		
		return false;
	}

	private void clampCords() {
		int tempx = PlayerMovementListener.playerx;
		int tempy = PlayerMovementListener.playery;
		
		int radius = diameter / 2;

		if(tempx <= radius) {
			tempx = radius;
		}
		else if(tempx >= (Game.WIDTH-radius)) {
			tempx = Game.WIDTH - radius;
		}
		if(tempy <= Game.HEIGHT/2 + radius) {
			tempy = Game.HEIGHT/2 + radius;
		}
		else if(tempy >= (Game.HEIGHT-radius)) {
			tempy = (Game.HEIGHT-radius);
		}		
		
		x = tempx;
		y = tempy;
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
