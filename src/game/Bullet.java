package game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Bullet extends Polygon implements MouseListener, MouseMotionListener {

	// this is what is shot out of the aiming canon
	// runs in a straight line until a ricochet is hit
	// I was thinking the shape could just be a triangle and the speed would
	// change based on the difficulty

	private double speed;
	private boolean mouseClicked;
	private boolean mouseReleased;


	public Bullet(Point[] inShape, Point inPosition, double inRotation, double speed) {
		super(inShape, inPosition, inRotation);
		this.speed = speed;
		mouseClicked = false;
		mouseReleased = false;
	}
	


	public void move() {
		// condition so that it stops moving in this direct if the edge of screen
		if (position.y > 8 && position.x > 10 && mouseClicked) {

			position.x -= speed * Math.cos(Math.toRadians(rotation));
			position.y -= speed * Math.sin(Math.toRadians(rotation));
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
	

	// does the same as the method in canon

	private void updateBulletAngle(int mouseX, int mouseY) {

		double bulletX = position.x;
		double bulletY = position.y;

		// atan2 is a method that is useful when calculating direction
		// between two points
		double angle = Math.atan2(bulletY - mouseY, bulletX - mouseX);
		rotation = Math.toDegrees(angle);

	}
	
	// Only methods needed are mouseClicked released and moved 

	@Override
	public void mouseClicked(MouseEvent e) {

		mouseClicked = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		mouseReleased = true;

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
	
		// so the bullet won't follow the mouse after shot!
		if(!mouseReleased)
		updateBulletAngle(e.getX(), e.getY());
		

	}

}
