package de.eks.sv.jan;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.math3.ode.FirstOrderConverter;
import org.apache.commons.math3.stat.descriptive.SynchronizedDescriptiveStatistics;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import de.eks.sv.jan.objekts.LoadedUmfrage;

public class ConvertUmfragen {
	public static void MergeUmfrage(List<LoadedUmfrage> umfragen) { 
		LoadedUmfrage firstUmfrage = umfragen.get(0); 
		for (int i = 1; i < umfragen.size(); i++) {
			boolean fragenEqal = true; 
			LoadedUmfrage secendUmfrage = umfragen.get(i); 
			System.out.println("Fragen Index eqal: " + (firstUmfrage.getFragen().get(0).getLastCellNum() == secendUmfrage.getFragen().get(0).getLastCellNum()));
			System.out.println("Firstumfrage Size: " + firstUmfrage.getFragen().get(0).getLastCellNum());
			System.out.println("Secendumfrage Size: " + secendUmfrage.getFragen().get(0).getLastCellNum());
			if(firstUmfrage.getFragen().get(0).getLastCellNum() == secendUmfrage.getFragen().get(0).getLastCellNum()) {
				// Checking fragen Eqal 
				for (int j = 0; j < firstUmfrage.getFragen().get(0).getLastCellNum(); j++) { // Geht alle Fragen durch
					Cell firstCell = firstUmfrage.getFragen().get(0).getCell(j); // Zelle in der Row
					String firstContent = firstCell.getStringCellValue(); // Content Zelle 
					for (int k = 0; k < secendUmfrage.getFragen().get(0).getLastCellNum(); k++) { // Geht alle Fragen von der Zweiten Umfragen durch
						Cell secendCell = secendUmfrage.getFragen().get(0).getCell(k);// Zelle in der Row
						String secendContent = secendCell.getStringCellValue(); // Content Zelle
						System.out.println("FirstConetent: " + firstContent);
						System.out.println("SecendContent: " + secendContent);
						if(firstContent.equalsIgnoreCase(secendContent) && firstCell.getColumnIndex() != secendCell.getColumnIndex()) { // Selbe Frage nicht selbe zeile
							fragenEqal = false; 
							System.out.println("Nicht die Selben Fragen sind in der selben Zeile!");
						} 
					}
				}
			} else {
				fragenEqal = false; 
			}
			System.out.println("--" + fragenEqal + "--");
			// Adding Row to Main Umfrage 
			if(fragenEqal) {
				int nextIndex = firstUmfrage.getAntworten().keySet().size();
				System.out.println("Nächster Index:" + nextIndex);
				for (int j = 1; j <= secendUmfrage.getAntworten().size()+1; j++) {
					nextIndex +=1; 
					firstUmfrage.getAntworten().put(nextIndex, secendUmfrage.getAntworten().get(j)); // ADDING ROW
				}
			} else {
				// equalise umfragen 
				for (int j = 0; j < firstUmfrage.getFragen().get(0).getLastCellNum(); j++) {
					Cell firstCell = firstUmfrage.getFragen().get(0).getCell(j); 
					for (int k = 0; k < secendUmfrage.getFragen().get(0).getLastCellNum(); k++) {
						Cell secendCell = secendUmfrage.getFragen().get(0).getCell(k); 
						if(firstCell.getStringCellValue().equalsIgnoreCase(secendCell.getStringCellValue())) { // Eqal Question
							if(firstCell.getColumnIndex() != secendCell.getColumnIndex()) { // Not the Same Colum Number 
								Cell secendSwitchCell = secendUmfrage.getFragen().get(0).getCell(j); 
								String oldSwitchCellValue = secendSwitchCell.getStringCellValue(); 
								secendSwitchCell.setCellValue(secendCell.getStringCellValue());
								secendCell.setCellValue(oldSwitchCellValue);
								HashMap<Integer, Row> secendAntworten = secendUmfrage.getAntworten();
								for (int l = 0; l < secendAntworten.keySet().size(); l++) {
									Row secendRow = secendAntworten.get(l); 
									Cell secendValueCell = secendRow.getCell(k); 
									Cell secendSwitchValueCell = secendRow.getCell(j); 
									String oldSwitchValueCellValue = secendSwitchValueCell.getStringCellValue(); 
									secendSwitchValueCell.setCellValue(secendValueCell.getStringCellValue());
									secendValueCell.setCellValue(oldSwitchValueCellValue);
								}
								int nextIndex = firstUmfrage.getAntworten().size();
								for (int y = 0; y < secendUmfrage.getAntworten().size(); y++) {
									nextIndex +=1; 
									firstUmfrage.getAntworten().put(nextIndex, secendUmfrage.getAntworten().get(y)); // ADDING ROW
								}
							}
						}
					}
				}
			}
		}
		
		LoadFile.SysoUmfrage(firstUmfrage);
	}
	
	
}
