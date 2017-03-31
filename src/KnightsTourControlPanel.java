import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
/* One of the tricky things you have to figure out is how to have
 * the controls in the control panel talk to the KnightsTourPanel.
 * I know you'll figure out a way.  DON'T USE STATIC METHODS!!!!!
 */
public class KnightsTourControlPanel extends JPanel {

	int WIDTH;
	int BUTTON_WIDTH;
	int BUTTON_HEIGHT;
	int BUTTON_Y;
	int INCREMENT;
	KnightsTourPanel TP;
	private boolean clicked = false;

	public KnightsTourControlPanel(int w, int h, KnightsTourPanel tp) {
		TP = new KnightsTourPanel(w, h * 3);
		WIDTH = w;
		BUTTON_WIDTH = 140;
		BUTTON_HEIGHT = 50;
		BUTTON_Y = 80;
		INCREMENT = (WIDTH - (4 * BUTTON_WIDTH)) / 5;
		this.setPreferredSize(new Dimension(w, h));
		this.setBackground(new Color(51, 102, 255));
		setUpButtonsAndSliders();
		tp = TP;
	}


	/* Add all the buttons and sliders used to control this Knight's tour.
	 * It is best if you allow the placement of the components to be handled
	 * by a layout manager.  You can find out lots about layouts if you google
	 * it!  You can also bind key events to the buttons and sliders, as well
	 */
	private void setUpButtonsAndSliders() {

		int MIN = 0;
		int MAX = 100;
		int INTERVAL = 15;

		JLabel speedLabel = new JLabel("Speed"); //i cant get this label to show up on the panel
		this.setLayout(null);
		this.add(speedLabel);
		speedLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		speedLabel.setLocation(10, 10);

		JButton start = new JButton("Start");
		this.add(start);
		start.setBounds(INCREMENT, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		start.setFont(new Font("Arial", Font.PLAIN, 30));
		start.setFocusPainted(false);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TP.start();
				repaint();
			}

			private void getKnightsTourPanel(KnightsTourPanel tp) {
				tp.start();
			}
		});

		JButton pause = new JButton("Pause");
		this.add(pause);
		pause.setBounds((2 * INCREMENT + BUTTON_WIDTH), BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		pause.setFont(new Font("Arial", Font.PLAIN, 30));
		pause.setFocusPainted(false);
		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TP.pause();
				repaint();
			}

		});


		JButton optimize = new JButton("Optimize");
		this.add(optimize);
		optimize.setBounds(3 * INCREMENT + 2 * BUTTON_WIDTH, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		optimize.setFont(new Font("Arial", Font.PLAIN, 30));
		optimize.setFocusPainted(false);
		optimize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TP.makeThoughtfulMove();
				repaint();
			}

		});

		JButton random = new JButton("Random");
		this.add(random);
		random.setBounds(4 * INCREMENT + 3 * BUTTON_WIDTH, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		random.setFont(new Font("Arial", Font.PLAIN, 30));
		random.setFocusPainted(false);
		random.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TP.makeRandomMove();
				repaint();
			}

		});

		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL,
				MIN, MAX, INTERVAL);
		speedSlider.setMajorTickSpacing(10);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		this.add(speedSlider);
		speedSlider.setBounds(260, 10, 280, 40);


	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}

