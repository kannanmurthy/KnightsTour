import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

public class KnightsTour {

	private static final int ROWS =  8;
	private static final int COLS = 8;
	private static final int SQUARE_SIZE = 80;
	private static final Dimension PANEL_DIMENSIONS = new Dimension(ROWS * SQUARE_SIZE, COLS * SQUARE_SIZE + 80);
	private JFrame frame;
	private KnightsTourPanel panel;
	private Timer t;
	private Timer playTimer;
	private int[][] knightGrid;
	private int[][] iterations;
	private boolean randomMode;
	private boolean shouldPlay;
	private int currRow;
	private int currCol;
	private int currIteration;

	public static void main(String[] args) {
		new KnightsTour().start();
	}

	public void start() {
		knightGrid = new int[ROWS][COLS];
		initializeIterationArr();
		randomMode = true;
		frame = new JFrame("KnightsTour!!");
		panel = new KnightsTourPanel(this, knightGrid, iterations);
		frame.add(panel);	
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		startTimer();
		startPlayTimer();
	}

	public void setStartPosition(int row, int col) {
		this.currRow = row;
		this.currCol = col;
	}

	public void shouldPlay() {
		shouldPlay = !shouldPlay;
	}

	public boolean step() {
		dispConsole(this.knightGrid);
		if (!containsSelectedSquare()) {
			JOptionPane.showMessageDialog(null, "Select a square");
			return false;
		}
		if (randomMode) {
			return makeRandomMove();
		} else {
			return makeThoughtfulMove();
		} 
	}

	//resets game
	public void clear() {
		this.knightGrid = new int[ROWS][COLS];
		initializeIterationArr();
		panel.setIterations(this.iterations);
		panel.canSelectSquare();
	}


	public void updateSpeed(int newSpeed) {
		playTimer.setDelay(newSpeed);
	}

	public void updateMode(boolean randomMode) {
		this.randomMode = randomMode;
	}

	public void play() {
		if (shouldPlay) {
			if (!step()) {
				pause();
			}	
		}
	}

	private void initializeIterationArr() {
		iterations = new int[ROWS][COLS];
		for (int r = 0; r < iterations.length; r++) {
			for (int c = 0; c < iterations[0].length; c++) {
				iterations[r][c] = -1;
			}
		}
	}

	private void startTimer() {
		t = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.refresh();
			}
		});
		t.start();	
	}

	private void startPlayTimer() {
		playTimer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		playTimer.start();
	}

	private boolean makeRandomMove() {
		int newRow;
		int newCol;

		while (true) {
			if (numMovesAvailable(currRow, currCol) <= 0) {
				JOptionPane.showMessageDialog(null, "No moves possible");
				return false;
			}

			newRow = (int)(8*Math.random());
			newCol = (int)(8*Math.random());

			if (canMakeMove(newRow, newCol)) {
				updateLoc(newRow, newCol);
				panel.setGrid(knightGrid);
				break;
			}
		}
		return true;
	}

	private boolean makeThoughtfulMove() {
		HashMap<Location, Integer> moves = new HashMap<>();
		int numMovesAvailable = numMovesAvailable(currRow, currCol);
		while (true) {
			if (numMovesAvailable(currRow, currCol) == 0) {
				JOptionPane.showMessageDialog(null, "No moves possible");
				return false; 
			}
			//sets up the map with possible places to go to
			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					Location l = new Location(c, r);
					if ((r != currRow && c != currCol) && canMakeMove(currRow, r, currCol, c) && (!moves.containsKey(l))) {
						moves.put(l, numMovesAvailable(r, c));
					}
				}
			}

			if (moves.size() == numMovesAvailable) {
				Integer value = Collections.min(moves.values());
				Location bestLocation = null;
				for (Map.Entry<Location, Integer> map : moves.entrySet()) {
					if (map.getValue().equals(value)) {
						bestLocation = map.getKey();
						break;
					}
				}
				updateLoc(bestLocation.y(), bestLocation.x());
				break;
			}
		}
		return true;
	}

	private void updateLoc(int newRow, int newCol) {
		knightGrid[currRow][currCol] = 0;
		knightGrid[newRow][newCol] = 1;
		currRow = newRow;
		currCol = newCol;
		updateIterations(newRow, newCol);
	}

	private void updateIterations(int row, int col) {
		iterations[row][col] = ++currIteration;
		panel.setIteration(row, col, currIteration);
	}

	private void pause() {
		shouldPlay = false;
		panel.pause();
	}

	private void dispConsole(int[][] grid) {
		for (int[] r : grid) {
			for (int c : r) {
				switch (c) {
					case 0:
						System.out.print("[ ]");
						break;
					case 1:
						System.out.print("[K]");
						break;
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private boolean containsSelectedSquare() {
		for (int[] r : knightGrid) {
			for (int c : r) {
				if (c == 1) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canMakeMove(int newRow, int newCol) {
		if (iterations[newRow][newCol] > -1) { return false; }
		int row = Math.abs(newRow - currRow);
		int col = Math.abs(newCol - currCol);
		return ((row == 2 && col == 1) || (row == 1 && col == 2));
	}

	private int numMovesAvailable(int row, int col) {
		int numMoves = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (canMakeMove(row, r, col, c)) {
					numMoves++;
				}
			}
		}
		return numMoves;
	}

	private boolean canMakeMove(int startRow, int newRow, int startCol, int newCol) {
		if (iterations[newRow][newCol] > -1) { return false; }
		int row = Math.abs(newRow - startRow);
		int col = Math.abs(newCol - startCol);
		return ((row == 2 && col == 1) || (row == 1 && col == 2));
	}

}
