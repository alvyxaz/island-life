package com.friendlyblob.islandlife.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI extends JFrame{

	public GUI(ActionListener listener) {
		setTitle("Simple example");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		this.setVisible(true);
		
		JButton button = new JButton("On");
		button.addActionListener(listener);
		
		this.getContentPane().add(button);
	}
	
	
}
