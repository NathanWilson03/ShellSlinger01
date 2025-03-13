package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

/**
 * Canon is a public class that extends Polygon which
 * holds fields for position, rotation and the shape, as well as a canAim boolean field
 * Canon also implements the MouseListener and MouseMotionListener
 * interface allowing us to adjust the rotation of the canon and aim where the
 * user wants( if the canAim field is true. In this game, so far there are only 2 canons 
 * no matter what. An aiming canon that shoots a bullet object and a starting canon 
 * that shoots a shell or target object. Once the mouse is clicked and release the canon 
 * shoots a bullet in the direction of the shell(target object). We are able to do this 
 * with the help of a private method that updates the angle of the canon based on the position 
 * of the users mouse. Using MouseMotuionListener 
 * The following private fields are used 
 * 
 * <ul>
 * <li>canAim - A boolean field to make it so one canon can rotate( change a rotation field)
 
 *  
 */
public class Canon extends Polygon implements MouseMotionListener, MouseListener {

	private boolean canAim;
	private double angleX;
	private double angleY;
	private MouseEvent e;

	
	/**
	 * Creates a Canon object using shape, initial position and rotation
	 * from the parent polygon class.
	 * if the canAim field is true then we set the rotation 60 decrease
	 * which is needed to aim towards the left side of the screen 
	 * 
	 * @param inShape - to create the shape of the object using an array of Points(x,y coordinates)
	 * @param inPosition - to start the shape in the starting canon position 
	 * @param inRotation - to set the rotation of the shape
	 * @param canAim - determines whether a canon is a aiming canon or starting canon
	 */
	public Canon(Point[] inShape, Point inPosition, double inRotation, boolean canAim) {
		super(inShape, inPosition, inRotation);
		this.canAim = canAim;

		if (canAim) {
			rotation = 60;

		}
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


	/**
	 * The updateCanonAngle takes in two int parameters for the mouseX and mouseY coordinates
	 * , which will be used for the mouseMoved method, which allows us to track 
	 * where the players cursor is.
	 * We then initialize two doubles canonX and canonY to hold the current position of the canon
	 * Lastly we initialize a double angle by calculating the the angle between the the current canon position 
	 * and the users cursor. 
	 * The rotation is set to the angle.
	 * @param mouseX
	 * @param mouseY
	 */
	private void updateCanonAngle(int mouseX, int mouseY) {

		double canonX = position.x;
		double canonY = position.y;

		// atan2 is a method that is useful when calculating direction
		// between two points
		double angle = Math.atan2(canonY - mouseY, canonX - mouseX);
		rotation = Math.toDegrees(angle);

	}
	
	/**
	 * The mouseClicked method is a needed method when we implement the MouseListener 
	 * interface. Takes in a MouseEvent parameter and all we do in this method 
	 * is set mouseClicked to true, which we use in the move method 
	 * because we do not want the canon to start moving until the mouse is clicked
	 * if the canon canAim
	 */

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
	/**
	 * Tracks the movement of the mouse using the motionListener 
	 * updates the angle technically recursively since the paint method in main class 
	 */

	@Override
	public void mouseMoved(MouseEvent e) {
		updateCanonAngle(e.getX(), e.getY());

	}

}
