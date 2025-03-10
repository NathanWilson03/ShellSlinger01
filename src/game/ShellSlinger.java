package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class ShellSlinger extends Game{
	static int counter = 0;
	private Bucket bucket;
	private Canon startCanon, aimCanon;
	private Shell shell;
	private Bullet bullet;
	private ShellPiece shellPiece;

	public ShellSlinger() {
		super("ShellSlinger", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
		

		// BUCKET SHAPE
		Point b1 = new Point(0, 0);
		Point b2 = new Point(5, 5);
		Point b3 = new Point(40, 10);
		Point b4 = new Point(40, 40);
		Point b5 = new Point(5, 45);
		Point b6 = new Point(0, 50);

		Point[] bucketPoints = { b1, b2, b3, b4, b5, b6 };

		// TWO BUCKETS(AT INITIAL POSITION)
		bucket = new Bucket(bucketPoints, new Point(770, 200), 0);
	
		
		
		this.addKeyListener(bucket);


		// CANON SHAPE
		Point c1 = new Point(50, 15);
		Point c2 = new Point(50, 35);
		Point c3 = new Point(15, 35);

		Point c4 = new Point(10, 40);
		Point c5 = new Point(0, 40);
	
		Point c6 = new Point(0, 5);
		Point c7 = new Point(10, 5);
		Point c8 = new Point(15, 10);
		
	
		

		Point[] canonPoints = { c1, c2, c3, c4, c5, c6,c7,c8 };
		

		// TWO CANONS(AT INITIAL POSITION)
		startCanon = new Canon(canonPoints, new Point(10, 550), 133, false);
		aimCanon = new Canon(canonPoints, new Point(765, 550), 240, true);
		
		this.addMouseMotionListener(aimCanon);
		
		
		// SHELL SHAPE 
		
		Point s1 = new Point(0, 0);  
		Point s2 = new Point(-5, -10); 
		Point s3 = new Point(-15, -15); 
		Point s4 = new Point(-25, -10); 
		Point s5 = new Point(-30, 0);  
		Point s6 = new Point(-25, 10); 
		Point s7 = new Point(-15, 15); 
		Point s8 = new Point(-5, 10);  
	
		
		Point[] shellPoints = {s1,s2,s3,s4,s5,s6,s7,s8};
		
		// -45 is the rotation needed so that the cos() of the rotation 
		// in radiant is at the angle of the canon
		// see Shell move() method 
		shell = new Shell(shellPoints, new Point(15, 550),-45,2 );
		
		
		
		// BULLET SHAPE 
		Point f1 = new Point(0,10);
		Point f2 = new Point(10,15);
		Point f3 = new Point(20,10);
		Point f4 = new Point(30,5);
		Point f5 = new Point(30,15);
		Point f6 = new Point(20,10);
		Point f7 = new Point(10,5);
		
		Point[] bulletPoints = {f1,f2,f3,f4,f5,f6,f7};
		
		bullet = new Bullet(bulletPoints, new Point(760, 550),aimCanon.rotation,4 );	
		
		this.addMouseMotionListener(bullet);
		this.addMouseListener(bullet);
		
	
	
	}

	public void paint(Graphics brush) {
		brush.setColor(Color.black);
		brush.fillRect(0, 0, width, height);

		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted

		brush.setColor(new Color(0, 130, 0));
		startCanon.paint(brush);

		brush.setColor(new Color(212, 15, 55));

		bullet.move();
		bullet.paint(brush);


		// no need for a move since
		// addMouseListener with parameter aimCanon calls our update method, when the mouse is moved
		
		aimCanon.paint(brush);

		// these are the points for our bucket object

		// Bucket bucket2 = new Bucket(bucketPoints, new Point(770,400), 0);

	

		brush.setColor(new Color(30, 144, 255));
		bucket.move();
		bucket.paint(brush);
		
		brush.setColor(new Color(34, 139, 34));

		shell.move();
		shell.paint(brush);
	
		
	
	}
	

	

	public static void main(String[] args) {
		
		String[] options = {"Easy", "Medium", "Hard"};
        // Show the custom option pane
        int choice = JOptionPane.showOptionDialog(
            null,
            "Choose an Difficulty:",
            "Custom Dialog",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0] // Default selection
        );
        
        
        
		ShellSlinger a = new ShellSlinger();
		a.repaint();
		
		

	
    
    

	
	}

}