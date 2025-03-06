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
    	Point p1 = new Point(40,10);
    	Point p2= new Point(40,40);
		Point p3 = new Point(10,40);
		
		Point p4 = new Point(0,50);
		Point p5 = new Point(0,0);
		Point p6 = new Point(10, 10);
		
		
	
		
		Point[] canonPoints = {p1,p2,p3,p4,p5,p6};
		
		
    	Canon startCanon = new Canon(canonPoints, new Point(10,550), 133);
    	Canon aimCanon = new Canon(canonPoints, new Point(765, 550), 60);
    	
    	brush.setColor(new Color(34, 177, 76));
    	startCanon.paint(brush);
    	
    	brush.setColor(new Color(160,40,20));
    	
    	aimCanon.paint(brush);
    	
    	
   
    	
  }
	
	
  
	public static void main (String[] args) {
   		ShellSlinger a = new ShellSlinger();
		a.repaint();
  }
}