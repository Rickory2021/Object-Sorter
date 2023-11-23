package objectsorter.textanalyzer.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import objectsorter.structure.ElementListQoL;
import objectsorter.structure.ElementObject;
import objectsorter.textanalyzer.html.HtmlTag;

public class CsvTableParser {
	// character from (char)(-1)
		public static final char INVALIDCHARACTER = (char)Integer.MAX_VALUE;
		private String rootDirectory;
		private ElementObject table;
		private BufferedReader reader;
		private String buffer;
		private char currentChar;
		
		public CsvTableParser() {
			this.rootDirectory = "";
			this.table = new ElementObject();
		}
		
		public CsvTableParser(String rootDirectory) {
			this.rootDirectory=rootDirectory;
			this.table = new ElementObject();
		}
		
		public ElementObject analyzeCsv(String fileName) {
			this.table = new ElementObject(fileName);
			buffer = "";
			try {
				reader = new BufferedReader(new FileReader(rootDirectory+"\\"+fileName));
				this.table = new ElementObject(fileName);
				boolean first = true;
				String[] dataName=null;
				while ((buffer = reader.readLine()) != null) {
					String[] bufferArray = buffer.trim().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
					if(first) {
						dataName = bufferArray;
						first=false;
					}
					ElementObject rowObject=new ElementObject("Row "+table.getObjectList().size());
					for(int i = 0;i<bufferArray.length;i++) {
						rowObject.addComponent(dataName[i], bufferArray[i], ElementListQoL.DUPLICATENAMEUNSORTED);
					}
					//System.out.println(rowObject);
					table.addObject(rowObject, ElementListQoL.DUPLICATENAMEUNSORTED);
					//System.out.println(table);
					
				}
				reader.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return table;
		}
		
		public ElementObject getTable() {
			return table;
		}
}
