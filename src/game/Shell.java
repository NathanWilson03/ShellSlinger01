package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
* Shell is a public class that extends Polygon
* which holds fields for speed(double), isBroken(boolean), an List of ShellPieces pieces difficulty 
* and inherited rotation, shape and initial position.
* Shell does not need to implement any KeyListener or MouseListener Interfaces since it is shot 
* across the screen at a set angle and not controlled by user(or this game would be extremely easy)
* If the shell once shot out the the starting canon is hit, it will break into "shell pieces"
* Which will be then collected by the bucket(these methods are created in this class)
* Then once the mouse is clicked and released the bullet starts to move.
* 
* Shell contains a fields 
* 
* <ul>
* <li>speed - to change the speed of the shell(target) based on difficulty
* <li>isBroken - to check to see if the shell was hit by the bullet 
* <li>pieces - the pieces that will appear on screen once the shell is hit by the bullet 
* <li>difficulty - the difficulty of the game chosen by the user. In  this class this effects the speed of the shell
*/


public class Shell extends Polygon {

    private double speed; 
    private boolean isBroken; 
    private List<ShellPiece> pieces; 
    private int difficulty; 

	/**
	 * Creates a Shell object using shape, initial position and rotation
	 * from the parent polygon class.
	 * When a a shell object is created the following parameters are initialized 
	 * @param inShape - to create the shape of the object using an array of Points(x,y coordinates)
	 * @param inPosition - to start the shape in the starting canon position 
	 * @param inRotation - to set the rotation of the shape
	 * @param speed - to set the speed at which the shell object moves across the screen 
	 * @param difficulty - to set the difficulty chosen by the player( easy medium or hard)
	 */
    public Shell(Point[] inShape, Point inPosition, double inRotation, double speed, int difficulty) {
        super(inShape, inPosition, inRotation);
        this.speed = speed;
        this.isBroken = false;
        this.pieces = new ArrayList<>();
        this.difficulty = difficulty;
    }

    /**
	 * The move method makes sure that the position of the shell is inside the border of the screen
	 * if it is then the position of the shell is incremented by the speed 
	 * and at the angle of the rotation of the aiming canon
	 * We only need to increment since the starting canon is in the bottom left corner 
	 */
    public void move() {
        if (!isBroken && position.y > 8 && position.x > 0 && position.y < 600 && position.x < 800) {
            position.x += speed * Math.cos(Math.toRadians(rotation));
            position.y += speed * Math.sin(Math.toRadians(rotation));
        }
    }

  
    /**
     * The breakApart method for creating the shell pieces which are just an array list of shells 
     * if isBroken boolean field is set to true. Which happens when the shell is hit 
     * We check to see if the shell is hit in a later method.
     * This method loops through the number of pieces based on the difficulty(higher difficulty, higher reward)
     * Then creates a number of shell pieces at the position of where the shell was hit
     * They then fly of in different directions because of the peiceRotation
     */
    public void breakApart() {
        isBroken = true;
        int numPieces = difficulty * 2; // Easy: 2, Medium: 4, Hard: 6 pieces
        for (int i = 0; i < numPieces; i++) {
            double pieceRotation = Math.random() * 360; // Random direction for each piece
            ShellPiece piece = new ShellPiece(getShape(), new Point(position.x, position.y), pieceRotation, speed / 2); // Slower speed
            pieces.add(piece);
        }
    }

    /**
     * Checks to see of the shell is broken 
     * @return - the boolean isBroken field 
     */
    public boolean isBroken() {
        return isBroken;
    }

    /**
     * Gets the pieces from the shell List 
     * Used in the ShellSlinger class to loop through all pieces 
     * draw and move them 
     * @return - pieces 
     */
    public List<ShellPiece> getPieces() {
        return pieces;
    }

    /**
     * This collidesWith method checks to see if the shell is hit by the bullet.
     * Takes in a bullet parameter and gets the points from the bullet 
     * If the shell objects contains a SINGLE point from the bullet shape 
     * return true, other wise return false( credit to Jacob, this method is genius)
     *
     *@param - bullet - bullet object to get points and check if equal to the points from shell
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
     * Handles the collection of Shell Pieces from after the collision 
     * , by the bucket objects. A local int variable starts at 0 and is incremented 
     * if the bucket collides with( using the collidesWith method) the shell Pieces. 
     * We also want to remove all the pieces once they hit the bucket so it looks like they went in 
     * This method does this by creating a list, adding the piece to the list once hitting the bucket 
     * Then removing all the pieces
     * 
     * @param bucket - The bucket object that collects the pieces when collision detected
     */
    public int collectPieces(Bucket bucket) {
        int collected = 0;
        List<ShellPiece> piecesToRemove = new ArrayList<>();

        for (ShellPiece piece : pieces) {
            if (bucket.collidesWith(piece)) {
                piecesToRemove.add(piece);
                collected++;
            }
        }

        // remove  collected pieces
        pieces.removeAll(piecesToRemove);
        return collected;
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
        if (!isBroken) {
            // draw the shell
            Point[] pArray = super.getPoints();
            int[] xPoints = new int[pArray.length];
            int[] yPoints = new int[pArray.length];

            for (int i = 0; i < pArray.length; i++) {
                xPoints[i] = (int) pArray[i].x;
                yPoints[i] = (int) pArray[i].y;
            }


            brush.fillPolygon(xPoints, yPoints, pArray.length);
        } else {
            // draw the shell pieces
            for (ShellPiece piece : pieces) {
                piece.paint(brush);
            }
        }
    }
    
   
    

}

