package de.eks.sv.jan.objekts;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;

public class LoadedUmfrage {
	private String fileName; 
	private List<Row> fragen; 
	private HashMap<Integer, Row> antworten; 
	public LoadedUmfrage(String fileName, List<Row> fragen, HashMap<Integer, Row> antworten) {
		this.fileName = fileName; 
		this.fragen = fragen; 
		this.antworten = antworten;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<Row> getFragen() {
		return fragen;
	}
	public void setFragen(List<Row> fragen) {
		this.fragen = fragen;
	}
	public HashMap<Integer, Row> getAntworten() {
		return antworten;
	}
	public void setAntworten(HashMap<Integer, Row> antworten) {
		this.antworten = antworten;
	}
}
