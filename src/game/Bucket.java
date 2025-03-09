package game;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JPanel;

public class Bucket extends Polygon implements KeyListener{

	private boolean forward;
	private boolean backward;
	
	
	// These objects will be controlled by the user and glide across the border 
	// to collect the pieces that break off from  the explosion 
	// I think there should be 2 but lmk 

	public Bucket(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		// TODO Auto-generated constructor stub
		
		forward = false;
		backward = false;
		
	}
	
	public void move() {
		
		double amountToMove = 2;
		
		
		// checking if we reach the bottom of the right side 
		if(position.y == 500 && position.x > 500) {
			position.y = 540; 
			position.x  = 716;
			
		}
		
		// then we check if the y is 540 and if it is we start to move horizontal 
		if(position.y == 540 && backward == true) {
	
		rotation = 90;
		position.x -= amountToMove;
	

		}
		// otherwise keep moving down
		else if(position.y != 540 && position.x >= 770 && forward == true){
			rotation = 360;
			position.y += amountToMove;
		}
		
		if(position.x == 60 && position.y == 540) {
			position.y = 498;
			position.x = 10;
		}
		
		if(position.x == 10 && position.y <= 498 && backward == true) {
			rotation = 180;
			position.y -= amountToMove;
		}
		
		if(position.x >= 10 && position.y == 10 && forward == true) {
			rotation = 270;
			position.x += amountToMove;
			
		}
		
		
	

		
		
		
		
	}


	
	public void paint(Graphics brush) {
		
		
		
		Point[] pArray = super.getPoints();
		int[] xPoints = new int[pArray.length];
		int[] yPoints = new int[pArray.length];
		
		for(int i = 0; i < pArray.length; i++) {
			xPoints[i] = (int)pArray[i].x;
			yPoints[i] = (int)pArray[i].y;
			
			
		}
		
		brush.fillPolygon(xPoints, yPoints, pArray.length);
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == 68) {
			forward = true;
			
		}
		
		if(e.getKeyCode() == 65) {
			backward = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == 68) {
			forward = false;
		}
		
		if(e.getKeyCode() == 65) {
			backward = false;
		}
		
	}

}
