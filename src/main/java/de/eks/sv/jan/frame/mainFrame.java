package de.eks.sv.jan.frame;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.eks.sv.jan.frame.panels.menuPanel;

public class mainFrame extends JFrame {
	public mainFrame() {
		menuPanel panel = new menuPanel(); 
		this.add(panel); 
		this.setLocation(0, 0);
		this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}