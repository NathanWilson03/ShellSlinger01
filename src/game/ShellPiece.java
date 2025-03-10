package game;

import java.awt.Graphics;

public class ShellPiece extends Polygon {
	private double speed; // Speed of the piece

	public ShellPiece(Point[] inShape, Point inPosition, double inRotation, double speed) {
		super(inShape, inPosition, inRotation);
		this.speed = speed;
	}

// Move the piece in its current direction
	public void move() {
		// Math.toRadians returns -45 x pi/ 180 = -.785
		// then the Math.cos of this is .707, which is the angle of the canon
		// similar with sin
		position.x += speed * Math.cos(Math.toRadians(rotation));
		position.y += speed * Math.sin(Math.toRadians(rotation));
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
}
