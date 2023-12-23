package objectsorter.structure.temp.comparator.eightysix;

import java.util.ArrayList;
import java.util.Collections;

import objectsorter.structure.ElementObject;
import objectsorter.structure.temp.Element;

public class ElementList<E extends Element> extends ElementObject{

	/**
	 * 
	 */
	/*private static final long serialVersionUID = -8018839762578996351L;
	public static final int DUPLICATE = 0, UNIQUEFINAL = 1, UNIQUEOVERRIDE = 3, 
			DUPLICATEMODULO = 0, UNIQUEMODULO = 1;
	private int listType;
	private ArrayList<E> elementArrayList; // super.getElementList
	private ElementComparator comparator;
	
	public ElementList() {
		this.listType = ElementList.DUPLICATE;
		this.elementArrayList = new ArrayList<>();
		this.comparator = null;
	}
	
	public ElementList(int listType) {
		this.listType = listType;
		this.elementArrayList = new ArrayList<>();
		this.comparator = null;
	}
	
	public ElementList(int listType, ElementComparator comparator) {
		this.listType = listType;
		this.elementArrayList = new ArrayList<>();
		this.comparator = null;
	}

	public int getListType() {
		return listType;
	}

	public void setListType(int listType) {
		this.listType = listType;
	}

	public ArrayList<E> getElementArrayList() {
		return elementArrayList;
	}

	public void setElementArrayList(ArrayList<E> elementArrayList) {
		this.elementArrayList = elementArrayList;
	}

	public ElementComparator getComparator() {
		return comparator;
	}

	public void setComparator(ElementComparator comparator) {
		this.comparator = comparator;
	}
	
	public boolean add(E element) {
		int index = Collections.binarySearch(elementArrayList, element, comparator);
		if (index<0) {
			elementArrayList.add((-(index) - 1),element);
			return true;
		}
		if(listType%2==UNIQUEMODULO) {
			if(listType==UNIQUEOVERRIDE) {
				elementArrayList.remove(index);
				elementArrayList.add(index,element);
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(E element) {
		return indexOf(element)>=0;
	}
	
	public E get(E element) {
		int index = Collections.binarySearch(elementArrayList, element, comparator);
		if (index<0) {
			return null;
		}else {
			return elementArrayList.get(index);
		}
	}
	
	public int indexOf(E element) {
		return Collections.binarySearch(elementArrayList, element, comparator);
	}
	
	public boolean isEmpty() {
		return elementArrayList.size()==0;
	}
	
	public boolean remove(E element) {
		int index = Collections.binarySearch(elementArrayList, element, comparator);
		if (index<0) {
			return false;
		}else {
			elementArrayList.remove(index);
			return true;
		}
	}
	
	public boolean removeAll(E element) {
		int index; boolean removed = false;
		while((index = Collections.binarySearch(elementArrayList, element, comparator))>=0){
			elementArrayList.remove(index);
			removed=true;
		}
		return removed;
	}
	
	public int size() {
		return elementArrayList.size();
	}*/
	
	//Add
	//Contains
	//Get
	//IndexOf
	//isEmpty
	//Remove
	//RemoveAll
	//Size
	//ToArray
	
}
