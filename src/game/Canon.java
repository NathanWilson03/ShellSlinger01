package game;

import java.awt.Color;
import java.awt.Graphics;

public class Canon extends Polygon {

	// two types of canon. Aiming, which shoots the bullet object and is controlled
	// by the user.
	// Then the starting canon which shoots the shell, when the round is started

	private boolean canAim;

	public Canon(Point[] inShape, Point inPosition, double inRotation, boolean canAim) {
		super(inShape, inPosition, inRotation);
		this.canAim = canAim;
	}

	@Override
	public void rotate(int degrees) {
		if(canAim)
		rotation = (rotation + degrees) % 90;
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
