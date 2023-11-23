package objectsorter.textanalyzer;

import java.io.BufferedReader;
import java.util.ArrayList;

import objectsorter.structure.ElementComponent;

public class TextComponent {
	private ElementComponent component;
	private ArrayList<String> incitingIdentifierList, endingIdentifierList;
	public ElementComponent getComponent() {
		return component;
	}
	public void setComponent(ElementComponent component) {
		this.component = component;
	}
	public ArrayList<String> getIncitingIdentifierList() {
		return incitingIdentifierList;
	}
	public void setIncitingIdentifierList(ArrayList<String> incitingIdentifierList) {
		this.incitingIdentifierList = incitingIdentifierList;
	}
	
	public String analyzeText(String currentLine, BufferedReader reader) {
		for(String incitingIncident: incitingIdentifierList) {
			if(currentLine.contains(incitingIncident)) {
				
			}
		}
		return currentLine;
	}
}
