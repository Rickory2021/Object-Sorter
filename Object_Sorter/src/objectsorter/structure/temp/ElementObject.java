package objectsorter.structure.temp;
import java.util.ArrayList;
import java.util.Collections;

import objectsorter.structure.temp.comparator.ElementObjectComparator;

public class ElementObject extends Element{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4407672074280243099L;
	private ElementEnum.StructureType structType;
	// The Tree Structure
	private ArrayList<ElementObject> objectList;
	private ElementObjectComparator<ElementObject> activeObjectComparator;
	
	// The Data of the Nodes
	private ElementComponentList<Integer> integerList;
	private ElementComponentList<Double> doubleList;
	private ElementComponentList<String> stringList;
	private ElementComponentList<Object> unknownList;
	
	public ElementObject() {
		super();
		this.structType = ElementEnum.StructureType.DUPLICATE;
		this.objectList = new ArrayList<ElementObject>();
		this.activeObjectComparator=new ElementObjectComparator<ElementObject>();
		
		this.integerList = new ElementComponentList<Integer>();
		this.doubleList = new ElementComponentList<Double>();
		this.stringList = new ElementComponentList<String>();
		this.unknownList = new ElementComponentList<>();
	}
	
	public ElementObject(String elementName) {
		super(elementName);
		this.objectList=new ArrayList<ElementObject>();
		this.activeObjectComparator=new ElementObjectComparator<ElementObject>();
		
		this.integerList = new ElementComponentList<Integer>();
		this.doubleList = new ElementComponentList<Double>();
		this.stringList = new ElementComponentList<String>();
		this.unknownList = new ElementComponentList<>();
	}
	
	public ElementEnum.StructureType getStructType() {
		return structType;
	}

	public void setStructType(ElementEnum.StructureType structType) {
		this.structType = structType;
	}
	
	public ElementObjectComparator<ElementObject> getActiveComponentComparator() {
		return activeObjectComparator;
	}

	public ArrayList<ElementObject> getObjectList() {
		return objectList;
	}

	public void setObjectList(ArrayList<ElementObject> objectList, boolean updateList) {
		this.objectList = objectList;
		if(updateList && activeObjectComparator != null) {
			Collections.sort(this.objectList, activeObjectComparator);
		}
		super.updateTime();
	}
	
	public void updateList() {
		if(activeObjectComparator != null) {
			Collections.sort(this.objectList, activeObjectComparator);
			super.updateTime();
		}
	}

	public ElementObjectComparator<ElementObject> getActiveObjectComparator() {
		return activeObjectComparator;
	}

	public void setActiveObjectComparator(ElementObjectComparator<ElementObject> activeObjectComparator) {
		this.activeObjectComparator = activeObjectComparator;
	}

	public ElementComponentList<Integer> getIntegerList() {
		return integerList;
	}

	public void setIntegerList(ElementComponentList<Integer> integerList) {
		this.integerList = integerList;
	}

	public ElementComponentList<Double> getDoubleList() {
		return doubleList;
	}

	public void setDoubleList(ElementComponentList<Double> doubleList) {
		this.doubleList = doubleList;
	}

	public ElementComponentList<String> getStringList() {
		return stringList;
	}

	public void setStringList(ElementComponentList<String> stringList) {
		this.stringList = stringList;
	}


	public ElementComponentList<Object> getUnknownList() {
		return unknownList;
	}


	public void setUnknownList(ElementComponentList<Object> unknownList) {
		this.unknownList = unknownList;
	}
	
	@Override
	public String toStringExtensive() {
		return null;
	}
}
