package ch.javaCourse.gameOfLife;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

class ViewMenuBar extends JMenuBar {

    private static final long serialVersionUID = 100L;

    JMenu gameOfLifeMenu, submenu;
    JMenuItem timeMenuItem, sizeMenuItem, saveMenuItem, loadMenuItem;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;

    public ViewMenuBar() {
	this.setBackground(Color.LIGHT_GRAY);

	populateMenuBar();
    }

    private void populateMenuBar() {
	// Build the first menu.
	gameOfLifeMenu = new JMenu("Game of Life");
	gameOfLifeMenu.setBackground(Color.LIGHT_GRAY);
	gameOfLifeMenu.setMnemonic(KeyEvent.VK_A);
	gameOfLifeMenu.getAccessibleContext().setAccessibleDescription(
		"Ome more options to change");
	this.add(gameOfLifeMenu);

	// a group of JMenuItems
	timeMenuItem = new JMenuItem("Set Time between generations", KeyEvent.VK_T);
	timeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
		ActionEvent.ALT_MASK));
	timeMenuItem.getAccessibleContext()
		.setAccessibleDescription(
			"Opens a dialog to change the time between the single generations, default time is 250 ms");

	sizeMenuItem = new JMenuItem("Resize playfield", KeyEvent.VK_T);
	sizeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
		ActionEvent.ALT_MASK));
	sizeMenuItem.getAccessibleContext()
		.setAccessibleDescription(
			"Opens a dialog to resize the playfield, default is a quadratic playfield of 25 times 25 cells");
	
	saveMenuItem = new JMenuItem("Save current Playfield", KeyEvent.VK_T);
	saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
		ActionEvent.ALT_MASK));
	saveMenuItem.getAccessibleContext()
		.setAccessibleDescription(
			"Opens a dialog to save the current playfield into a file");
	
	loadMenuItem = new JMenuItem("Load Playfield", KeyEvent.VK_T);
	loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
		ActionEvent.ALT_MASK));
	loadMenuItem.getAccessibleContext()
		.setAccessibleDescription(
			"Opens a dialog to load a playfield configuration");

	gameOfLifeMenu.add(timeMenuItem);
	gameOfLifeMenu.add(sizeMenuItem);
	
	gameOfLifeMenu.addSeparator();
	
	gameOfLifeMenu.add(saveMenuItem);
	gameOfLifeMenu.add(loadMenuItem);

    }

    /*
     * Getter for the menu Item changing the time between generations
     * @return the menuItem object 
     */
    public JMenuItem getTimeBetweengenerationsMenuItem() {
	return timeMenuItem;
    }
   
    /*
     * Getter for the menu Item changing the size of the playfield
     * @return the menuItem object 
     */
    public JMenuItem getResizePlayfieldMenuItem() {
	return sizeMenuItem;
    }
    
    /*
     * Getter for the menu Item opening a save-dialog
     * @return the menuItem object 
     */
    public JMenuItem getSavePlayfieldMenuItem() {
	return saveMenuItem;
    }
    
    /*
     * Getter for the menu Item opening a load-dialog
     * @return the menuItem object 
     */
    public JMenuItem getLoadPlayfieldMenuItem() {
	return loadMenuItem;
    }
}
