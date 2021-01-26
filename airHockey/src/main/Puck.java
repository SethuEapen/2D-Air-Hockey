package main;

import java.awt.Color;
import java.awt.Graphics;

public class Puck extends GameObject {

	int radius = 24;
	long lastTime = System.nanoTime();
	long currentTime;
	double timePassed;
	
	public Puck(int x, int y, ID id) {
		super(x, y, id);
			accX = -.01;
			accY = -.01;
			velX = 6;
			velY = 7;
	}
	
	@Override
	public void tick() {
		//if(velX > 0 && velY >= 0) {//very temporary will need to get rid of this with the gravity 
		puckMovement();
		//}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x, y, radius, radius);
	}
	
	public void puckMovement() {
		currentTime = System.nanoTime();
		timePassed = (currentTime - lastTime)/12000000;
		x = x + (int) ((velX * timePassed) + (.5 * accX * Math.pow(timePassed, 2)));
		velX = velX + (accX * timePassed);
		y = y + (int) ((velY * timePassed) + (.5 * accY * Math.pow(timePassed, 2)));
		velY = velY + (accY * timePassed);

		if(x <= 0) {
			velX = -velX;
			accX = -accX;
		}
		else if(x >= Game.WIDTH-100) {
			velX = -velX;
			accX = -accX;
		}
		if(y <= 0) {
			velY = -velY;
			accY = -accY;
		}
		else if(y >= Game.HEIGHT-100) {
			velY = -velY;
			accY = -accY;
		}
		
		
		//resetting
		lastTime = System.nanoTime();
	}

}
