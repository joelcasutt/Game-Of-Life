package ch.javaCourse.gameOfLife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.javaCourse.gameOfLife.Controller.StartConfigurationListener;

class CollectionView extends JFrame {

    private static final long serialVersionUID = 100L;
    private int row, num, cellSize;
    private ArrayList<ArrayList<JButton>> buttons;
    private JButton startButton, randomButton, resetButton;
    private JTextField generationsToPlay;
    private JPanel playfield, buttonfield;
    private ViewMenuBar menuBar;

    CollectionView(int row, int num) {
	super("Game of Life");
	this.row = row;
	this.num = num;
	this.buttons = new ArrayList<ArrayList<JButton>>();
	this.startButton = new JButton("Start");
	this.randomButton = new JButton("Random");
	this.resetButton = new JButton("Reset");
	this.buttonfield = new JPanel(new FlowLayout());
	this.generationsToPlay = new JTextField("10", 3);
	this.playfield = new JPanel(new GridLayout(this.row, this.num));
	this.menuBar = new ViewMenuBar();
	initForm();
    }

    private void initForm() {
	this.cellSize = getCellSize(row,num);
	System.out.println(cellSize);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new BorderLayout());
	this.setBounds(200, 200, num * cellSize, row * cellSize + 30);

	this.add(menuBar, BorderLayout.NORTH);

	playfield.setBounds(0, 0, row * cellSize, num * cellSize);
	for (int i = 0; i < row; i++) {
	    buttons.add(new ArrayList<JButton>());
	    for (int j = 0; j < num; j++) {
		buttons.get(i).add(buttonFabric());
		playfield.add(buttons.get(i).get(j));
	    }
	}
	this.add(playfield, BorderLayout.CENTER);

	buttonfield.add(generationsToPlay);
	buttonfield.add(startButton);
	buttonfield.add(resetButton);
	buttonfield.add(randomButton);

	this.add(buttonfield, BorderLayout.SOUTH);
    }
    
    private static int getCellSize(int row, int num) {
	int newCellSize = 25;
	newCellSize = (int) Math.round(25.0*(25.0/((row+num)/2.0)));
	if (newCellSize < 10) {
	    newCellSize = 10;
	}
	return newCellSize;
    }

    private static JButton buttonFabric() {
	JButton b = new JButton();
	b.setBorderPainted(true);
	b.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
	b.setBackground(Color.GRAY);
	b.setOpaque(true);
	return b;
    }

    public int[] changeButtonColor(Object button) {
	JButton thisButton = (JButton) button;
	int[] position = { 0, 0 };
	outer: for (int i = 0; i < row; i++) {
	    for (int j = 0; j < num; j++) {
		if (thisButton == buttons.get(i).get(j)) {
		    position[0] = i;
		    position[1] = j;
		    break outer;
		}
	    }
	}
	if (thisButton.getBackground().equals(Color.GRAY)) {
	    thisButton.setBackground(Color.BLUE);

	} else {
	    thisButton.setBackground(Color.GRAY);
	}
	return position;
    }

    public void resizePlayfield(int rows, int nums) {
	this.playfield.setLayout(new GridLayout(rows, nums));
	playfield.setBounds(0, 0, rows * cellSize, nums * cellSize);
	buttons.clear();
	for (int i = 0; i < rows; i++) {
	    buttons.add(new ArrayList<JButton>());
	    for (int j = 0; j < nums; j++) {
		buttons.get(i).add(buttonFabric());
		playfield.add(buttons.get(i).get(j));
	    }
	}

    }

    // Getter and Setters
    public void setButtonColor(int row, int num, boolean doa) {
	if (doa == true) {
	    buttons.get(row).get(num).setBackground(Color.BLUE);
	} else {
	    buttons.get(row).get(num).setBackground(Color.GRAY);
	}
    }

    public int getGenerationsToPlay() {
	return Integer.parseInt(generationsToPlay.getText());
    }

    public void setStartButtonState(boolean onOff) {
	startButton.setEnabled(onOff);
    }

    // Listener related methods
    public void setStartConfigurationListener(
	    ArrayList<ArrayList<StartConfigurationListener>> l) {
	for (int i = 0; i < row; i++) {
	    for (int j = 0; j < num; j++) {
		buttons.get(i).get(j).addActionListener(l.get(i).get(j));
	    }
	}
    }

    public void setStartButtonListener(ActionListener l) {
	startButton.addActionListener(l);
    }

    public void setRandomButtonListener(ActionListener l) {
	randomButton.addActionListener(l);
    }

    public void setResetButtonListener(ActionListener l) {
	resetButton.addActionListener(l);
    }

    public void setGenerationTimeMenuItemListener(ActionListener l) {
	menuBar.getTimeBetweengenerationsMenuItem().addActionListener(l);
    }

    public void setResizePlayfieldMenuItemListener(ActionListener l) {
	menuBar.getResizePlayfieldMenuItem().addActionListener(l);
    }

    public void setSaveCurrentPlayfieldMenuItemListener(ActionListener l) {
	menuBar.getSavePlayfieldMenuItem().addActionListener(l);
    }

    public void setLoadPlayfieldMenuItemListener(ActionListener l) {
	menuBar.getLoadPlayfieldMenuItem().addActionListener(l);
    }

}
