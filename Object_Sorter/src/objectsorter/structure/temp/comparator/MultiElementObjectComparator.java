package objectsorter.structure.temp.comparator;

import java.util.ArrayList;
import java.util.Comparator;

import objectsorter.structure.temp.Element;
import objectsorter.structure.temp.ElementObject;

public class MultiElementObjectComparator <T extends ElementObject> extends Element implements Comparator<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3184032193965342215L;
	ArrayList<ElementObjectComparator<ElementObject>> elementObjectComparatorList;
	
	public MultiElementObjectComparator() {
		super();
		this.elementObjectComparatorList = new ArrayList<>();
	}
	
	public ArrayList<ElementObjectComparator<ElementObject>> getElementObjectComparatorList() {
		return elementObjectComparatorList;
	}

	public void setElementObjectComparatorList(
			ArrayList<ElementObjectComparator<ElementObject>> elementObjectComparatorList) {
		this.elementObjectComparatorList = elementObjectComparatorList;
	}
	
	@Override
	public int compare(T elementObject1, T elementObject2) {
		for(ElementObjectComparator<ElementObject> comparator : elementObjectComparatorList) {
			int compareValue = comparator.compare(elementObject1, elementObject2);
			if(compareValue!=0)return compareValue;
		}
		return 0;
	}

}
