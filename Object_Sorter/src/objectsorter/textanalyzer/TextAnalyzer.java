package objectsorter.textanalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import objectsorter.structure.ElementObject;

public class TextAnalyzer {
	public static String rootDirectory = "";
	
	private ArrayList<ElementObject> elementObjectList;
	private ArrayList<TextObject> TextOjectList;
	
	public String analyzeText(String fileName) {
		StringBuilder output = new StringBuilder();
		int index = 0;String currentLine = "NULL";
		try {
			//BufferedReader reader = new BufferedReader(new FileReader("Database\\Billboard.com\\HTML\\2011\\Billboard Japan Hot 100 � Billboard 2011-04-09.html"));
			BufferedReader reader = new BufferedReader(new FileReader(rootDirectory+"\\"+fileName));
			while(currentLine!=null) {
				for(TextObject textObject:TextOjectList) {
					textObject.analyzeText(currentLine, reader);
				}
			}
		}catch(Exception e) {
			output.append("ERROR MESSAGE Line: "+index+"\t"+e.getMessage()+"\n"+currentLine);
		}
		return output.toString();
	}
}
