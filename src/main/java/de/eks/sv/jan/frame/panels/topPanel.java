package de.eks.sv.jan.frame.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.record.PageBreakRecord.Break;

public class topPanel extends JPanel {
	private Container container = new Container(); 
	public DefaultTableModel model = new DefaultTableModel(new String[][] {}, new String[] {"FileNames"}) {
		 @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
	}; 
	public JTable table = new JTable(model); 
	public topPanel() {
		
		this.setLayout(new BorderLayout());
		JButton getFiles = new JButton("Datei Öffnen");
		JButton berechne = new JButton("Convert"); 
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setApproveButtonText("Datei aussuchen");
		this.add(BorderLayout.EAST, getFiles);
		getFiles.setBackground(Color.white);
		berechne.setBackground(Color.white);
		berechne.addActionListener(new ConvertActionListener(table));
		getFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rueckgabeWert = fileChooser.showSaveDialog(null); 
				if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
					singleShowData(fileChooser.getSelectedFile());
				}
			}
		});
		container.setLayout(new BorderLayout());
		container.add(BorderLayout.NORTH, new JLabel("File Names"));
		container.add(BorderLayout.CENTER, table); 
		this.add(BorderLayout.CENTER, container);
		this.add(BorderLayout.SOUTH, berechne); 
	}
	
	@SuppressWarnings("unused")
	private void showData(File[] files) {
		System.out.println(files.length);
		for (int i = 0; i < files.length; i++) {
			singleShowData(files[i]);
		}
	}
	private void singleShowData(File file) {
		if(!file.getName().endsWith(".xlsx")) {
			JOptionPane.showMessageDialog(new JFrame(), "Bitte wählen sie nur Dateien mit einem .xlsx (Excel) format aus!", "Fehler beim Datei Format", JOptionPane.WARNING_MESSAGE);
			return; 
		}
		model.addRow(new Object[] {file.getAbsolutePath()});
		this.repaint();
		SwingUtilities.updateComponentTreeUI(this);
	}
}
