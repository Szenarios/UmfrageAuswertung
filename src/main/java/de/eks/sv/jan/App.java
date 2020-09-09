package de.eks.sv.jan;

import javax.swing.JFrame;

import de.eks.sv.jan.frame.AutoUpdater;
import de.eks.sv.jan.frame.mainFrame;

public class App {
    public static void main( String[] args ) {
    	JFrame frame = new mainFrame(); 
    	AutoUpdater autoUpdater = new AutoUpdater(frame); 
    	autoUpdater.start();
    }
}
