package main;

import java.awt.Color;
import java.awt.Graphics;

public class Puck extends GameObject {

	long lastTime = System.nanoTime();
	long currentTime;
	double timePassed;
	boolean AIWin = false;
	boolean PlayerWin = false;
	Handler handler;
	
	public Puck(int x, int y, ID id, Handler handler) {
		super(x, y, id);
			accX = 0;//-.01;
			accY = 0;//-.01;
			velX = 0;//6;
			velY = 0;//7;
			diameter = 40;
			mass = 2;
			this.handler = handler;
	}
	
	@Override
	public void tick() {
		puckMovement();
	}

	@Override
	public void render(Graphics g) {
		int radius = diameter/2;
		
		g.setColor(Color.BLACK);
		g.fillOval(x-radius, y-radius, diameter, diameter);
		if(AIWin) {
			g.drawString("AI WON", 100, 100);
		}
		if(PlayerWin) {
			g.drawString("PLAYER WON", 100, 100);
		}
	}
	
	public void puckMovement() {
		currentTime = System.nanoTime();
		timePassed = (currentTime - lastTime)/Game.update;
		
		
		/*//rework
		


		*///end of rework
		
		calcAcc();

		x = x + (int) ((velX * timePassed) + (.5 * accX * Math.pow(timePassed, 2)));
		velX = velX + (accX * timePassed);
		y = y + (int) ((velY * timePassed) + (.5 * accY * Math.pow(timePassed, 2)));
		velY = velY + (accY * timePassed);


		int radius = diameter/2;
		
		if(x <= radius) {
			x = radius + 1;
			velX = -velX;
			accX = -accX;
		}
		else if(x >= Game.WIDTH-radius) {
			x = Game.WIDTH-radius-1;
			velX = -velX;
			accX = -accX;
		}
		if(y <= radius) {
			y = radius + 1;
			velY = -velY;
			accY = -accY;
		}
		else if(y >= Game.HEIGHT-radius) {
			y = Game.HEIGHT-radius - 1;
			velY = -velY;
			accY = -accY;
		}

		//check score
		score();
		
		//resetting
		lastTime = System.nanoTime();
	}
	
	private void score() {
		if(x >= (Game.WIDTH/2 - Game.WIDTH/6) && x <= (Game.WIDTH/2 + Game.WIDTH/6)) {
			if(y <= 10 + (diameter/2)) {
				reset();
				Game.playerScore++;
				System.out.println("Game Score: player - " + Game.playerScore + " AI - " + Game.AIScore);
			}
		}
		if(x >= (Game.WIDTH/2 - Game.WIDTH/6) && x <= (Game.WIDTH/2 + Game.WIDTH/6)) {
			if(y >= Game.HEIGHT - 10 - (diameter/2)) {
				reset();
				Game.AIScore++;
				System.out.println("Game Score: player - " + Game.playerScore + " AI - " + Game.AIScore);
			}
		}
		if(Game.AIScore >= 7) {
			System.out.println("AI Won!");
			AIWin = true;
		}
		if(Game.playerScore >= 7) {
			System.out.println("PLAYER Won!");
			PlayerWin = true;
		}
	}
	
	public void reset() {
		x = Game.WIDTH/2; 
		y = Game.HEIGHT/2;
		velX = 0;
		velY = 0;
		accX = 0;
		accY = 0;
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
		}
	}
	
	private void calcAcc() {
				
		accX = -velX / 1;
		accY = -velY / 1;
		
		
		//double distance = (puckVel * timePassed) + (.5 * Game.friction * Math.pow(timePassed, 2));
		/*if(velX != 0 && velY != 0) {
			double angle = Math.atan2(velX, velY);
			accX = Math.abs(Math.cos(angle)) * -velX;
			accY = Math.abs(Math.sin(angle)) * -velY;
			System.out.println(accY);
		}*/
		
		//System.out.println((int)(velX) + ", " + (int)(velY));
		/*
		if((int)(velX * 10) != 0) {
			//double angle = Math.atan2(velX, velY);
			//accX = -1 * Math.cos(angle);
			//System.out.println(angle + ", " + accX + ", " + accY);
			accX = -velX / 10;
		}
		else {
			accX = 0;
			velX = 0;
		}
		if((int)(velY * 10) != 0) {
			//double angle = Math.atan2(velX, velY);
			//accY = -1 * Math.sin(angle);
			accY = -velY / 10;
		}
		else {
			accY = 0;
			velY = 0;
		}
		if((int)(velX) == 0 && (int)(velY) != 0) {
			if(y <= Game.HEIGHT/2 - diameter/2) {
				x = Game.WIDTH/2; 
				y = Game.HEIGHT/2;
			}
		}
		//System.out.println((int)(velX) + ", " + (int)(velY));
		*/
	}
	
}
