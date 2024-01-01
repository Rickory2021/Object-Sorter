package objectsorter.structure.temp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import objectsorter.structure.temp.comparator.ElementComponentComparator;

public class ElementComponentList <E> extends Element{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8178598806525382815L;
	private ElementEnum.StructureType structType;
	private ArrayList<ElementComponent<E>> elementList;
	// NOTE THIS IS FILLER (CHANGE CLASS LATER)
	private ElementComponentComparator<ElementComponent<E>> activeComponentComparator;
	
	
	public ElementComponentList() {
		super();
		this.structType = ElementEnum.StructureType.DUPLICATE;
		this.elementList = new ArrayList<ElementComponent<E>>();
		this.activeComponentComparator=new ElementComponentComparator<ElementComponent<E>>();
	}
	
	public ElementComponentList(String elementName) {
		super(elementName);
		this.elementList=new ArrayList<ElementComponent<E>>();
		this.activeComponentComparator=new ElementComponentComparator<ElementComponent<E>>();
	}
	
	public ElementComponentList(String elementName, ElementComponentComparator<ElementComponent<E>> activeComponentComparator) {
		super(elementName);
		this.structType = ElementEnum.StructureType.DUPLICATE;
		this.elementList = new ArrayList<ElementComponent<E>>();
		setActiveComponentComparator(activeComponentComparator,false);
	}
	
	public ElementEnum.StructureType getStructType() {
		return structType;
	}
	
	public void setStructType(ElementEnum.StructureType structType) {
		this.structType=structType;
	}

	public ArrayList<ElementComponent<E>> getElementList() {
		return elementList;
	}
	
	public boolean addData(E data) {
		ElementComponent<E> newElement = new ElementComponent<E>(data);
		int index;
		if(activeComponentComparator == null) {	
			index=elementList.size()-1;
		}else{
			index = Collections.binarySearch(elementList, newElement, activeComponentComparator);
		}
		
		// Check it there is already a duplicate name
		if(elementList.indexOf(newElement)!=-1) {
			// Element Already Exist
			switch(structType) {
			case DUPLICATE:
				// ADD
				if (index<0) {
					elementList.add((-(index) - 1),newElement);
					super.updateTime();
					return true;
				}
				elementList.add(index,newElement);
				super.updateTime();
				return true;
			case UNIQUEFINAL:
				//RETURN FALSE
				return false;
			case UNQIUEOVERRIDE:
				//REPLACE
				if (index<0) {
					elementList.add((-(index) - 1),newElement);
					super.updateTime();
					return true;
				}
				elementList.remove(index);
				elementList.add(index,newElement);
				super.updateTime();
				return true;
			}
		}
		return false;
	}
	
	public boolean addData(String name,E data) {
		ElementComponent<E> newElement = new ElementComponent<E>(name,data);
		int index;
		if(activeComponentComparator == null) {
			index=elementList.size();
		}else{
			index = Collections.binarySearch(elementList, newElement, activeComponentComparator);
		}
		
		// Check it there is already a duplicate name
		if(elementList.indexOf(newElement)!=-1) {
			// Element Already Exist
			switch(structType) {
			case DUPLICATE:
				// ADD
				if (index<0) {
					elementList.add((-(index) - 1),newElement);
					super.updateTime();
					return true;
				}
				elementList.add(index,newElement);
				super.updateTime();
				return true;
			case UNIQUEFINAL:
				//RETURN FALSE
				return false;
			case UNQIUEOVERRIDE:
				//REPLACE
				if (index<0) {
					elementList.add((-(index) - 1),newElement);
					super.updateTime();
					return true;
				}
				elementList.remove(index);
				elementList.add(index,newElement);
				super.updateTime();
				return true;
			}
		}else {
			if (index<0) {
				elementList.add((-(index) - 1),newElement);
				super.updateTime();
				return true;
			}
			elementList.add(index,newElement);
			super.updateTime();
			return true;
		}
		return false;
	}
	
	public boolean addElement(ElementComponent<E> element) {
		if(activeComponentComparator == null) {
			elementList.add(element);
			return true;
		}
		int index = Collections.binarySearch(elementList, element, activeComponentComparator);
		if (index<0) {
			// New Element means you always add 
			elementList.add((-(index) - 1),element);
			super.updateTime();
			return true;
		}
		// This means that the "same" element is in the list, so check how structure is defined
		switch(structType) {
		case DUPLICATE:
			// ADD
			elementList.add(index,element);
			super.updateTime();
			return true;
		case UNIQUEFINAL:
			//RETURN FALSE
			return false;
		case UNQIUEOVERRIDE:
			//REPLACE
			elementList.remove(index);
			elementList.add(index,element);
			super.updateTime();
			return true;
		}
		return false;
	}
	
	public int indexOfNamed(String name) {
		for(int i = 0; i<elementList.size();i++) {
			if(elementList.get(i).getElementName().equals(name))return i;
		}
		return -1;
	}
	
	public ElementComponent<E> getElementObjectNamed(String name){
		for(ElementComponent<E> component: elementList) {
			if(component.getElementName().equals(name))return component;
		}
		return null;
	}
	
	public ArrayList<ElementComponent<E>> getElementObjectNamedList(String name){
		ArrayList<ElementComponent<E>> tempArrayList = new ArrayList<ElementComponent<E>>();
		for(ElementComponent<E> component: elementList) {
			if(component.getElementName().equals(name))tempArrayList.add(component);
		}
		return tempArrayList;
		
	}
	
	//Add
	//Contains
	//Get
	//IndexOf
	//isEmpty
	//Remove
	//RemoveAll
	//Size
	//ToArray

	public void setElementList(ArrayList<ElementComponent<E>> elementList, boolean updateList) {
		this.elementList = elementList;
		if(updateList && activeComponentComparator != null) {
			Collections.sort(this.elementList, activeComponentComparator);
		}
		super.updateTime();
	}
	
	public ElementComponentComparator<ElementComponent<E>> getActiveComponentComparator() {
		return activeComponentComparator;
	}

	public void setActiveComponentComparator(ElementComponentComparator<ElementComponent<E>> activeComponentComparator, boolean updateList) {
		ElementComponentComparator.swapPointerFromElementComponentList(this,activeComponentComparator);
		this.activeComponentComparator = activeComponentComparator;
		if(updateList && activeComponentComparator != null) {
			Collections.sort(this.elementList, activeComponentComparator);
		}
		super.updateTime();
	}
	
	public void updateList() {
		if(activeComponentComparator != null) {
			Collections.sort(this.elementList, activeComponentComparator);
			super.updateTime();
		}
		
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ElementComponent Name: "+super.toString());
		stringBuilder.append("List:\n");
		for(int i = 0; i<elementList.size();i++) {
			//stringBuilder.append("\t"+elementList.get(i)+"\n");
			stringBuilder.append(elementList.get(i)+"\n");
		}
		return stringBuilder.toString();
	}

	@Override
	public String toStringExtensive() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ElementComponent Name: "+super.toString());
		stringBuilder.append("Structure Type: "+ structType.toString());
		stringBuilder.append("Active Comparator: " + activeComponentComparator);
		stringBuilder.append("List:\n");
		for(int i = 0; i<elementList.size();i++) {
			//stringBuilder.append("\t"+elementList.get(i)+"\n");
			stringBuilder.append(elementList.get(i)+"\n");
		}
		return stringBuilder.toString();
	}

	
}
