package ch.javaCourse.gameOfLife.menuFunctionality;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ResizePlayfieldView extends JDialog {

    private static final long serialVersionUID = 100L;

    JButton updateButton;
    JTextField inputRowField, inputNumField;
    JTextArea description;

    public ResizePlayfieldView() {
	this.setLayout(new FlowLayout());
	this.setBounds(200, 200, 370, 110);
	updateButton = new JButton("Update");
	inputRowField = new JTextField(7);
	inputNumField = new JTextField(7);
	description = new JTextArea();

	initForm();

    }

    private void initForm() {

	this.setTitle("Size of the playfield");
	description
		.setText("please enter a new size (rows/colons) for the playfield:");
	description.setOpaque(false);
	description.setEditable(false);
	
	this.add(description);
	this.add(inputRowField);
	this.add(inputNumField);
	this.add(updateButton);
    }

    public int getInputRow() {
	String input = inputRowField.getText();
	return Integer.parseInt(input);

    }
    
    public int getInputNum() {
	String input = inputRowField.getText();
	return Integer.parseInt(input);

    }
    
    public void setInputRowTextColor(Color color) {
	inputRowField.setForeground(color);
    }
    
    public void setInputNumTextColor(Color color) {
	inputNumField.setForeground(color);
    }

    public void setUpdateResizeButtonListener(ActionListener l) {
	updateButton.addActionListener(l);
    }
}

