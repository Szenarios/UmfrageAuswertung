package de.eks.sv.jan.frame.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import de.eks.sv.jan.ConvertUmfragen;
import de.eks.sv.jan.LoadFile;
import de.eks.sv.jan.objekts.LoadedUmfrage;

public class ConvertActionListener implements ActionListener {
	JTable table;  
	public ConvertActionListener(JTable table) { 
		this.table = table; 
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println(table.getRowCount());
		List<LoadedUmfrage> list = new ArrayList<LoadedUmfrage>(); 
		for (int i = 0; i < table.getRowCount(); i++) {
			String fileName = (String) table.getValueAt(i, 0); 
			File file = new File(fileName);
			list.add(LoadFile.get(file)); 
			System.out.println(file.getAbsolutePath());
		}
		ConvertUmfragen.MergeUmfrage(list);
	}
}
