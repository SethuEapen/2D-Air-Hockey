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
		diameter = 84;
		mass = 4;
	}
	
	@Override
	public void tick() {
		//calculating location
		clampCords();
		//calculating velocity
		currentTime = System.nanoTime();
		timePassed = (currentTime - lastTime)/12000000;
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
		tempObject.velX = calcCompnt(mass, tempObject.mass, velX, tempObject.velX);
		tempObject.velY = calcCompnt(mass, tempObject.mass, velY, tempObject.velY);

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
		
		if(count == 50) {
			System.out.println("Tempx: " + tempx + ", Tempy: " + tempy + ", Distance: " + distance + ", difX: " + difX + ", difY: " + difY + ", Hypo: " + hypo);		
			count = 0;
		}
		count++;
		
		return false;
	}

	private void clampCords() {
		int tempx = PlayerMovementListener.playerx;
		int tempy = PlayerMovementListener.playery;
		
		int boarder = 40;
		int radius = diameter / 2;

		if(tempx <= radius) {
			tempx = radius;
		}
		else if(tempx >= (Game.WIDTH-radius)) {
			tempx = Game.WIDTH - radius;
		}
		if(tempy <= radius) {
			tempy = radius;
		}
		else if(tempy >= (Game.HEIGHT-radius-boarder)) {
			tempy = (Game.HEIGHT-radius-boarder);
		}		
		
		x = tempx;
		y = tempy;
	}
	
	@Override
	public void render(Graphics g) {
		int radius = diameter / 2;

		int adjustedx = x - radius;
		int adjustedy = y - radius;
		
		g.setColor(Color.BLUE);
		g.fillOval(adjustedx, adjustedy, diameter, diameter);
	}

}
