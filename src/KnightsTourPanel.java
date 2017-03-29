import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/*
 * This class will be the display and will get the starting position
 * of the knight using a mousePress.  It should also have the data
 * like the 2D array and the current location of the knight.  The 
 * paintComponent method should redraw the view from the beginning, as it
 * always should.
 */

public class KnightsTourPanel extends JPanel implements MouseListener {

	// what private data is needed?
	int WIDTH;
	int HEIGHT;
	int GRID_WIDTH;
	int GRID_HEIGHT;
	private Timer t;
	Dimension d;
	private boolean clicked = false;
	public int[][] knightGrid = {{2,3,4,4,4,4,3,2},
			{3,4,6,6,6,6,4,3},
			{4,6,8,8,8,8,6,4},
			{4,6,8,8,8,8,6,4},
			{4,6,8,8,8,8,6,4},
			{4,6,8,8,8,8,6,4},
			{3,4,6,6,6,6,4,3},
			{2,3,4,4,4,4,3,2}
	};


	public KnightsTourPanel(int w, int h) {
		d = new Dimension(w,h);
		this.setPreferredSize(d);
		this.setBackground(new Color(150,150,150));
		knightGrid = new int[8][8];
		WIDTH = w;
		HEIGHT = h;
		GRID_WIDTH = WIDTH/knightGrid.length;
		GRID_HEIGHT = HEIGHT/knightGrid[0].length;
		addMouseListener();
//		t.start();
	}

	// add the mouse listener.  This will only work for the 
	// first click, and then after the first click, there should
	// be no more mouse listening!

	private static void addMouseListener() {

	}
	private void makePanel() {
		t = new Timer(10, new ActionListener() {// fires off every 10 ms
			@Override
			public void actionPerformed(ActionEvent arg0) {
				this.tick();
				repaint();// naturally, we want to see the new view
			}
			private void tick() {
				// TODO Auto-generated method stub

			}
		});
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int r = 0; r < knightGrid.length; r++){
			for(int c = 0; c< knightGrid[0].length;c++) {
				g.drawRect(r*GRID_WIDTH, c*GRID_HEIGHT, GRID_WIDTH, GRID_HEIGHT);
			}
		}
	}
	/* make random move just selects a new location at random
	 * if the knight is trapped (no new locations to move to)
	 * then false is returned.  Otherwise, true is returned.
	 * The knight's location should be updated and the 
	 */
	public boolean makeRandomMove() {
		System.out.println("Making random moves");
		int row = (WIDTH/GRID_WIDTH);
		int col = (HEIGHT/GRID_HEIGHT);
		int randr = (int) (row*Math.random());
		int randC = (int) (col*Math.random());
		knightGrid[randr][randC]= 0;
		// this only works for one time lol, it doesnt take into repeats and stuff so you might wanna rewrite this again or i can do it tomorrow in class
		return false;
	}
	/* make a move to a new location that ensures the best chance
	 * for a complete traversal of the board.
	 * if the knight is trapped (no new locations to move to)
	 * then false is returned.  Otherwise, true is returned.
	 */
	public boolean makeThoughtfulMove() {

		return false;
	}

	public void reachableDecrement() {

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(clicked == false) {
			System.out.println("Clicked! " + e.getButton());
			clicked = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void pause() {
		System.out.println("Simulation Paused");
	}

	public void start() {
		System.out.println("Simulation Started");
	}
}
