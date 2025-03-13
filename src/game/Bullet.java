package game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Bullet is a public class that extends Polygon which holds fields for
 * position, rotation and the shape. Bullet also implements the MouseListener
 * and MouseMotionListener interface allowing us to adjust the rotation of the
 * bullet and aim where the user wants. Then once the mouse is clicked and
 * released the bullet starts to move. If the bullet makes contact with the
 * shell the shell explodes The user will have to react quickly pressing the
 * keys D and A to move the bucket to collect as many pieces as possible Bullet
 * contains a fields
 * 
 * <ul>
 * <li>speed - to change the speed of the bullet based on difficulty
 * <li>mouseClicked - to check if the user clicks the mouse
 * <li>mouseReleased - to check if the user releases the mouse(needed because
 * bullet will just follow the mouse the whole time, otherwise)
 */

public class Bullet extends Polygon implements MouseListener, MouseMotionListener {

	private double speed;
	private boolean mouseClicked;
	private boolean mouseReleased;
	public boolean gameOver;
	

	/**
	 * Creates a Bullet object using shape, initial position and rotation from the
	 * parent polygon class. We initialize the two fields mouseClicked and
	 * mouseReleased to false and wait for mouse input. We also initialize the speed
	 * which depends on the difficulty of the game
	 * 
	 * @param inShape
	 * @param inPosition
	 * @param inRotation
	 * @param speed
	 */
	public Bullet(Point[] inShape, Point inPosition, double inRotation, double speed) {
		super(inShape, inPosition, inRotation);
		this.speed = speed;
		mouseClicked = false;
		mouseReleased = false;
		gameOver = false;
	}

	/**
	 * The move method makes sure that the position of the bullet is inside the
	 * border of the screen if it is then the position of the bullet is decremented
	 * by the speed and at the angle of the rotation of the aiming canon We only
	 * need to decrement since the aiming canon is in the bottom left corner
	 * increment or decrement the x and y position based on the key input and
	 */
	public void move() {
		// condition so that it stops moving in this direct if the edge of screen
		if (position.y > 8 && position.x > 10 && mouseClicked) {
			position.x -= speed * Math.cos(Math.toRadians(rotation));
			position.y -= speed * Math.sin(Math.toRadians(rotation));

		} else if (position.y <= 10 || position.x <= 10 || position.y >= 590|| position.x >= 790) {
			reset();
			// game over
		}
	}

	public void ricochet(double r) {
		this.rotation = 2 * r - this.rotation;
	}

	public void reset() {
		super.position = new Point(760, 550);
		this.mouseClicked = false;
		gameOver = true;
		
	}

	/**
	 * Paint takes in a point array from the Polygon super class and splits them
	 * into two x,y integer arrays. Then passed into and for loop and assigned to
	 * the x and y double points from the parent class(through casting. Then we use
	 * the built in method from Graphics class to paint the object
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

	/**
	 * The updateBulletAngle takes in two int parameters for the mouseX and mouseY
	 * coordinates , which will be used for the mouseMoved method, which allows us
	 * to track where the players cursor is. We then initialize two doubles bulletX
	 * and bulletY to hold the current position of the bullet Lastly we initialize a
	 * double angle by calculating the the angle between the the current bullet
	 * position and the users cursor. The rotation is set to the angle.
	 * 
	 * @param mouseX
	 * @param mouseY
	 */
	private void updateBulletAngle(int mouseX, int mouseY) {

		double bulletX = position.x;
		double bulletY = position.y;

		// atan2 is a method that is useful when calculating direction
		// between two points
		double angle = Math.atan2(bulletY - mouseY, bulletX - mouseX);
		rotation = Math.toDegrees(angle);

	}

	// Only methods needed are mouseClicked released and moved

	/**
	 * The mouseClicked method is a needed method when we implement the
	 * MouseListener interface. Takes in a MouseEvent parameter and all we do in
	 * this method is set mouseClicked to true, which we use in the move method
	 * because we do not want the bullet to start moving until the mouse is clicked
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		mouseClicked = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * The mouseReleased method is a needed method when we implement the
	 * MouseListener interface. Takes in a MouseEvent parameter and all we do in
	 * this method is set mouseReleased to true, which we use in the move method
	 * because we do not want the bullet to follow the mouse when released
	 */
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

	/**
	 * The mouseMoved method is a needed method when we implement the
	 * MouseMotionListener interface. Takes in a MouseEvent parameter and we update
	 * the angle of the bullet if and only if the bullet has not been shot or the
	 * mouse has not been released
	 * 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {

		// so the bullet won't follow the mouse after shot!
		if (!mouseReleased)
			updateBulletAngle(e.getX(), e.getY());

	}

}
