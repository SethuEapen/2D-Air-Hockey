package main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject { //puck

    PlayerMovementListener pml;

	int radius = 32;
	long lastTime = System.nanoTime();
	long currentTime;
	double timePassed;
	int lastx = 0, lasty = 0;
	int lastvelx = 0, lastvely = 0;

	
	int count = 0;
    
	public Player(int x, int y, ID id) {
		super(x, y, id);
		
	}
	
	@Override
	public void tick() {
		//calculating location
		clampCords();
		//calculating velocity
		currentTime = System.nanoTime();
		timePassed = (currentTime - lastTime)/1000;
		velX = (x - lastx)/timePassed;
		velY = (y - lasty)/timePassed;
		
		//calculating acceleration
		accX = (velX - lastvelx)/timePassed;
		accY = (velY - lastvely)/timePassed;

		
		//reporting values
		
		//if(count == 1) {
		System.out.println("Vel:" + velX);
		System.out.println("Vel: " + velY);
		System.out.println("Acc:" + accX);
		System.out.println("Acc: " + accY);
			//count = 0;
		//}
		count++;
		//resetting
		lastx = x;
		lasty = y;
		lastTime = System.nanoTime();
	}

	private void clampCords() {
		int tempx = PlayerMovementListener.playerx;
		int tempy = PlayerMovementListener.playery;
		
		
		int clampx = Math.max(tempx, radius/2);
		clampx = Math.min(clampx, Game.WIDTH - radius);
		int clampy = Math.max(tempy, (Game.HEIGHT / 2) + radius/2);
		clampy = Math.min(clampy, Game.HEIGHT - radius*7/4);
		
		clampx -= radius/2;
		clampy -= radius/2;

		
		x = clampx;
		y = clampy;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, radius, radius);
	}

}
