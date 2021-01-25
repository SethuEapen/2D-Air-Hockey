package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PlayerMovementListener extends JPanel implements MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//JPanel blankArea;

	
	//public JPanel getBlankArea() {
	//	return blankArea;
	//}

	public PlayerMovementListener() {
		super(new GridLayout(0,1));
        //blankArea = new JPanel();
        //add(blankArea);
        
        //Register for mouse events on blankArea and panel.
        //blankArea.addMouseMotionListener(this);
        addMouseMotionListener(this);
        
        //setPreferredSize(new Dimension(450, 450));
        //setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println(x + ", " + y);
	}

}
