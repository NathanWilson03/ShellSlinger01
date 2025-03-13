package game;
import java.awt.Graphics;

/**
 * Ricochet is a class that extends Polygon and takes in 
 * no new special fields, only the inherited 
 * rotation, position and the shape, which is a point array 
 * . Ricochet objects are made so that the bullet object can
 * bounce off of them and the more ricochets you hit your score 
 * gets multiplied. Because it is harder to hit the shell when you have 
 * to essentially "bank" your bullet off the ricochets 
 * If you completely miss the game is over 
 */
public class Ricochet extends Polygon {
	
	public Ricochet(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}
	
	/*
	 * Gets the rotation of the ricochet
	 * 
	 */
	public double getRotation() {
		return super.rotation;
	}
	
	/**
    * This collidesWith method checks to see if the ricochet is hit by the bullet.
    * Takes in a bullet parameter and gets the points from the bullet 
    * If the ricochet objects contains a SINGLE point from the bullet shape 
    * return true, other wise return false
    *
    *@param - bullet - bullet object to get points and check if equal to the points from ricochet
    */
	public boolean collidesWith(Bullet bullet) {
       Point[] bulletPoints = bullet.getPoints();
       for (Point bulletPoint : bulletPoints) {
           if (this.contains(bulletPoint)) {
               return true;
           }
       }
       return false;
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
