package game;


import java.awt.Graphics;

public class Bucket extends Polygon{
	
	// These objects will be controlled by the user and glide across the border 
	// to collect the pieces that break off from  the explosion 
	// I think there should be 2 but lmk 

	public Bucket(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		// TODO Auto-generated constructor stub
	}
	
	
	public void paint(Graphics brush) {
		
		
		Point[] pArray = super.getPoints();
		int[] xPoints = new int[pArray.length];
		int[] yPoints = new int[pArray.length];
		
		for(int i = 0; i < pArray.length; i++) {
			xPoints[i] = (int)pArray[i].x;
			yPoints[i] = (int)pArray[i].y;
			
			
		}
		
		
		
		brush.fillPolygon(xPoints, yPoints, pArray.length);
		
		
		
	}

}
