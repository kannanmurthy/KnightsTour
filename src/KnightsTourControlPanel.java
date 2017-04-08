import java.awt.*;
import java.util.Hashtable;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;

public class KnightsTourControlPanel extends JPanel {
	
	private JButton startButton;
	private JButton nextButton;
	private JButton resetButton;
	private JSlider speedSlider;
	private JRadioButton randomModeRadioButton;
	private JRadioButton optimizedModeRadioButton;
	private KnightsTour knightsTour;
	final static int MIN_SPEED = 1200;
	final static int MAX_SPEED = 300;

	public KnightsTourControlPanel(int w, int h, KnightsTour knightsTour) {
		this.setPreferredSize(new Dimension(w, h));
		this.setBackground(Color.BLUE);
		this.knightsTour = knightsTour;
		this.setLayout(null);
		setUpButtonsAndSliders();
	}

	private void setUpButtonsAndSliders() {
		startButton = new JButton("Start");
		nextButton = new JButton("Next");
		resetButton = new JButton("Reset");
		speedSlider = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED);
		randomModeRadioButton = new JRadioButton("Random");
		optimizedModeRadioButton = new JRadioButton("Optimized");

		randomModeRadioButton.setSelected(true);
		speedSlider.setInverted(true);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(MAX_SPEED), new JLabel("                Fast"));
		labelTable.put(new Integer(MIN_SPEED), new JLabel("Slow"));
		speedSlider.setLabelTable(labelTable);
		speedSlider.setPaintLabels(true);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startButtonAction();
			}
		});

		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextButtonAction();
			}
		});

		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonAction();
			}
		});

		speedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				speedSliderAction();
			}
		});

		randomModeRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				randomModeRadioButtonAction();
			}
		});

		optimizedModeRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				algorithmModeRadioButtonAction();
			}
		});

		this.add(startButton);
		this.add(nextButton);
		this.add(resetButton);
		this.add(speedSlider);
		this.add(randomModeRadioButton);
		this.add(optimizedModeRadioButton);
	}

	public void pause() {
		startButton.setText("Start");
		 currentlyPlaying = false;
	}

	boolean currentlyPlaying = false;
	private void startButtonAction() {
		if (currentlyPlaying) {
			startButton.setText("Start");
			currentlyPlaying = false;
		} else {
			startButton.setText("Stop");
			currentlyPlaying = true;
		}
		knightsTour.shouldPlay();
		knightsTour.play();
	}

	private void nextButtonAction() {
		knightsTour.step();
	}

	private void resetButtonAction() {
		knightsTour.clear();
	}

	private void speedSliderAction() {
		knightsTour.updateSpeed(speedSlider.getValue());
	}

	private void randomModeRadioButtonAction() {
		knightsTour.updateMode(true);
		if (randomModeRadioButton.isSelected()) {
			optimizedModeRadioButton.setSelected(false);
		}
	}

	private void algorithmModeRadioButtonAction() {
		knightsTour.updateMode(false);
		if (optimizedModeRadioButton.isSelected()) {
			randomModeRadioButton.setSelected(false);
		}
	}


}
