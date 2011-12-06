package ch.javaCourse.gameOfLife.menuFunctionality;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UpdateTimeView extends JDialog {

    private static final long serialVersionUID = 100L;

    JButton updateButton;
    JTextField inputField;
    JTextArea description;

    public UpdateTimeView() {
	this.setLayout(new FlowLayout());
	this.setBounds(200, 200, 230, 110);
	updateButton = new JButton("Update");
	inputField = new JTextField(7);
	description = new JTextArea();

	initForm();

    }

    private void initForm() {

	this.setTitle("Time between generations");
	description
		.setText("please enter a new delay between\n the generations in miliseconds:");
	description.setOpaque(false);
	description.setEditable(false);
	
	this.add(description);
	this.add(inputField);
	this.add(updateButton);
    }

    public int getInputTime() {
	String input = inputField.getText();
	return Integer.parseInt(input);

    }
    
    public void setInputTextColor(Color color) {
	inputField.setForeground(color);
    }

    public void setUpdateButtonListener(ActionListener l) {
	updateButton.addActionListener(l);
    }
}
