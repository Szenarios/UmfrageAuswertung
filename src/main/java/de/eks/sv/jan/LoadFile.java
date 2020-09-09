package de.eks.sv.jan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.eks.sv.jan.objekts.LoadedUmfrage;

public class LoadFile {
	public static LoadedUmfrage get(File file) {
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			
			// List for Umfrage 
			List<Row> fragen = new ArrayList<Row>(); 
			HashMap<Integer, Row> antworten = new HashMap<Integer, Row>(); 
			
			Sheet sheet = workbook.getSheetAt(0);
			for(Row row : sheet) {
				if(row.getRowNum() == 0) {
					fragen.add(row); 
				} else {
					antworten.put(row.getRowNum(), row); 
				}
			}
			workbook.close();
			fileInputStream.close();
		return new LoadedUmfrage(file.getName(), fragen, antworten);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	public static void LoadUmfrageInToFil(LoadedUmfrage umfrage) {
		// TODO 
	}
	public static void SysoUmfrage(LoadedUmfrage umfrage) {
		System.out.println("Fragen:");
		for (int i = 0; i < umfrage.getFragen().get(0).getLastCellNum(); i++) {
			System.out.println(umfrage.getFragen().get(0).getCell(i).getStringCellValue());
		}
		System.out.println("Antworten:");
		for (int i = 1; i < umfrage.getAntworten().keySet().size(); i++) {
			Row row = umfrage.getAntworten().get(i);
			String out = "Row " + i +": "; 
			for (int j = 0; j < row.getLastCellNum(); j++) {
				out = out + row.getCell(j); 
			}
			System.out.println(out);
		}
	}
	
}