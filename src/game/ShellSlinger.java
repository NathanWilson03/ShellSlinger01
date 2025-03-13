package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * The name of the game is Shell Slinger. The objective is 
 * to aim your canon at the shell, shot out of the starting canon 
 * and shoot. The catch is we have ricochets floating in the space.
 * If the user wants to get a really high score, they can aim for the 
 * ricochet, the bullet will bounce off and hopefully in the direction of the shell.
 * If the shell is hit by the bullet, it will explode. The second part to the game is 
 * you also have two buckets on the border of the screen in which you can move around 
 * using your A-D keys and J-L keys. You want to try and collect as much of the shell 
 * pieces before they all go off screen. Your score is then calculated, and is multiplied 
 * by the difficulty you chose and the number of ricochets that you hit 
 * 
 * Fields for this class include 
* 
* <ul>
* <li>bucket, bucket2 - Two Bucket objects that sit and move on the border of the screen 
* <li>startCanon, aimCanon - Two canon objects. One that can aim and one that starts the game 
* <li> shell - The target object that you want to hit 
* <li> bullet - the bullet object that you shoot at the shell 
* <li> difficulty - an int that determines the difficulty that the user chooses 
* <li> score - The users score 
* 
* bullet will just follow the mouse the whole time, otherwise)
*/

class ShellSlinger extends Game {
    static int counter = 0;
    private Bucket bucket, bucket2;
    private Canon startCanon, aimCanon;
    private Shell shell;
    private Bullet bullet;
    private int difficulty; // Difficulty level (1 = Easy, 2 = Medium, 3 = Hard)
    private int score;
    private boolean isOver;
    private ArrayList<Ricochet> ricochet;
    static int frame = 0;


    
    /**
     * Creates the shell slinger game.
     * First we initialize the difficulty to the one passed in 
     * , the score to 0 and create a new arrayList of Ricochets 
     * Then we actually start to make our shapes starting with bucket 
     * and putting them on the right and left side(middle) screen and 
     * add the essential keyListener to the window 
     * We then initilaze the two canons in a similar way but add a 
     * mouseMotionListener and passing in the object 
     * We do this with the rest of the object but when we make the Richochet 
     * we use a different process(a switch statement) that allows the number of 
     * ricochets to be determined by the difficulty 
     * 
     * 
     * @param difficulty
     */
    public ShellSlinger(int difficulty) {
        super("ShellSlinger", 800, 600);
        this.setFocusable(true);
        this.requestFocus();
        this.difficulty = difficulty;
        this.score = 0;
        this.ricochet = new ArrayList<Ricochet>();

        // BUCKET SHAPE
        Point[] bucketPoints = {
            new Point(0, 0), new Point(5, 5), new Point(40, 10),
            new Point(40, 40), new Point(5, 45), new Point(0, 50)
        };

        // Initialize the bucket
        bucket = new Bucket(bucketPoints, new Point(770, 200), 0);
        bucket2 = new Bucket(bucketPoints, new Point(20, 200), 180);
        this.addKeyListener(bucket2);
        this.addKeyListener(bucket);


        // CANON SHAPE
        Point[] canonPoints = {
            new Point(50, 15), new Point(50, 35), new Point(15, 35),
            new Point(10, 40), new Point(0, 40), new Point(0, 5),
            new Point(10, 5), new Point(15, 10)
        };

        // Initialize the canons
        startCanon = new Canon(canonPoints, new Point(10, 550), 133, false);
        aimCanon = new Canon(canonPoints, new Point(765, 550), 240, true);
        this.addMouseMotionListener(aimCanon);

        // SHELL SHAPE
        Point[] shellPoints = {
            new Point(0, 0), new Point(-5, -10), new Point(-15, -15),
            new Point(-25, -10), new Point(-30, 0), new Point(-25, 10),
            new Point(-15, 15), new Point(-5, 10)
        };

        // Initialize the shell with difficulty
        shell = new Shell(shellPoints, new Point(15, 550), -45, 1, difficulty);

        // BULLET SHAPE
        Point[] bulletPoints = {
            new Point(0, 10), new Point(10, 15), new Point(20, 10),
            new Point(30, 5), new Point(30, 15), new Point(20, 10),
            new Point(10, 5)
        };

        // Initialize the bullet
        bullet = new Bullet(bulletPoints, new Point(760, 550), aimCanon.rotation, 4);
        this.addMouseMotionListener(bullet);
        this.addMouseListener(bullet);
        
        
        // for the Ricochet object
        Point r1 = new Point(0, 0);
		Point r2 = new Point(100, 0);
		Point r3 = new Point(100, 2);
		Point r4 = new Point(0, 2);
		Point[] rPoints = { r1, r2, r3, r4 };
		
		// based on the difficulty we change the number/position of the ricochet 
		switch (this.difficulty) {
		case 1:
			this.ricochet.add(new Ricochet(rPoints, new Point(50, 250), 90));
			this.ricochet.add(new Ricochet(rPoints, new Point(400, 50), 180));
			break;
		case 2:
			this.ricochet.add(new Ricochet(rPoints, new Point(650, 100), 45));
			this.ricochet.add(new Ricochet(rPoints, new Point(50, 100), -45));
			this.ricochet.add(new Ricochet(rPoints, new Point(50, 450),45));
			break;
		case 3:
			this.ricochet.add(new Ricochet(rPoints, new Point(50, 250), 90));
			this.ricochet.add(new Ricochet(rPoints, new Point(400, 50), 180));
			this.ricochet.add(new Ricochet(rPoints, new Point(650, 100), 45));
			this.ricochet.add(new Ricochet(rPoints, new Point(50, 100), -45));
			this.ricochet.add(new Ricochet(rPoints, new Point(50, 450),45));
			break;
		}

    }

    /**
     * The recursive method where we call the paint and move 
     * methods from all the object classes in our game
     * Also setting the color and such 
     * Lastly just displaying the score 
     */
    @Override
    public void paint(Graphics brush) {
        brush.setColor(Color.black);
        brush.fillRect(0, 0, width, height);

        // Draw the canons
        brush.setColor(new Color(0, 130, 0));
        startCanon.paint(brush);

        brush.setColor(new Color(212, 15, 55));
        aimCanon.paint(brush);

        // Move and draw the bullet
        bullet.move();
        bullet.paint(brush);

        // Move and draw the bucket
        brush.setColor(new Color(30, 144, 255));
        bucket2.move2();
        bucket2.paint(brush);
        bucket.move();
        bucket.paint(brush);
       

        // Move and draw the shell
        brush.setColor(new Color(34, 139, 34));
        shell.move();
        shell.paint(brush);

        // Check for collision between the shell and the bullet
        if (shell.collidesWith(bullet)) {
            shell.breakApart();
        }
        
        brush.setColor(new Color(34, 139, 34));
		for(Ricochet r : this.ricochet) {
			r.paint(brush);
		}
        for (Ricochet r : this.ricochet) {
			r.paint(brush);
			if (r.collidesWith(bullet) && frame > 10) {
				bullet.ricochet(r.getRotation());
				frame = 0;
			}
		}


        // Draw shell pieces if the shell is broken
        if (shell.isBroken()) {
            for (ShellPiece piece : shell.getPieces()) {
            	
                piece.move();
                piece.paint(brush);
                
           
            }

            // Check for collisions between the bucket and shell pieces
            int collected = shell.collectPieces(bucket);
            collected += shell.collectPieces(bucket2);
            score += collected;
        }

        // Display the score
        brush.setColor(Color.WHITE);
        brush.drawString("Score: " + score, 10, 20);
        
        frame++;
      
    }
    
    
    
    public static void main(String[] args) {
        String[] options = {"Easy", "Medium", "Hard"};
        int choice = JOptionPane.showOptionDialog(
            null,
            "Choose a Difficulty:",
            "Custom Dialog",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0] // Default selection
        );

        int difficulty = choice + 1; // Easy: 1, Medium: 2, Hard: 3
        ShellSlinger game = new ShellSlinger(difficulty);
        game.repaint();
    }
}

