package objectsorter.textanalyzer;

import java.io.BufferedReader;
import java.util.ArrayList;

import objectsorter.structure.ElementComponent;
import objectsorter.structure.ElementObject;

public class TextObject {
	private ElementObject object;
	private ArrayList<TextComponent> textComponentList;
	private  ArrayList<String> incitingIdentifierList, endingIdentifierList;
	private boolean loopable;
	public ElementObject getObject() {
		return object;
	}
	public void setObject(ElementObject object) {
		this.object = object;
	}
	public ArrayList<String> getIncitingIdentifierList() {
		return incitingIdentifierList;
	}
	public void setIncitingIdentifierList(ArrayList<String> incitingIdentifierList) {
		this.incitingIdentifierList = incitingIdentifierList;
	}
	public ArrayList<String> getEndingIdentifierList() {
		return endingIdentifierList;
	}
	public void setEndingIdentifierList(ArrayList<String> endingIdentifierList) {
		this.endingIdentifierList = endingIdentifierList;
	}
	public boolean isLoopable() {
		return loopable;
	}
	public void setLoopable(boolean loopable) {
		this.loopable = loopable;
	}
	
	public String analyzeText(String currentLine, BufferedReader reader) {
		for(String incitingIncident: incitingIdentifierList) {
			if(currentLine.contains(incitingIncident)) {
				// We have found the incitingIndetifier for Hosting the Object
				currentLine = currentLine.substring(currentLine.indexOf(incitingIncident), 0);
			}
		}
		return currentLine;
	}
}
