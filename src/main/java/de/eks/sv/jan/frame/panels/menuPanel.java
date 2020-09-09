package de.eks.sv.jan.frame.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import de.eks.sv.jan.frame.AutoUpdater;

public class menuPanel extends JPanel {
	private topPanel topPanel; 
	public menuPanel() {
		this.setLayout(new BorderLayout());
		this.topPanel = new topPanel(); 
		this.add(BorderLayout.NORTH, topPanel); 	
	}
	public JPanel getTopPanel() {
		return topPanel;
	}
}