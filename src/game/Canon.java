package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class Canon extends Polygon implements MouseMotionListener, MouseListener {

	// two types of canon. Aiming, which shoots the bullet object and is controlled
	// by the user.
	// Then the starting canon which shoots the shell, when the round is started

	private boolean canAim;
	private double angleX;
	private double angleY;
	private MouseEvent e;

	public Canon(Point[] inShape, Point inPosition, double inRotation, boolean canAim) {
		super(inShape, inPosition, inRotation);
		this.canAim = canAim;

		if (canAim) {
			rotation = 60;

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

	// this method takes in two integers which will be the coordinates
	// of where the cursor is
	// then we subtract that from the position of the canon
	// convert to degrees and assign to the rotation
	private void updateCanonAngle(int mouseX, int mouseY) {

		double canonX = position.x;
		double canonY = position.y;

		// atan2 is a method that is useful when calculating direction
		// between two points
		double angle = Math.atan2(canonY - mouseY, canonX - mouseX);
		rotation = Math.toDegrees(angle);
	

	}



	@Override
	public void mouseClicked(MouseEvent e) {
		updateCanonAngle(e.getX(), e.getY());
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		updateCanonAngle(e.getX(), e.getY());

	}

}
