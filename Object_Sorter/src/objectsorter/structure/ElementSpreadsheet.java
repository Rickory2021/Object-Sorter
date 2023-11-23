package objectsorter.structure;

import java.io.Serializable;
import java.util.ArrayList;

public class ElementSpreadsheet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4194185812483536791L;
	private String spreadsheetName;
	private ArrayList<ElementObject> objectList;
	private ArrayList<ElementSorter> sorterList;
	
	public String getSpreadsheetName() {
		return spreadsheetName;
	}
	public void setSpreadsheetName(String spreadsheetName) {
		this.spreadsheetName = spreadsheetName;
	}
	
	public ArrayList<ElementObject> getObjectList() {
		return objectList;
	}
	
	public boolean addObject(ElementObject object ,int addType) {
		return ElementListQoL.addElement(object, this.objectList, addType);
	}
	
	public boolean removeObject(String objectName) {
		return ElementListQoL.removeElement(new ElementObject(objectName), this.objectList);
	}
	
	public void setObjectList(ArrayList<ElementObject> objectList) {
		this.objectList = objectList;
	}
	public ArrayList<ElementSorter> getSorterList() {
		return sorterList;
	}
	
	public boolean addSorter(ElementSorter object ,int addType) {
		return ElementListQoL.addElement(object, this.sorterList, addType);
	}
	
	public boolean removeSorter(String sorterName) {
		return ElementListQoL.removeElement(new ElementSorter(sorterName), this.sorterList);
	}
	
	public void setSorterList(ArrayList<ElementSorter> sorterList) {
		this.sorterList = sorterList;
	}

	
}
