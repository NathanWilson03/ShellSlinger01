package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JPanel;

public class Bucket extends Polygon implements KeyListener {

	private boolean forward;
	private boolean backward;

	// These objects will be controlled by the user and glide across the border
	// to collect the pieces that break off from the explosion
	// I think there should be 2 but lmk

	public Bucket(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		// TODO Auto-generated constructor stub

		forward = false;
		backward = false;

	}

	public void move() {

		// make sure all coordinates are divisible by 4
		double amountToMove = 4;

		// initial movement up or down
		if (forward && position.y != 536 && position.x == 770) {
			position.y += amountToMove;
		}

		if (backward && position.y != 536 && position.x == 770) {
			position.y -= amountToMove;
		}
	

		// if the red canon is hit by the bucket
		if (forward && position.y == 500) {

			position.x = 712;
			position.y = 536;
		}
		

		if (position.y == 536 && forward) {
			rotation = 90;
			position.x -= amountToMove;
		}

		// if we are at the bottom of the screen
		// and moving back
		if (position.y == 536 && backward) {
			position.x += amountToMove;
		}

		// but if we hit the red canon again
		// rotate to right wall and set new coordinates
		if (backward && position.x == 712 && position.y == 536) {
			rotation = 0;
			position.y = 496;
			position.x = 770;
		}

		// Now we need to handle the left side
		if (forward && position.x == 56 && position.y != 12) {
			rotation = 180;
			position.x = 20;
			position.y = 492;

		}

		if (forward && position.x == 20 && position.y >= 32) {
			position.y -= amountToMove;
		}

		if (backward && position.x == 20 && position.y <= 500) {
			position.y += amountToMove;

		}

		if (backward && position.y == 500 && position.x == 20) {
			position.y = 536;
			position.x = 56;
			rotation -= 90;

		}

		// handling the top side of the screen

		if (forward && position.y == 32 && position.x == 20) {
			rotation = 270;
			position.x = 16;
			position.y = 12;

		}

		if (forward && position.y == 12) {
			position.x += amountToMove;
		}
		
		if(backward && position.y == 12) {
			position.x  -= amountToMove;
		}
		
		// specifically top left side 
		if(backward && position.y == 12 && position.x == 20) {
			rotation = 180;
		}
		
		// lastly top right 
		if(forward && position.y == 12 && position.x == 760) {
			rotation = 360;
			position.y = 16;
			position.x = 770;
		}
		
		if(forward && position.x == 760) {
			position.y += amountToMove;
		}
		
		if(backward && position.x == 770 && position.y == 16) {
			rotation = 270;
			position.y =12;
			position.x = 764;
		}
		
	
		
	
		
		
		
		
		
		
		

	}

	public void paint(Graphics brush) {

		Point[] pArray = super.getPoints();
		int[] xPoints = new int[pArray.length];
		int[] yPoints = new int[pArray.length];

		for (int i = 0; i < pArray.length; i++) {
			xPoints[i] = (int) pArray[i].x;
			yPoints[i] = (int) pArray[i].y;

		}

		brush.fillPolygon(xPoints, yPoints, pArray.length);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 68) {
			forward = true;

		}

		if (e.getKeyCode() == 65) {
			backward = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == 68) {
			forward = false;
		}

		if (e.getKeyCode() == 65) {
			backward = false;
		}

	}

}
