package main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject { //puck

    PlayerMovementListener pml;

	int radius = 32;
    
	public Player(int x, int y, ID id) {
		super(x, y, id);
		
	}
	
	@Override
	public void tick() {
		clampCords();
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
