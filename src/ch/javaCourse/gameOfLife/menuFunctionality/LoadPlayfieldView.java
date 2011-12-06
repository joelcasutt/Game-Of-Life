package ch.javaCourse.gameOfLife.menuFunctionality;

import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

public class LoadPlayfieldView extends JFileChooser {

    private static final long serialVersionUID = 100L;


    public LoadPlayfieldView() {

	initForm();

    }

    private void initForm() {
	setFileSelectionMode(JFileChooser.FILES_ONLY);
    }


    public void setLoadConfigButtonListener(ActionListener l) {
	this.addActionListener(l);
    }
}