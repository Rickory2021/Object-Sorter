package objectsorter.structure.temp;

import java.io.Serializable;
import java.util.ArrayList;

import objectsorter.structure.temp.comparator.ElementComparator;

public class ElementObject extends Element implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1422025453615546985L;

	private ElementList<ElementComponent<Integer>> integerComponentList;
	private ElementList<ElementComponent<Double>> doubleComponentList;
	private ElementList<ElementComponent<String>> stringComponentList;
	private ElementList<ElementComponent<ArrayList<?>>> arrayListComponentList;
	private ElementList<ElementComponent<Object>> objectComponentList;
	
	private ElementList<ElementObject> objectList;
	
	private ArrayList<ElementComparator> comparatorList;
	
	public ElementObject() {
		super();
		constructArrayList();
	}
	
	public ElementObject(String elementName) {
		super(elementName);
		constructArrayList();
	}
	
	public void constructArrayList() {
		this.integerComponentList = new ElementList<ElementComponent<Integer>>();
		this.doubleComponentList = new ElementList<ElementComponent<Double>>();
		this.stringComponentList = new ElementList<ElementComponent<String>>();
		this.arrayListComponentList = new ElementList<ElementComponent<ArrayList<?>>>();
		this.objectComponentList = new ElementList<ElementComponent<Object>>();
		
		this.objectList = new ElementList<ElementObject>();
		
		this.comparatorList = new ArrayList<ElementComparator>();
	}

	public ElementList<ElementComponent<Integer>> getIntegerComponentList() {
		return integerComponentList;
	}

	public void setIntegerComponentList(ElementList<ElementComponent<Integer>> integerComponentList) {
		this.integerComponentList = integerComponentList;
		super.updateTime();
	}

	public ElementList<ElementComponent<Double>> getDoubleComponentList() {
		return doubleComponentList;
	}

	public void setDoubleComponentList(ElementList<ElementComponent<Double>> doubleComponentList) {
		this.doubleComponentList = doubleComponentList;
		super.updateTime();
	}

	public ElementList<ElementComponent<String>> getStringComponentList() {
		return stringComponentList;
	}

	public void setStringComponentList(ElementList<ElementComponent<String>> stringComponentList) {
		this.stringComponentList = stringComponentList;
		super.updateTime();
	}

	public ElementList<ElementComponent<ArrayList<?>>> getArrayListComponentList() {
		return arrayListComponentList;
	}

	public void setArrayListComponentList(ElementList<ElementComponent<ArrayList<?>>> arrayListComponentList) {
		this.arrayListComponentList = arrayListComponentList;
		super.updateTime();
	}

	public ElementList<ElementComponent<Object>> getObjectComponentList() {
		return objectComponentList;
	}

	public void setObjectComponentList(ElementList<ElementComponent<Object>> objectComponentList) {
		this.objectComponentList = objectComponentList;
		super.updateTime();
	}

	public ElementList<ElementObject> getObjectList() {
		return objectList;
	}

	public void setObjectList(ElementList<ElementObject> objectList) {
		this.objectList = objectList;
		super.updateTime();
	}

	public ArrayList<ElementComparator> getComparatorList() {
		return comparatorList;
	}

	public void setComparatorList(ArrayList<ElementComparator> comparatorList) {
		this.comparatorList = comparatorList;
		super.updateTime();
	}
	
	
}
