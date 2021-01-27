package main;

import java.awt.Color;
import java.awt.Graphics;

public class Puck extends GameObject {

	long lastTime = System.nanoTime();
	long currentTime;
	double timePassed;
	
	public Puck(int x, int y, ID id) {
		super(x, y, id);
			accX = 0;//-.01;
			accY = 0;//-.01;
			velX = 0;//6;
			velY = 0;//7;
			diameter = 42;
	}
	
	@Override
	public void tick() {
		//if(velX > 0 && velY >= 0) {//very temporary will need to get rid of this with the gravity 
		puckMovement();
		//}
	}

	@Override
	public void render(Graphics g) {
		int radius = diameter/2;
		
		g.setColor(Color.BLACK);
		g.fillOval(x-radius, y-radius, diameter, diameter);
	}
	
	public void puckMovement() {
		currentTime = System.nanoTime();
		timePassed = (currentTime - lastTime)/12000000;
		x = x + (int) ((velX * timePassed) + (.5 * accX * Math.pow(timePassed, 2)));
		velX = velX + (accX * timePassed);
		y = y + (int) ((velY * timePassed) + (.5 * accY * Math.pow(timePassed, 2)));
		velY = velY + (accY * timePassed);

		int boarder = 40;
		int radius = diameter/2;
		
		if(x <= radius) {
			velX = -velX;
			accX = -accX;
		}
		else if(x >= Game.WIDTH-radius) {
			velX = -velX;
			accX = -accX;
		}
		if(y <= radius) {
			velY = -velY;
			accY = -accY;
		}
		else if(y >= Game.HEIGHT-radius-boarder) {
			velY = -velY;
			accY = -accY;
		}
		
		
		//resetting
		lastTime = System.nanoTime();
	}
	
}
