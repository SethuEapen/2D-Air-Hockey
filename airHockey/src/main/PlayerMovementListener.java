package main;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class PlayerMovementListener extends JPanel implements MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	
	public static int playerx;
	public static int playery;
	
	public PlayerMovementListener() {
		super(new GridLayout(0,1));
        addMouseMotionListener(this);
        
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {//update cords when mouse moves
		playerx = e.getX();
		playery = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		playerx = e.getX();
		playery = e.getY();
	}
/*
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
*/
}
