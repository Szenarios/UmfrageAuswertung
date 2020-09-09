package de.eks.sv.jan.frame;

import java.awt.Component;

public class AutoUpdater extends Thread {
	private Component panel; 
	private int FPS = 30; 
	public AutoUpdater(Component panel) {
		this.panel = panel; 
	}
	@Override
	public void run() {
		while(true) { 
			while(panel.isVisible()) {
				try {
					panel.repaint(); 
					Thread.sleep(1000 / FPS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
