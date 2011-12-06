package ch.javaCourse.gameOfLife;

import java.util.ArrayList;
import java.util.Scanner;

class CollectionModel {

    private int row, num;
    private ArrayList<ArrayList<Boolean>> playfield;

    CollectionModel(int row, int num) {
	this.row = row;
	this.num = num;
	this.playfield = new ArrayList<ArrayList<Boolean>>();

	// Set size of two-dimensional arraylist and fill it with "dead" cells
	for (int i = 0; i < row; i++) {
	    playfield.add(new ArrayList<Boolean>());
	    for (int j = 0; j < num; j++) {
		playfield.get(i).add(false);
	    }
	}

    }

    private void updateGeneration() {
	// store current generation, so the whole playfield can be updated at once
	ArrayList<ArrayList<Boolean>> newGeneration = new ArrayList<ArrayList<Boolean>>();
	for (int i = 0; i < row; i++) {
	    newGeneration.add(new ArrayList<Boolean>());
	    for (int j = 0; j < num; j++) {
		int neighbors = getNeighbors(i, j);
		if (neighbors==3 && playfield.get(i).get(j)==false) {
		    newGeneration.get(i).add(true);
		} else if ((neighbors<2 || neighbors>3) && playfield.get(i).get(j)==true) {
		    newGeneration.get(i).add(false);
		} else {
		    newGeneration.get(i).add(playfield.get(i).get(j));
		}
	    }
	}
	playfield = newGeneration;
    }

    private int getNeighbors(int row, int num) {
	int neighbours = 0;
	// count how many of the neighbors of a given cell are alive, start top left then clockwise
	try {
	    if (playfield.get(row - 1).get(num - 1)) {
		neighbours++;
	    }
	} catch (IndexOutOfBoundsException e) {
	    // if the index is out of bounds, assume the cell is dead
	}
	try {
	    if (playfield.get(row - 1).get(num)) {
		neighbours++;
	    }
	} catch (IndexOutOfBoundsException e) {
	    // if the index is out of bounds, assume the cell is dead
	}
	try {
	    if (playfield.get(row - 1).get(num + 1)) {
		neighbours++;
	    }
	} catch (IndexOutOfBoundsException e) {
	    // if the index is out of bounds, assume the cell is dead
	}
	try {
	    if (playfield.get(row).get(num + 1)) {
		neighbours++;
	    }
	} catch (IndexOutOfBoundsException e) {
	    // if the index is out of bounds, assume the cell is dead
	}
	try {
	    if (playfield.get(row + 1).get(num + 1)) {
		neighbours++;
	    }
	} catch (IndexOutOfBoundsException e) {
	    // if the index is out of bounds, assume the cell is dead
	}
	try {
	    if (playfield.get(row + 1).get(num)) {
		neighbours++;
	    }
	} catch (IndexOutOfBoundsException e) {
	    // if the index is out of bounds, assume the cell is dead
	}
	try {
	    if (playfield.get(row + 1).get(num - 1)) {
		neighbours++;
	    }
	} catch (IndexOutOfBoundsException e) {
	    // if the index is out of bounds, assume the cell is dead
	}
	try {
	    if (playfield.get(row).get(num - 1)) {
		neighbours++;
	    }
	} catch (IndexOutOfBoundsException e) {
	    // if the index is out of bounds, assume the cell is dead
	}
	return neighbours;
    }
    
    protected ArrayList<ArrayList<Boolean>> getNextGeneration() {
	updateGeneration();
	return playfield;
    }

    protected void setOneState(int row, int num) {
	if (playfield.get(row).get(num)) {
	    playfield.get(row).set(num, false);
	} else {
	    playfield.get(row).set(num, true);
	}
    }

    protected void reset() {
	for (int i = 0; i < row; i++) {
	    for (int j = 0; j < num; j++) {
		playfield.get(i).set(j, false);
	    }
	}
    }

    protected ArrayList<ArrayList<Boolean>> generateRandomConfiguration() {
	for (int i = 0; i < row; i++) {
	    for (int j = 0; j < num; j++) {
		if (Math.random() < 0.5) {
		    playfield.get(i).set(j, true);
		} else {
		    playfield.get(i).set(j, false);
		}
	    }
	}
	return playfield;
    }
    
    
    protected String getPlayfield() {
	String playfield = "";
	for (ArrayList<Boolean> row : this.playfield) {
	    for (Boolean cell : row) {
		playfield += cell.toString() + " ";
	    }
	    playfield += "\n";
	}
	return playfield;
    }
    
    protected ArrayList<ArrayList<Boolean>> setPlayfield(ArrayList<String> playfield) {
	int rowNumber = playfield.size();
	for (int i = 0; i<rowNumber; i++) {
	    String currentRow = playfield.get(i);
	    Scanner innerScanner = new Scanner(currentRow);
	    innerScanner.useDelimiter(" ");
	    int currentNum = 0;
	    while (innerScanner.hasNextBoolean()) {
		this.playfield.get(i).set(currentNum, innerScanner.nextBoolean());
		currentNum++;
	    }
	}
	return this.playfield;
    }
}
