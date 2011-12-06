package ch.javaCourse.gameOfLife.menuFunctionality;

import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

public class SaveCurrentPlayfieldView extends JFileChooser {

    private static final long serialVersionUID = 100L;


    public SaveCurrentPlayfieldView() {

	initForm();

    }

    private void initForm() {
	setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }


    public void setSaveConfigButtonListener(ActionListener l) {
	this.addActionListener(l);
    }
}


