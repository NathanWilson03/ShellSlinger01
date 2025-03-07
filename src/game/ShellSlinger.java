package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

class ShellSlinger extends Game {
	static int counter = 0;

  public ShellSlinger() {
    super("ShellSlinger",800,600);
    this.setFocusable(true);
	this.requestFocus();
	
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	
    	
    	
    	// this is our cannon object shape 
    	Point c1 = new Point(40,10);
    	Point c2= new Point(40,40);
		Point c3 = new Point(10,40);
		
		Point c4 = new Point(0,50);
		Point c5 = new Point(0,0);
		Point c6 = new Point(10, 10);
		
		
		Point[] canonPoints = {c1,c2,c3,c4,c5,c6};
		
		
    	Canon startCanon = new Canon(canonPoints, new Point(10,550), 133);
    	Canon aimCanon = new Canon(canonPoints, new Point(765, 550), 60);
    	
    	brush.setColor(new Color(34, 177, 76));
    	startCanon.paint(brush);
    	
    	brush.setColor(new Color(160,40,20));
    	
    	aimCanon.paint(brush);
    	
    	
    	// these are the points for our bucket object
    	
    	Point b1 = new Point(0,0);
    	Point b2 = new Point(5,5);
    	Point b3 = new Point(40,10);
    	Point b4 = new Point(40,40);
    	Point b5 = new Point(5,45);
    	Point b6 = new Point(0,50);
    	
    	Point[] bucketPoints = {b1,b2,b3,b4,b5,b6};
    	
    	Bucket bucket = new Bucket(bucketPoints, new Point(770,200), 0);
    	Bucket bucket2 = new Bucket(bucketPoints, new Point(770,400), 0);
    	brush.setColor(new Color(211, 175, 55));
    	bucket.paint(brush);
    	bucket2.paint(brush);
    	
    	
    	
    	
    	
    	
    	
   
    	
  }
	
	
  
	public static void main (String[] args) {
   		ShellSlinger a = new ShellSlinger();
		a.repaint();
  }
}