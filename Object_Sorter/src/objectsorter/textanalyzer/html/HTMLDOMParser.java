package objectsorter.textanalyzer.html;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import objectsorter.structure.ElementListQoL;
import objectsorter.structure.ElementObject;
import objectsorter.textanalyzer.TextObject;

public class HTMLDOMParser {
	// character from (char)(-1)
	public static final char INVALIDCHARACTER = (char)Integer.MAX_VALUE;
	private String rootDirectory;
	private ElementObject dom;
	private BufferedReader reader;
	private StringBuilder buffer;
	private char currentChar;
	
	public HTMLDOMParser() {
		this.rootDirectory = "";
		this.dom = new ElementObject();
	}
	
	public HTMLDOMParser(String rootDirectory) {
		this.rootDirectory=rootDirectory;
		this.dom = new ElementObject();
	}
	
	public ElementObject analyzeHtml(String fileName) {
		buffer = new StringBuilder(); currentChar='\0';
		try {
			reader = new BufferedReader(new FileReader(rootDirectory+"\\"+fileName));
			this.dom = new ElementObject(fileName);
			while ((currentChar = (char) reader.read()) != INVALIDCHARACTER) {
				buffer.append(currentChar);
				if(currentChar=='<') {
					analyzeHtmlRecursive(dom);
				}
			}
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dom;
	}
	
	public boolean analyzeHtmlRecursive(ElementObject currentElement) throws IOException {
		//Potential Tag Found
		//Need to traverse until a space is found
		while ((currentChar = (char) reader.read()) != INVALIDCHARACTER && 
				consolidateWhiteSpace(currentChar)!=' ') {
			buffer.append(currentChar);
			if(currentChar=='>')break;
		}
		// Space has been found
		// Make Tag
		HtmlTag tag = new HtmlTag(buffer.toString());
		ElementObject newElement = new ElementObject(tag.getTagName());
		if(!tag.hasEndCarrot()) {
			// Since no End Carrot, then there is attributes
			// Clear Buffer
			buffer.setLength(0);
			while ((currentChar = (char) reader.read()) != INVALIDCHARACTER && 
					currentChar!='>') {
				buffer.append(currentChar);
			}
			String[] attributeList = buffer.toString().trim().split("\'");
			for(int i = 0,j=1;i<attributeList.length&&j<attributeList.length;) {
				String attributeStringName = attributeList[i].trim(),
						attributeStringInfo =  attributeList[j].trim();
				attributeStringName=attributeStringName.substring(0,attributeStringName.length()-1);
				newElement.addComponent(attributeStringName, attributeStringInfo, ElementListQoL.DUPLICATENAMEUNSORTED);
				i+=2;
				j+=2;
			}
		}
		buffer.setLength(0);
		
		if(!tag.hasSlash()) {
			return false;
		}else {
			currentElement.addObject(newElement, ElementListQoL.DUPLICATENAMEUNSORTED);
			if(tag.isSingleton())return true;
		}
		
		// Now time for Text/CDATA
		if(tag.hasCDATA()) {
			String cDataString = null;
			while ((currentChar = (char) reader.read()) != INVALIDCHARACTER) {
				buffer.append(currentChar);
				
				if(currentChar=='>') {
					if(tag.isCommentTag() && buffer.indexOf("-->")!=-1) {
						cDataString=buffer.substring(0, buffer.length()-"-->".length());
						break;
					}else if(tag.isScriptTag() && buffer.indexOf("</script>")!=-1) {
						cDataString=buffer.substring(0, buffer.length()-"</script>".length());
						break;
					}
				}
			}
			if(cDataString.length()!=0)
				newElement.addComponent("cdata",cDataString, ElementListQoL.DUPLICATENAMEUNSORTED);
			buffer.setLength(0);
		}else {
			while ((currentChar = (char) reader.read()) != INVALIDCHARACTER) {
				buffer.append(currentChar);
				if(currentChar=='<') {
					String text=buffer.toString().substring(0, buffer.length()-"<".length()).trim();
					if(text.length()!=0)
						newElement.addComponent("text",text, ElementListQoL.DUPLICATENAMEUNSORTED);
					buffer.setLength(0);
					buffer.append(currentChar);
					if(!analyzeHtmlRecursive(currentElement.getLastObject())) {
						return true;
					}
					
				}
			}
			String text=buffer.toString().trim();
			if(text.length()!=0)
				newElement.addComponent("text",text, ElementListQoL.DUPLICATENAMEUNSORTED);
		}
		return true;
	}
	
	public static ElementObject constructTable(ElementObject tableDOM, String componentType ,int columns) {
		ElementObject tableObject = new ElementObject("Table");
		ArrayList<ElementObject> objList = tableDOM.getObjectList();
		ElementObject obj=null;
		ElementObject rowObject=new ElementObject("Row "+tableObject.getObjectList().size());
		String[] dataName = new String[columns];
		for(int i = 0; i<objList.size();i++) {
			if((i-1)%columns==columns-1) {
				tableObject.addObject(rowObject, ElementListQoL.DUPLICATENAMEUNSORTED);
				rowObject = new ElementObject("Row "+tableObject.getObjectList().size());
			}
			obj=objList.get(i);
			if(i/columns==0) {
				dataName[i]=obj.getComponent(componentType).getcomponentInfo().toString();
			}
			
			if(obj.getComponent(componentType)==null||obj.getComponent(componentType).getcomponentInfo()==null) {
				rowObject.addComponent(dataName[i%columns], null , ElementListQoL.DUPLICATENAMEUNSORTED);
			}else {
				rowObject.addComponent(dataName[i%columns], obj.getComponent(componentType).getcomponentInfo() , ElementListQoL.DUPLICATENAMEUNSORTED);
			}
			
		}
		tableObject.addObject(rowObject, ElementListQoL.DUPLICATENAMEUNSORTED);
		rowObject = new ElementObject("Row "+tableObject.getObjectList().size());
		return tableObject;
	}
	
	public static ElementObject constructTable(ArrayList<ElementObject> elementObjectArray, String componentType ,int columns) {
		ElementObject tableDOM = new ElementObject("TableHTMLDOM", elementObjectArray, new ArrayList<>());
		return constructTable(tableDOM, componentType ,columns);
	}
	
	public static char consolidateWhiteSpace(char character) {
		if(character==' '||character=='\r' || character=='\t' || character=='\f'
				||character=='\n') return ' ';
		return character;
	}

	public ElementObject getDom() {
		return dom;
	}

	public void setDom(ElementObject dom) {
		this.dom = dom;
	}
	
	
}
