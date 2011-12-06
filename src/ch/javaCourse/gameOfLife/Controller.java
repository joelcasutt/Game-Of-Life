package ch.javaCourse.gameOfLife;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import ch.javaCourse.gameOfLife.menuFunctionality.LoadPlayfieldView;
import ch.javaCourse.gameOfLife.menuFunctionality.ResizePlayfieldView;
import ch.javaCourse.gameOfLife.menuFunctionality.SaveCurrentPlayfieldView;
import ch.javaCourse.gameOfLife.menuFunctionality.UpdateTimeView;

public class Controller {

    private int row, num;
    private static int timeBetweenGenerations = 250;
    private CollectionView view;
    private CollectionModel model;

    private UpdateTimeView updateTimeView;
    private ResizePlayfieldView resizePlayfieldView;
    private SaveCurrentPlayfieldView saveCurrentPlayfieldView;
    private LoadPlayfieldView loadPlayfieldView;

    public Controller(int row, int num) {
	this.row = row;
	this.num = num;
	this.view = new CollectionView(row, num);
	this.model = new CollectionModel(row, num);

	this.updateTimeView = new UpdateTimeView();
	this.resizePlayfieldView = new ResizePlayfieldView();
	this.saveCurrentPlayfieldView = new SaveCurrentPlayfieldView();
	this.loadPlayfieldView = new LoadPlayfieldView();

	addListener();
    }

    public Controller(int size) {
	this(size, size);
    }

    public Controller() {
	this(25);
    }

    public void showView() {
	this.view.setVisible(true);
    }

    public static void setTimeBetweenGenerations(int time) {
	timeBetweenGenerations = time;
    }

    private void addListener() {
	ArrayList<ArrayList<StartConfigurationListener>> startConfigurationListeners = new ArrayList<ArrayList<StartConfigurationListener>>();
	for (int i = 0; i < row; i++) {
	    startConfigurationListeners
		    .add(new ArrayList<StartConfigurationListener>());
	    for (int j = 0; j < num; j++) {
		startConfigurationListeners.get(i).add(
			new StartConfigurationListener());
	    }
	}
	this.view.setStartConfigurationListener(startConfigurationListeners);
	this.view.setStartButtonListener(new StartButtonListener());
	this.view.setRandomButtonListener(new RandomButtonListener());
	this.view.setResetButtonListener(new ResetButtonListener());
	this.view
		.setGenerationTimeMenuItemListener(new GenerationTimeMenuListener());
	this.view
		.setResizePlayfieldMenuItemListener(new ResizePlayfieldMenuListener());
	this.view
		.setSaveCurrentPlayfieldMenuItemListener(new SavePlayfieldMenuListener());
	this.view
		.setLoadPlayfieldMenuItemListener(new LoadPlayfieldMenuListener());
	this.updateTimeView
		.setUpdateButtonListener(new UpdateConfigurationListener());
	this.resizePlayfieldView
		.setUpdateResizeButtonListener(new ResizePlayfieldListener());
    }

    class StartConfigurationListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    Object button = arg0.getSource();
	    int[] position = view.changeButtonColor(button);
	    model.setOneState(position[0], position[1]);
	}
    }

    class StartButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    view.setStartButtonState(false);

	    Thread oneGeneration = new Thread() {
		public void run() {
		    int generationsToPlay = view.getGenerationsToPlay();
		    for (int g = 0; g < generationsToPlay; g++) {
			ArrayList<ArrayList<Boolean>> newGeneration = model
				.getNextGeneration();
			for (int i = 0; i < row; i++) {
			    for (int j = 0; j < num; j++) {
				view.setButtonColor(i, j, newGeneration.get(i)
					.get(j));
			    }
			}
			try {
			    Thread.sleep(timeBetweenGenerations);
			} catch (InterruptedException e1) {
			    e1.printStackTrace();
			}
		    }
		    view.setStartButtonState(true);
		}
	    };
	    oneGeneration.start();
	}
    }

    class RandomButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    ArrayList<ArrayList<Boolean>> randomField = model
		    .generateRandomConfiguration();
	    for (int i = 0; i < row; i++) {
		for (int j = 0; j < num; j++) {
		    view.setButtonColor(i, j, randomField.get(i).get(j));
		}
	    }
	}
    }

    class ResetButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {

	    model.reset();
	    for (int i = 0; i < row; i++) {
		for (int j = 0; j < num; j++) {
		    view.setButtonColor(i, j, false);
		}
	    }
	}
    }

    class GenerationTimeMenuListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent arg0) {
	    updateTimeView.setVisible(true);
	}
    }

    class ResizePlayfieldMenuListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    resizePlayfieldView.setVisible(true);
	}
    }

    class SavePlayfieldMenuListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    String playfield = model.getPlayfield();

	    int retVal = saveCurrentPlayfieldView.showSaveDialog(null);
	    if (retVal == JFileChooser.APPROVE_OPTION) {
		BufferedWriter outFile = null;
		try {
		    outFile = new BufferedWriter(new FileWriter(
			    saveCurrentPlayfieldView.getSelectedFile()));
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		try {
		    outFile.write(playfield);
		    outFile.flush();
		    outFile.close();
		    return;
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	    }
	    System.out.println("no good file...");

	}
    }

    class LoadPlayfieldMenuListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {

	    int retVal = loadPlayfieldView.showOpenDialog(null);
	    ArrayList<String> playfield = new ArrayList<String>();
	    if (retVal == JFileChooser.APPROVE_OPTION) {
		BufferedReader inFile = null;
		try {
		    inFile = new BufferedReader(new FileReader(
			    loadPlayfieldView.getSelectedFile()));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		try {
		    String currentLine = "";
		    while ((currentLine = inFile.readLine()) != null) {
			playfield.add(currentLine);
		    }
		    inFile.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    ArrayList<ArrayList<Boolean>> newField = model
		    .setPlayfield(playfield);
	    for (int i = 0; i < row; i++) {
		for (int j = 0; j < num; j++) {
		    view.setButtonColor(i, j, newField.get(i).get(j));
		}
	    }
	}
    }

    class UpdateConfigurationListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {

	    try {
		Controller.setTimeBetweenGenerations(updateTimeView
			.getInputTime());
		updateTimeView.setVisible(false);
		updateTimeView = null;

	    } catch (NumberFormatException e) {
		updateTimeView.setInputTextColor(Color.RED);
	    }
	}

    }

    class ResizePlayfieldListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    int rows = resizePlayfieldView.getInputRow();
	    int nums = resizePlayfieldView.getInputNum();
	    String[] newDimension = { String.valueOf(rows),
		    String.valueOf(nums) };
	    GameOfLifeStarter.main(newDimension);
	    resizePlayfieldView.setVisible(false);
	    view.setVisible(false);

	}
    }

}
