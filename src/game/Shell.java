package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Shell extends Polygon{

	// the target object, what is shot out of the starting cannon
	// is not effected by ricochets and when hit will break into smaller pieces
	// Only one shell will be shot 
    private double speed; // Speed of the shell
    private boolean isBroken; // Whether the shell has broken into pieces
    private List<ShellPiece> pieces; // List of shell pieces after breaking
	
	public Shell(Point[] inShape, Point inPosition, double inRotation, double speed) {
		super(inShape, inPosition, inRotation);
        this.speed = speed;
        this.isBroken = false;
        this.pieces = new ArrayList<>();
    }

    // Move the shell in its current direction
    public void move() {
    	// condition so that it stops moving in this direct if the edge of screen is hit or broken
        if (!isBroken && position.y > 8) {
        	// 
            position.x += speed * Math.cos(Math.toRadians(rotation));
            position.y += speed * Math.sin(Math.toRadians(rotation));
        }
        
        
    }

    // Break the shell into pieces
    public void breakApart() {
        isBroken = true;
        int numPieces = 5; // Number of pieces to create
        for (int i = 0; i < numPieces; i++) {
            double pieceRotation = Math.random() * 360; // Random direction for each piece
            ShellPiece piece = new ShellPiece(getShape(), new Point(position.x, position.y), pieceRotation, speed);
            pieces.add(piece);
        }
    }

    // Check if the shell is broken
    public boolean isBroken() {
        return isBroken;
    }

    // Get the list of shell pieces
    public List<ShellPiece> getPieces() {
        return pieces;
    }


    public void paint(Graphics brush) {
        if (!isBroken) {
            // Draw the shell
            Point[] pArray = super.getPoints();
            int[] xPoints = new int[pArray.length];
            int[] yPoints = new int[pArray.length];

            for (int i = 0; i < pArray.length; i++) {
                xPoints[i] = (int) pArray[i].x;
                yPoints[i] = (int) pArray[i].y;
            }

            brush.fillPolygon(xPoints, yPoints, pArray.length);
        } else {
            // Draw the shell pieces
            for (ShellPiece piece : pieces) {
                piece.paint(brush);
            }
        }
    }
}
