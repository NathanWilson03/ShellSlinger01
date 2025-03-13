package game;

import java.awt.Graphics;

/**
 * ShellPiece is a public class that extends Polygon which holds fields for
 * speed(double), and inherited rotation, shape and initial position. ShellPiece
 * does not need to implement any KeyListener or MouseListener Interfaces since
 * once the shell explodes you cannot control the shell pieces. These objects
 * are made when the shell is hit by a bullet and the goal is to collect as many
 * of them as possible by the bucket objects on the sides of the screen
 *
 * 
 * Shell contains a fields
 * 
 * <ul>
 * <li>speed - to change the speed of the shell pieces
 * 
 */
public class ShellPiece extends Polygon {
	private double speed;

	/**
	 * Creates a ShellPiece object with an array of x,y coordinates, a specific
	 * starting point rotation and speed based on difficulty
	 * 
	 * @param inShape
	 * @param inPosition
	 * @param inRotation
	 * @param speed
	 */
	public ShellPiece(Point[] inShape, Point inPosition, double inRotation, double speed) {
		super(inShape, inPosition, inRotation);
		this.speed = speed;
	}

	/**
	 * Moves the shell pieces across the screen. The way they move is more detailed in the shell class 
	 * But if a shell has no rotation then it will move up and right 
	 * In  the shell class rotation is random and multiplied by 360 allowing this movement to happen in all
	 * directions
	 * we use the positon.x and position,y to update the overall position 
	 */
	public void move() {
		// Math.toRadians returns -45 x pi/ 180 = -.785
		// then the Math.cos of this is .707, which is the angle of the canon
		// similar with sin
		position.x += speed * Math.cos(Math.toRadians(rotation));
		position.y += speed * Math.sin(Math.toRadians(rotation));
	}

	/**
	 * Paint takes in a point array from the Polygon super class and splits them into 
	 * two x,y integer arrays. Then passed into and for loop and assigned to the 
	 * x and y double points from the parent class(through casting.
	 * Then we use the built in method from Graphics class to paint the object
	 * 
	 * @param brush
	 */
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
}
