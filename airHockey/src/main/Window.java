package main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Window extends Canvas { //creates the window

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Window(int width, int height, String title, Game game) {
		//Create JFrame
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JComponent newContentPane = new PlayerMovementListener();
        newContentPane.setOpaque(true); //content panes must be opaque
		
        PlayerMovementListener pml = new PlayerMovementListener();
        game.addMouseMotionListener(pml);
        newContentPane.add(game);
        frame.setContentPane(newContentPane);
		
		frame.setVisible(true);
		game.start();
	}
	
}
