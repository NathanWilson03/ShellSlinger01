package game;

import java.awt.Graphics;

public class ShellPiece extends Polygon {
	private double speed; // Speed of the piece

	public ShellPiece(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		this.speed = 2 + Math.random() * 3; // Random speed between 2 and 5
	}

// Move the piece in its current direction
	public void move() {
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
